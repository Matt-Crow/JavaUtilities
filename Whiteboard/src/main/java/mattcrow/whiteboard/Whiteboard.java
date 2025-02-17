package mattcrow.whiteboard;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;

/**
 *
 * @author Matt
 */
public class Whiteboard extends JPanel{
    private final LinkedList<Point> points = new LinkedList<>();
    private final int MARKER_SIZE = 10;
    private final JPanel body;
    
    public Whiteboard(){
        super();

        setLayout(new BorderLayout());
        body = new JPanel() {
            @Override
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                Iterator<Point> i = points.iterator();
                Point p;
                while(i.hasNext()){
                    p = i.next();
                    g.fillRect(p.x, p.y, MARKER_SIZE, MARKER_SIZE);
                }
            }
        };
        add(body, BorderLayout.CENTER);
        body.setBackground(Color.WHITE);
        initMouseAdapter();

        var toolsSection = new JPanel();
        add(toolsSection, BorderLayout.PAGE_START);

        SaveButton s = new SaveButton(new SaveAction(body));
        s.setPointsToSave(points);
        toolsSection.add(s);
        
        //temp
        JButton load = new JButton("Load");
        load.addActionListener((a)->{
            JFileChooser choose = new JFileChooser();
            choose.setFileSelectionMode(JFileChooser.FILES_ONLY);
            if(choose.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
                throw new UnsupportedOperationException();
            }
        });
        toolsSection.add(load);
        
        repaint();
    }
    
    private void initMouseAdapter(){
        MouseAdapter a = new MouseAdapter(){
            @Override
            public void mouseDragged(MouseEvent e){
                /*
                The points are rounded to a rectangular grid
                so that it is easier to erase them
                */
                //if left mouse button, draw, if right, erase
                if(SwingUtilities.isLeftMouseButton(e)){
                    points.add(new Point(e.getX() - e.getX() % MARKER_SIZE, e.getY() - e.getY() % MARKER_SIZE));
                } else if(SwingUtilities.isRightMouseButton(e)){
                    points.remove(new Point(e.getX() - e.getX() % MARKER_SIZE, e.getY() - e.getY() % MARKER_SIZE));
                }
                repaint();
            }
        };
        body.addMouseMotionListener(a);
    }
}
