package animations;

import animations.gui.GuiService;
import java.util.Arrays;
import javax.swing.UIManager;

/**
 *
 * @author Matt
 */
public class Main {
    public static void main(String[] args){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.err.printf("Args are %s\n", Arrays.toString(args));
        new GuiService().createWindow();
    }
}
