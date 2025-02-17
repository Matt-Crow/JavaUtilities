package image.gui;

import javax.swing.JPanel;

import image.ApplicationState;

import java.awt.BorderLayout;

// TODO need to combine some of this with ImagePane - split it up into components, then combine?

/**
 *
 * @author Matt
 */
public class Whiteboard extends JPanel {
    public Whiteboard(ApplicationState applicationState) {
        super();
        setLayout(new BorderLayout());

        add(new ImagePainter(applicationState), BorderLayout.CENTER);

        var toolsSection = new JPanel();
        add(toolsSection, BorderLayout.PAGE_START);

        toolsSection.add(new SaveButton(applicationState));

        repaint();
    }
}
