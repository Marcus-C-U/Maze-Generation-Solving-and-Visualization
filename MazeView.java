import java.awt.Canvas;

public class MazeView extends Canvas {

    Cell[][] grid;

    public MazeView(Cell[][] grid) {
        
        this.grid = grid;

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

    }
    
}
