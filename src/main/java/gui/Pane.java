package gui;

import io.AudioInput;
import io.AudioOutput;
import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
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
        
        String ip = "unknown";
        try{
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch(UnknownHostException ex){
            ex.printStackTrace();
        }
        JLabel yourIp = new JLabel("Your IP address is " + ip);
        yourIp.setForeground(Color.black);
        yourIp.setBackground(Color.green);
        yourIp.setOpaque(true);
        add(yourIp);
        
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
        
        msgs = new JTextArea("Messages appear here");
        msgs.setEditable(false);
        msgs.setLineWrap(true);
        msgs.setWrapStyleWord(true);
        JScrollPane scrolly = new JScrollPane(msgs);
        scrolly.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrolly);
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
}
