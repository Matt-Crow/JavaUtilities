package image.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 * JPanel which paints an image
 */
public class ImagePainter extends JPanel {
    private final BufferedImage buffer;

    public ImagePainter(BufferedImage buffer) {
        this.buffer = buffer;
        setBackground(Color.WHITE);
        addMouseMotionListener(new DrawTool(buffer, () -> repaint()));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(buffer, 0, 0, this);
    }
}
