package image.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.nio.file.Paths;
import javax.swing.JOptionPane;

/**
 *
 * @author Matt
 */
public class SaveButton extends JButton implements ActionListener{
    private final BufferedImage image;
    
    public SaveButton(BufferedImage image){
        super("Save");
        this.image = image;
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
            var written = ImageIO.write(image, "png", Paths.get(dir, name + ".png").toFile());
            if (!written) {
                throw new RuntimeException("Failed to write file for some reason");
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
