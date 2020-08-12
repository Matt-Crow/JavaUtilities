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
 * The TextChatComponent handles the graphical rendering of text messages and input.
 * In the future, I will want to split this into front-end and back-end components.
 * Not sure if I'll have one back-end for processing commands and another for network stuff, or just have one class
 * @author Matt
 */
public class TextChatComponent extends JComponent implements ActionListener{
    private final JTextArea msgs;
    private final JScrollPane scroll;
    private final JTextField input;
    
    /*
    TODO: add command class so these can have descriptions and other attributes
    also, move this to the backend
    */
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
     * Fired whenever the user enters something into the input field.
     * First, checks to see if they entered a command. 
     * If so, passes everything after the first space to the command's consumer
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String msg = input.getText();
        if(msg.startsWith(CMD_START)){
            int firstSpace = msg.indexOf(" ");
            String inputtedCmd = (firstSpace == -1) ? msg.substring(1) : msg.substring(1, firstSpace).toUpperCase(); // everything between the CMD_START and the first space (or until the end of the line) 
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
    
    /**
     * Registers a string with the list of commands.
     * When the user types the given name prefixed with CMD_START,
     * runs the given consumer, passing the text they entered after the name as an argument to the consumer.
     * 
     * @param name the case-insensitive name to associate with the given consumer
     * @param f the consumer to run when a user runs the given command
     */
    public void addCommand(String name, Consumer<String> f){
        commands.put(name.toUpperCase(), f);
    }
    
    /**
     * Prints a message only on this local chat,
     * without sending it to the server.
     * @param message the message to display.
     */
    private void logLocal(String message){
        msgs.append(message);
        msgs.append("\n");
        SwingUtilities.invokeLater(()->{
            JScrollBar bar = scroll.getVerticalScrollBar();
            bar.setValue(bar.getMaximum());
            scroll.repaint();
        });
    }
    
    /**
     * In the future, this will send the given message to the backend
     * so it can broadcast the message to all connected users.
     * @param message the message to send to everyone in the chat
     */
    private void send(String message){
        // do server stuff in the future
        logLocal(String.format("You: %s", message));
    }
    
    /**
     * Displays a list of available commands.
     * In the future, 
     * this should also display details on the commands, 
     * such as a brief description
     */
    private void listCommands(){
        logLocal("Available commands:");
        commands.keySet().forEach((cmdName)->{
            logLocal(String.format("* %s%s", CMD_START, cmdName));
        });
    }
}
