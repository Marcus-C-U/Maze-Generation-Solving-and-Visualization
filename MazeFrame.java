import java.util.LinkedList;
import java.util.Queue;
import javax.swing.*;

public class MazeFrame extends JFrame {
    
    int M = 10;
    int N = 10;
    Cell[][] grid = new Cell[M][N];
    
    
    public MazeFrame() {

        setTitle("Maze Generator");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initializeCells();
        MazeGenerateDepthFirst();
      

    }

    public void MazeGenerateDepthFirst () {
        
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                Cell cell = grid[i][j];
                cell.left = Math.random() < 0.5;
                cell.right = Math.random() < 0.5;
                cell.top = Math.random() < 0.5;
                cell.bottom = Math.random() < 0.5;
            }
        }

    }

    private void initializeCells() {

        for (int i = 0; i < grid.length; i++) {

            for (int j = 0; j < grid[0].length; j++) {

                grid[i][j] = new Cell(i, j);
            
            }

        }
        
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            MazeFrame mazeFrame = new MazeFrame();
            mazeFrame.setVisible(true);
        });
    
    }
    
}
