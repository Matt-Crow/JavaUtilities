package gui;

import io.AudioInput;
import io.AudioOutput;
import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import net.Connection;

/**
 *
 * @author Matt
 */
public class Pane extends JPanel implements WindowListener{
    private Connection conn;
    private AudioInput microphone;
    private AudioOutput speakers;
    private final JPanel connect;
    private final JTextArea msgs;
    
    public Pane() throws LineUnavailableException{
        super();
        
        AudioFormat format = new AudioFormat(8000.0f, 16, 1, true, true);
        microphone = new AudioInput(format);
        speakers = new AudioOutput(format);
        
        setBackground(Color.black);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        msgs = new JTextArea("Messages appear here");
        msgs.setEditable(false);
        msgs.setLineWrap(true);
        msgs.setWrapStyleWord(true);
        JScrollPane scrolly = new JScrollPane(msgs);
        scrolly.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrolly);
        msgs.append("\nIP addresses for this machine include:");
        try {
            for(String thisIp : getInetAddrs()){
                msgs.append("\n" + thisIp);
            }
        } catch (UnknownHostException ex) {
            msgs.append("\nError: unknown host");
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
        msgs.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                scrollDown();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                scrollDown();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                scrollDown();
            }
        
            private void scrollDown(){
                SwingUtilities.invokeLater(()->{
                    JScrollBar b = scrolly.getVerticalScrollBar();
                    b.setValue(b.getMaximum());
                });
            }
        });
        
        connect = new JPanel();
        connect.setLayout(new BoxLayout(connect, BoxLayout.Y_AXIS));
        connect.setBackground(Color.yellow);
        
        JLabel label = new JLabel("Enter the IP address to chat with");
        connect.add(label);
        
        JTextField ipInput = new JTextField("000.000.000.000");
        connect.add(ipInput);
        SwingUtilities.invokeLater(()->{
            ipInput.requestFocus();
        });
        
        JButton connButton = new JButton("connect");
        connButton.addActionListener((e)->{
            connect(ipInput.getText());
        });
        connect.add(connButton);
        
        add(connect);
    }

    public void connect(String ipAddr){
        if(conn != null){
            conn.close();
        }
        try{
            conn = new Connection(microphone, speakers, InetAddress.getByName(ipAddr));
            microphone.start();
            speakers.start();
            conn.open();
            connect.setBackground(Color.green);
        } catch (UnknownHostException ex) {
            msgs.append("\n Unknown host: " + ipAddr);
            connect.setBackground(Color.red);
        } catch (SocketException ex) {
            msgs.append("\n Socket error. See console for output");
            connect.setBackground(Color.red);
            ex.printStackTrace();
        } catch (LineUnavailableException ex) {
            msgs.append("\n Line unavailable");
            connect.setBackground(Color.red);
        }
    }
    
    @Override
    public void windowOpened(WindowEvent e) {}

    @Override
    public void windowClosing(WindowEvent e) {
        microphone.close();
        speakers.close();
        if(conn != null){
            conn.close();
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {}

    @Override
    public void windowIconified(WindowEvent e) {}

    @Override
    public void windowDeiconified(WindowEvent e) {}

    @Override
    public void windowActivated(WindowEvent e) {}

    @Override
    public void windowDeactivated(WindowEvent e) {}
    
    /**
     * 
     * @return all IP addresses that can be
     * used to connect to this computer
     */
    private String[] getInetAddrs() throws UnknownHostException, SocketException{
        ArrayList<String> ret = new ArrayList<>();
        
        for(InetAddress addr : InetAddress.getAllByName(InetAddress.getLocalHost().getHostName())){
            if(!addr.isLoopbackAddress()){
                ret.add(addr.getHostAddress());
            }
        }
        /*
        Enumeration<NetworkInterface> netInts = NetworkInterface.getNetworkInterfaces();
        NetworkInterface inter;
        Enumeration<InetAddress> addrs;
        InetAddress addr;
        while(netInts.hasMoreElements()){
            inter = netInts.nextElement();
            //System.out.printf("NetworkInterface %s\n", inter.getDisplayName());
            addrs = inter.getInetAddresses();
            while(addrs.hasMoreElements()){
                addr = addrs.nextElement();
                if(!addr.isLoopbackAddress()){
                    //System.out.printf("-IP %s\n", addr.getHostAddress());
                    ret.add(addr.getHostAddress());
                }
            }
        }*/
        
        return ret.toArray(new String[ret.size()]);
    }
    
    public static void main(String[] args) throws SocketException, LineUnavailableException, UnknownHostException{
        for(String ip : new Pane().getInetAddrs()){
            System.out.println(ip);
        }
    }
}
