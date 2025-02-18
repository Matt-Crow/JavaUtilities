package image.gui;

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
    private final JMenuItem modeButton;

    public ToolBar(ApplicationState applicationState){
        super();
        this.applicationState = applicationState;

        JMenuItem openButton = new JMenuItem("open");
        openButton.addActionListener(e -> chooseFile());
        add(openButton);

        modeButton = new JMenuItem("Draw");
        modeButton.addActionListener(e -> changeModes());
        add(modeButton);

        var saveButton = new SaveButton(applicationState);
        add(saveButton);
    }
    
    private void chooseFile() {
        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jfc.setDialogTitle("Choose Image to Open");
        if(jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
            var chosenImage = new ImageLoader().loadImage(jfc.getSelectedFile().getAbsolutePath());
            applicationState.setImage(chosenImage);
        }
    }

    private void changeModes() {
        if (applicationState.getMouseTool() == applicationState.getPannerTool()) {
            // in pan mode, switch to draw mode
            applicationState.setMouseTool(applicationState.getDrawTool());
            modeButton.setText("Pan");
        } else {
            // in draw mode, switch to pan mode
            applicationState.setMouseTool(applicationState.getPannerTool());
            modeButton.setText("Draw");
        }
    }
}
