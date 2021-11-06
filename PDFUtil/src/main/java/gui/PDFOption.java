package gui;

import java.io.File;
import java.util.function.Consumer;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 *
 * @author Matt Crow
 */
public class PDFOption extends JComponent {
    private final JTextArea message;
    private final PDFChooser chooser;
    
    public PDFOption(String title, String buttonText, Consumer<File> withFile){
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        add(new JLabel(title));
        
        message = new JTextArea("TOTO Drag and drop PDF for each option");
        add(message);
        
        chooser = new PDFChooser(withFile, this);
        JButton button = new JButton(buttonText);
        button.addActionListener((e)->chooser.choose());
        add(button);
    }
}
