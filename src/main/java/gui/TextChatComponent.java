package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.function.Consumer;
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
    private final HashMap<String, Consumer<String>> commands;
    
    private static final String CMD_START = "/";
    
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
        
        commands = new HashMap<>();
        addCommand("?", (str)->listCommands());
        addCommand("echo", (str)->logLocal(String.format("Echo: %s", str)));
    }
    
    /**
     * Fired whenever the user enters something into the input field
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String msg = input.getText();
        if(msg.startsWith(CMD_START)){
            //is a command
            int firstSpace = msg.indexOf(" ");
            String inputtedCmd = (firstSpace == -1) ? msg.substring(1) : msg.substring(1, firstSpace).toUpperCase();
            String args = (firstSpace == -1) ? "" : msg.substring(firstSpace + 1);
            if(commands.containsKey(inputtedCmd)){
                commands.get(inputtedCmd).accept(args);
            } else {
                logLocal(String.format("Invalid command: %s", inputtedCmd));
            }
        } else {
            send(msg);
        }
        
        input.setText("");
    }
    
    public void addCommand(String name, Consumer<String> f){
        commands.put(name.toUpperCase(), f);
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
    
    private void listCommands(){
        logLocal("Available commands:");
        commands.keySet().forEach((cmdName)->{
            logLocal(String.format("* %s%s", CMD_START, cmdName));
        });
    }
}
