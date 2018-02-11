package graphics;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JButton;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.BoxLayout;

import utilities.Op;

@SuppressWarnings("serial")
public class ColorTesterPanel extends JPanel implements ChangeListener{
	private JSlider r;
	private JSlider g;
	private JSlider b;
	private JButton button;
	
	public ColorTesterPanel(){
		super();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setVisible(true);
		r = new JSlider(0, 255, 0);
		g = new JSlider(0, 255, 0);
		b = new JSlider(0, 255, 0);
		add(r);
		add(g);
		add(b);
		r.addChangeListener(this);
		g.addChangeListener(this);
		b.addChangeListener(this);
		
		button = new JButton("Generate Code");
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Op.log("new CustomColor(" + r.getValue() + ", " + g.getValue() + ", " + b.getValue() + ");");
			}
		});
		add(button);
	}
	public void stateChanged(ChangeEvent e){
		repaint();
	}
	public void paintComponent(Graphics graphics){
		super.paintComponents(graphics);
		graphics.setColor(new CustomColor(r.getValue(), g.getValue(), b.getValue()));
		graphics.fillRect(0, 100, 256, 256);
	}
}
