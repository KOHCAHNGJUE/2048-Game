//2048testME
package Movement;


public class Left {
    private static int myScore;
    public Left(int myTiles[][],int size,int myScore){

        Left(myTiles,size,myScore);
  
    }
      private static int checkLeft(int j, int[][] tiles, int size) {
        int count=0; int i=0;
        for(i=0; i<size-1; i++) {
            if(tiles[i][j]==0) {
                count++;
            }
            else
                break;
        } return count;
    }    
    
    private static void slideToLeft(int i, int j, int[][] tiles, int size) {
        int temp=0; i=0;
        for(i=i; i<size-1; i++) {
            temp=tiles[i][j];
            tiles[i][j]=tiles[i+1][j];
            tiles[i+1][j]=temp;
        }
    }
    
    private static void Left(int[][] tiles, int size, int point) {
        int count=0;
        myScore = point;
        //removing 0s from the right
        for(int j=0; j<size; j++) {
            count = checkLeft(j,tiles,size);
            while(count>0) {
                for(int i=0; i<count; i++) {
                    slideToLeft(i,j,tiles,size);
                    count--;
                }
            }
        }        
        //remove 0s in between numbers
        int temp=0; count=size-2;
        while(count>0) {
            for(int j=0; j<size; j++) {
                for(int i=1; i<size-1; i++) {
                    if(tiles[i][j]==0) {
                    temp=tiles[i][j];
                    tiles[i][j]=tiles[i+1][j];
                    tiles[i+1][j]=temp;
                    }
                }
            } count--;  
        }
        //merge if equal
        count=size-2;
            for(int j=0; j<size; j++) {
                for(int i=0; i<size-1; i++) {
                    if(tiles[i][j]==tiles[i+1][j]) {
                        tiles[i][j]=(tiles[i+1][j])*2;
                        myScore+=tiles[i+1][j];
                        tiles[i+1][j]=0;
//                        System.out.println(point);
                        while(count>0) {
                            if(tiles[i][j]==0) {
                                temp=tiles[i][j];
                                tiles[i][j]=tiles[i+1][j];
                                tiles[i+1][j]=temp;
                            } count--;
                        }
                    }
                }
            }
            temp=0; count=size-2;
            while(count>0) {
                for(int j=0; j<size;j++) {
                    for(int i=1; i<size-1; i++) {
                        if(tiles[i][j]==0) {
                        temp=tiles[i][j];
                        tiles[i][j]=tiles[i+1][j];
                        tiles[i+1][j]=temp;
                    }
                }
            } count--;  
        } 
    }
    
    public int getScore() {
        return myScore;
    } 
}
