import java.awt.*;
import javax.swing.JFrame;

/*
This was my last assignment for CISP 401 at ARC
*/
public class Pumpkin extends JFrame{
    private Color[] colors;
    
    public Pumpkin(){
        colors = new Color[]{
            Color.ORANGE,
            Color.BLUE,
            Color.MAGENTA,
            Color.YELLOW,
            Color.RED
        };
        setBackground(Color.BLACK);
        setSize(500, 500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    @Override
    public void paint(Graphics g){
        g.clearRect(0, 0, getWidth(), getHeight());
        int w = getWidth();
        int h = getHeight();
        int spacing = w / (colors.length);
        
        g.setColor(Color.WHITE);
        g.fillRect((int)(w * 0.45), h / 5, w / 10, h / 5);
        
        for(int i = 0; i < colors.length; i++){
            g.setColor(colors[i]);
            g.fillOval(i * spacing / 2, (int)(h * 0.25), spacing, (int)(h * 0.75));
            g.fillOval(w - ((i + 2) * spacing / 2), (int)(h * 0.25), spacing, (int)(h * 0.75));
        }
        
        g.setColor(Color.BLACK);
        g.fillOval(w / 4, h / 2, w / 10, w / 10);
        g.fillOval(w / 4 * 3, h / 2, w / 10, w / 10);
    }
    
    public static void main(String[] args){
        new Pumpkin();
    }
}