public class Cell {
    
    public boolean visited;
    int row;
    int col;
    public boolean left,right,top,bottom;

    public Cell(int row, int col){
        this.row = row;
        this.col = col;
        visited = false;
        left = right = top = bottom = true;
    }
    
}
