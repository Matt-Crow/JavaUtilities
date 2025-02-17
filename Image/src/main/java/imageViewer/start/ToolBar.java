package imageViewer.start;

import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import image.ApplicationState;
import image.io.ImageLoader;

/**
 * The ToolBar serves as the menu bar for the main image viewing frame.
 * It features a small selection of tools and menus for the user to choose from.
 * 
 * @author Matt Crow
 */
public class ToolBar extends JMenuBar {
    private final ApplicationState applicationState;

    public ToolBar(ApplicationState applicationState){
        super();
        this.applicationState = applicationState;

        JMenuItem openButton = new JMenuItem("open");
        openButton.addActionListener(e -> chooseFile());
        add(openButton);
    }
    
    private void chooseFile(){
        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jfc.setDialogTitle("Choose Image to Open");
        if(jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
            var chosenImage = new ImageLoader().loadImage(jfc.getSelectedFile().getAbsolutePath());
            applicationState.setImage(chosenImage);
        }
    }
}
