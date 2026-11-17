import java.util.*;
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
        
      Stack<Cell> stack = new Stack<>();
        Random random = new Random();

        for (int i = 0; i < grid.length; i++) {

            for (int j = 0; j < grid[0].length; j++) {

                Cell cell = grid[i][j];
                cell.left = true;
                cell.right = true;
                cell.top = true;
                cell.bottom = true;
                cell.visited = false;

            }

        }

        Cell start = grid[0][0];
        start.visited = true;
        stack.push(start);

        while (!stack.isEmpty()) {

            Cell current = stack.peek();
            List<Cell> neighbors = getUnvisitedNeighbors(current);

            if (neighbors.isEmpty()) {

                stack.pop();
                continue;

            }

            Cell next = neighbors.get(random.nextInt(neighbors.size()));
            removeWall(current, next);
            next.visited = true;
            stack.push(next);

        }

    }

    private List<Cell> getUnvisitedNeighbors(Cell cell) {

        List<Cell> neighbors = new ArrayList<>();
        int row = cell.row;
        int col = cell.col;

        if (row > 0 && !grid[row - 1][col].visited) {

            neighbors.add(grid[row - 1][col]);

        }

        if (row < M - 1 && !grid[row + 1][col].visited) {

            neighbors.add(grid[row + 1][col]);

        }

        if (col > 0 && !grid[row][col - 1].visited) {

            neighbors.add(grid[row][col - 1]);

        }

        if (col < N - 1 && !grid[row][col + 1].visited) {

            neighbors.add(grid[row][col + 1]);

        }

        return neighbors;

    }

    private void removeWall(Cell first, Cell second) {

        if (first.row < second.row) {

            first.bottom = false;
            second.top = false;

        } 
        
        else if (first.row > second.row) {

            first.top = false;
            second.bottom = false;

        } 
        
        else if (first.col < second.col) {

            first.right = false;
            second.left = false;

        } 
        
        else {

            first.left = false;
            second.right = false;

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
