package mattcrow.whiteboard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.util.LinkedList;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JOptionPane;

/**
 *
 * @author Matt
 */
public class SaveButton extends JButton implements ActionListener{
    private LinkedList<Point> pointsToSave;
    private final SaveAction saveAction;
    
    public SaveButton(SaveAction saveAction){
        super("Save");
        pointsToSave = null;
        this.saveAction = saveAction;
        addActionListener(this);
    }
    
    /**
     * Sets which drawing this should save when it is clicked
     * @param points the points that comprise the drawing to save
     */
    public void setPointsToSave(LinkedList<Point> points){
        pointsToSave = points;
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
