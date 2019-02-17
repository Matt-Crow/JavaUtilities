package gameoflife;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

class GameGrid extends JPanel implements MouseListener{
    
    private final int SIZE;
    private short[][] grid;
    private int cellSize;
    
    public GameGrid() {
        super();
        SIZE = 100;
        grid = new short[SIZE][SIZE];
        
        addMouseListener(this);
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("SPACE"), "update");
        getActionMap().put("update", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
            }
        });
    }
    
    private int adjCount(int x, int y){
        int count = 0;
        for(int i = x - 1; i <= x + 1; i++){
            for(int j = y - 1; j <= y + 1; j++){
                if(!(i == x && j == y)){
                    //don't count self as adj
                    if(i >= 0 && i < SIZE && j >= 0 && j < SIZE){
                        count += grid[i][j];
                    }
                }
            }
        }
        return count;
    }
    
    private void update(){
        short[][] newGrid = new short[SIZE][SIZE];
        
        for(int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                if(adjCount(i, j) == 3){
                    newGrid[i][j] = 1;
                }
                if(adjCount(i, j) == 2 && grid[i][j] == 1){
                    newGrid[i][j] = 1;
                }
            }
        }
        
        grid = newGrid;
        repaint();
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        cellSize = (getWidth() > getHeight()) ? getHeight() / SIZE : getWidth() / SIZE;
        
        g.setColor(Color.black);
        for(int i = 0; i < SIZE; i++){
            g.fillRect(i * cellSize, 0, 1, cellSize * SIZE);
            for(int j = 0; j < SIZE; j++){
                g.fillRect(0, j * cellSize, cellSize * SIZE, 1);
                if(grid[i][j] == 1){
                    g.fillRect(i * cellSize, j * cellSize, cellSize, cellSize);
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        int i = e.getX() / cellSize;
        int j = e.getY() / cellSize;
        if(i < SIZE && j < SIZE){
            grid[i][j] = (short)((grid[i][j] == 0) ? 1 : 0);
        }
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
