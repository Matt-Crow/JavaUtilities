package animations;

import animations.animation.implementations.PolarGraphAnimation;
import animations.animation.implementations.SinusoidalGraphAnimation;
import animations.animation.implementations.VectorFieldAnimation;
import animations.gui.GuiService;
import animations.gui.Window;
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
        Window window = new GuiService().createWindow();
        
        // Testing stuff
        //window.getContent().setAnimation(new PolarGraphAnimation());
        //window.getContent().setAnimation(new SinusoidalGraphAnimation());
        window.getContent().setAnimation(new VectorFieldAnimation());
    }
}
