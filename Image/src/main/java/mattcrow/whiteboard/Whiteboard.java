package mattcrow.whiteboard;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JFileChooser;

/**
 *
 * @author Matt
 */
public class Whiteboard extends JPanel {
    // TODO allow resizing
    private final BufferedImage buffer = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);

    private final int MARKER_SIZE = 10;
    private final JPanel body;

    public Whiteboard() {
        super();
        setLayout(new BorderLayout());
        body = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(buffer, 0, 0, this);
            }
        };
        add(body, BorderLayout.CENTER);
        body.setBackground(Color.WHITE);
        MouseAdapter a1 = new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                for (var dx = 0; dx < MARKER_SIZE; dx++) {
                    for (var dy = 0; dy < MARKER_SIZE; dy++) {
                        buffer.setRGB(e.getX() + dx, e.getY() + dy, Color.BLACK.getRGB()); // TODO allow choosing color
                    }
                }
                repaint();
            }
        };
        body.addMouseMotionListener(a1);

        var toolsSection = new JPanel();
        add(toolsSection, BorderLayout.PAGE_START);

        SaveButton s = new SaveButton(new SaveAction(body));
        toolsSection.add(s);

        // temp
        JButton load = new JButton("Load");
        load.addActionListener((a) -> {
            JFileChooser choose = new JFileChooser();
            choose.setFileSelectionMode(JFileChooser.FILES_ONLY);
            if (choose.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                throw new UnsupportedOperationException();
            }
        });
        toolsSection.add(load);

        repaint();
    }
}
