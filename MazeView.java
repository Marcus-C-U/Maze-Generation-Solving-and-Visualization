import java.awt.*;
import java.util.List;

public class MazeView extends Canvas {

    private final Cell[][] grid;
    private final List<Cell> solutionPath;

    public MazeView(Cell[][] grid, List<Cell> solutionPath) {
        
        this.grid = grid;
        this.solutionPath = solutionPath;
        
    }

    private void DrawCell(java.awt.Graphics g, Cell cell, int left, int top, int dimensions) { 
        
        top = top + 1;

        if (cell.top) {

            g.drawLine(left, top, left + dimensions, top);
        
        }

        if (cell.left) {

            g.drawLine(left, top, left, top + dimensions);
        
        }

        if (cell.right) {

            g.drawLine(left + dimensions, top, left + dimensions, top + dimensions);
        
        }

        if (cell.bottom) {

            g.drawLine(left, top + dimensions, left + dimensions, top + dimensions);
        
        }

    }

    @Override 
    public void paint(java.awt.Graphics g) {
        
        super.paint(g);
        int width = getWidth();
        int height = getHeight();
        int dimensions;
        
        if (width < height) {
        
            dimensions = width/grid.length;
        
        } 

        else {

            dimensions = height/grid.length;

        }

        for (int i = 0; i < grid.length; i++) {
            
            for (int j = 0; j < grid[0].length; j++) {

                Cell cell = grid[i][j];

                int x = j * dimensions;
                int y = i * dimensions;

                DrawCell(g, cell, x, y, dimensions);

            }

        }
        
        Graphics2D pathGraphics = (Graphics2D) g.create();
        pathGraphics.setColor(Color.BLUE);
        pathGraphics.setStroke(new BasicStroke(Math.max(2, dimensions / 8)));


        for (int i = 1; i < solutionPath.size(); i++) {
 
            try {

                Thread.sleep(100);

            } 
        
            catch (InterruptedException e) {

                e.printStackTrace();

            }

            Cell previous = solutionPath.get(i - 1);
            Cell current = solutionPath.get(i);

            int previousX = previous.col * dimensions + dimensions / 2;
            int previousY = previous.row * dimensions + dimensions / 2;
            int currentX = current.col * dimensions + dimensions / 2;
            int currentY = current.row * dimensions + dimensions / 2;
            
            pathGraphics.drawLine(previousX, previousY, currentX, currentY);

        }

    }
    
}
