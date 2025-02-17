package image.gui;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;

// TODO need to combine some of this with ImagePane - split it up into components, then combine?

/**
 *
 * @author Matt
 */
public class Whiteboard extends JPanel {
    public Whiteboard(BufferedImage buffer) {
        super();
        setLayout(new BorderLayout());

        add(new ImagePainter(buffer), BorderLayout.CENTER);

        var toolsSection = new JPanel();
        add(toolsSection, BorderLayout.PAGE_START);

        toolsSection.add(new SaveButton(buffer));

        repaint();
    }
}
