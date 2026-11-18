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
        List<Cell> solutionPath = MazeSolvebfs();
        add(new MazeView(grid, solutionPath));

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

    public List<Cell> MazeSolvebfs () {

        int rows = grid.length;
        int columns = grid[0].length;

        Queue<Cell> queue = new LinkedList<>();
        Cell[][] predecessors = new Cell[rows][columns];
        List<Cell> solutionPath = new ArrayList<>();

        for (Cell[] row : grid) {

            for (Cell cell : row) {

                cell.visited = false;

            }

        }

        Cell start = grid[0][0];
        Cell exit = grid[rows - 1][columns - 1];
        queue.add(start);
        start.visited = true;

        while (!queue.isEmpty()) {

            Cell current = queue.poll();
            int row = current.row;
            int col = current.col;

            if (current == exit) {

                for (Cell cell = exit; cell != null; cell = predecessors[cell.row][cell.col]) {
                    
                    solutionPath.add(cell);

                }

                Collections.reverse(solutionPath);
                System.out.println("Maze solved!");
                return solutionPath;

            }

            if (row > 0 && !current.top && !grid[row - 1][col].bottom && !grid[row - 1][col].visited) {
                
                Cell neighbor = grid[row - 1][col];
                neighbor.visited = true;
                predecessors[row - 1][col] = current;
                queue.add(neighbor);

            }

            if (row < rows - 1 && !current.bottom && !grid[row + 1][col].top && !grid[row + 1][col].visited) {
                
                Cell neighbor = grid[row + 1][col];
                neighbor.visited = true;
                predecessors[row + 1][col] = current;
                queue.add(neighbor);

            }

            if (col > 0 && !current.left && !grid[row][col - 1].right && !grid[row][col - 1].visited) {

                Cell neighbor = grid[row][col - 1];
                neighbor.visited = true;
                predecessors[row][col - 1] = current;
                queue.add(neighbor);

            }

            if (col < columns - 1 && !current.right && !grid[row][col + 1].left && !grid[row][col + 1].visited) {

                Cell neighbor = grid[row][col + 1];
                neighbor.visited = true;
                predecessors[row][col + 1] = current;
                queue.add(neighbor);

            }

        }

        System.out.println("No solution found.");

        return solutionPath;

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
