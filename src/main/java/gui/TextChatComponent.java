package gui;

import java.awt.BorderLayout;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

/**
 * split this into multiple classes?
 * @author Matt
 */
public class TextChatComponent extends JComponent{
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
        add(input, BorderLayout.PAGE_END);
    }
}
