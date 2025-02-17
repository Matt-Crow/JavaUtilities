package mattcrow.whiteboard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.nio.file.Paths;
import javax.swing.JOptionPane;

/**
 *
 * @author Matt
 */
public class SaveButton extends JButton implements ActionListener{
    private final SaveAction saveAction;
    
    public SaveButton(SaveAction saveAction){
        super("Save");
        this.saveAction = saveAction;
        addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
            System.out.println(chooser.getSelectedFile().getAbsolutePath());
            String name = JOptionPane.showInputDialog("Enter a name for this drawing: ");
            saveTo(chooser.getSelectedFile().getAbsolutePath(), name);
        }
    }
    
    private void saveTo(String dir, String name){
        saveAction.saveTo(Paths.get(dir, name + ".png").toFile());
    }
}
