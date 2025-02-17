package imageViewer.start;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.LinkedList;
import java.util.function.Consumer;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * The ToolBar serves as the menu bar for the main image viewing frame.
 * It features a small selection of tools and menus for the user to choose from.
 * 
 * @author Matt Crow
 */
class ToolBar extends JMenuBar {
    private final LinkedList<Consumer<File>> fileSelectionListeners;
    
    ToolBar(){
        super();
        
        JMenuItem openButton = new JMenuItem("open");
        openButton.addActionListener(this::chooseFile);
        add(openButton);
        
        fileSelectionListeners = new LinkedList<>();
    }
    
    public void addFileSelectionListener(Consumer<File> listener){
        fileSelectionListeners.add(listener);
    }
    
    private void chooseFile(ActionEvent e){
        chooseFile();
    }
    private void chooseFile(){
        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jfc.setDialogTitle("Choose Image to Open");
        if(jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
            fileSelectionListeners.forEach((listener)->listener.accept(jfc.getSelectedFile()));
        }
    }
}
