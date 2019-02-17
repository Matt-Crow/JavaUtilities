package gameoflife;

import javax.swing.JFrame;

public class GameOfLife extends JFrame{


    public GameOfLife(){
        super("Game of Life");
        setContentPane(new GameGrid());
        setSize(500, 500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args) {
        new GameOfLife();
    }
    
}
