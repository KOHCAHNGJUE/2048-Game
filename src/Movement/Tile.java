//2048testME
package Movement;

public class Tile {
    private int[][] before;
    private int[][] after;
    
    
    public Tile (int row, int column) {
        before = new int[row][column];
        after = new int[row][column];
        
    }
    
    public void setBefore(int[][] tiles,int row,int column) {
              
        for (int i = 0; i < row; i++) {
            System.arraycopy(tiles[i], 0, before[i], 0, column);
        }
    }
    
    public void setAfter(int[][] tiles,int row, int column) {
        
        for (int i = 0; i < row; i++) {
            System.arraycopy(tiles[i], 0, after[i], 0, column);
        }
    }
    
    public int[][] printBefore(int row, int column) {
        return before;
    }
    
    public boolean compareBeforeAfter(int row, int column) {
        boolean value = false;
        for(int i=0; i<row; i++) {
            for(int j=0; j<column; j++) {
                if (before[i][j]!=after[i][j]) {
                    value = true;
                }
            }
        }
        return value;
    }
}
