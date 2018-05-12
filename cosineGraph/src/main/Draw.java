package main;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.lang.Math;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Draw extends JPanel {
	private double r;
	private Timer t;
	private ActionListener update;
	private double theta;
	private int petalCount = 2;
	
	public Draw(){
		super();
		setVisible(true);
		setBackground(Color.BLACK);
		
		theta = 0.0;
		
		update = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				repaint();
				if(theta > Math.PI * 2){
					theta = 0.0;
					petalCount++;
				}
			}
		};
		t = new Timer(1, update);
		t.setRepeats(true);
		t.start();
	}
	@Override
    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		int size = (getWidth() + getHeight()) / 5;
		
        g.setColor(Color.PINK);
        for(double i = 0; i < theta; i += 0.001){
        	r = size * Math.sin(petalCount * i);
        	g.fillRect(getWidth() / 2 + (int) (r * Math.cos(i)), getHeight() / 2 + (int)(r * Math.sin( i)), 1, 1);
        }
        theta += 0.005;
    }
}
