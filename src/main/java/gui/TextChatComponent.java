package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

/**
 * split this into multiple classes?
 * @author Matt
 */
public class TextChatComponent extends JComponent implements ActionListener{
    private final JTextArea msgs;
    private final JScrollPane scroll;
    private final JTextField input;
    // TODO: add commands
    
    public TextChatComponent(){
        super();
        setLayout(new BorderLayout());
        
        msgs = new JTextArea();
        msgs.setEditable(false);
        msgs.setWrapStyleWord(true);
        msgs.setLineWrap(true);
        
        scroll = new JScrollPane(msgs);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        add(scroll, BorderLayout.CENTER);
         
        input = new JTextField();
        input.addActionListener(this);
        add(input, BorderLayout.PAGE_END);
    }
    
    /**
     * Fired whenever the user enters something into the input field
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String msg = input.getText();
        if(msg.startsWith("/")){
            //is a command
        } else {
            send(msg);
        }
        
        input.setText("");
    }
    private void logLocal(String message){
        msgs.append(message);
        msgs.append("\n");
        SwingUtilities.invokeLater(()->{
            JScrollBar bar = scroll.getVerticalScrollBar();
            bar.setValue(bar.getMaximum());
            scroll.repaint();
        });
    }
    
    private void send(String message){
        // do server stuff in the future
        logLocal(String.format("You: %s", message));
    }
}
