package image.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.nio.file.Paths;
import javax.swing.JOptionPane;

import image.ApplicationState;

/**
 *
 * @author Matt
 */
public class SaveButton extends JButton implements ActionListener{
    private final ApplicationState applicationState;
    
    public SaveButton(ApplicationState applicationState){
        super("Save");
        this.applicationState = applicationState;
        addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
            String name = JOptionPane.showInputDialog("Enter a name for this drawing: ");
            saveTo(chooser.getSelectedFile().getAbsolutePath(), name);
        }
    }
    
    private void saveTo(String dir, String name){
        try {
            var written = ImageIO.write(applicationState.getImage(), "png", Paths.get(dir, name + ".png").toFile());
            if (!written) {
                throw new RuntimeException("Failed to write file for some reason");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
