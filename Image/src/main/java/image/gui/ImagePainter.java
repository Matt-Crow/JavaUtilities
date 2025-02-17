package image.gui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

import image.ApplicationState;

/**
 * JPanel which paints an image
 */
public class ImagePainter extends JPanel {
    private final ApplicationState applicationState;

    public ImagePainter(ApplicationState applicationState) {
        this.applicationState = applicationState;
        setBackground(Color.WHITE);
        addMouseMotionListener(new DrawTool(applicationState, () -> repaint()));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(applicationState.getImage(), 0, 0, this);
    }
}
