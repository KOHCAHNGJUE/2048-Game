//2048testME
package Movement;


public class Right {
    private static int myScore;
    public Right(int myTiles[][],int size,int myScore){

        Right(myTiles,size,myScore);
  
    }
     private static int checkRight(int j, int[][] tiles, int size) {
        int count=0; int i=0;
        for(i=0; i<size-1; i++) {
            if(tiles[size-1-i][j]==0) {
                count++;
            }
            else
                break;
        } return count;
    }    
    
    private static void slideToRight(int i, int j, int[][] tiles, int size) {
        int temp=0; i=0;
        for(i=i; i<size-1; i++) {
            temp=tiles[size-1-i][j];
            tiles[size-1-i][j]=tiles[size-2-i][j];
            tiles[size-2-i][j]=temp;
        }
    }
    
    private static void Right(int[][] tiles, int size, int point) {
        myScore = point;
        int count=0;
        //removing 0s from the right
        for(int j=0; j<size; j++) {
            count = checkRight(j,tiles,size);
            while(count>0) {
                for(int i=0; i<count; i++) {
                    slideToRight(i,j,tiles,size);
                    count--;
                }
            }
        }        
        //remove 0s in between numbers
        int temp=0; count=size-2;
        while(count>0) {
            for(int j=0; j<size; j++) {
                for(int i=1; i<size-1; i++) {
                    if(tiles[size-1-i][j]==0) {
                    temp=tiles[size-1-i][j];
                    tiles[size-1-i][j]=tiles[size-2-i][j];
                    tiles[size-2-i][j]=temp;
                    }
                }
            } count--;  
        }
        //merge if equal
        count=size-2;
            for(int j=0; j<size; j++) {
                for(int i=0; i<size-1; i++) {
                    if(tiles[size-1-i][j]==tiles[size-2-i][j]) {
                        tiles[size-1-i][j]=(tiles[size-2-i][j])*2;
                        myScore+=tiles[size-2-i][j];
                        tiles[size-2-i][j]=0;
//                        System.out.println(points);
                        while(count>0) {
                            if(tiles[size-1-i][j]==0) {
                                temp=tiles[size-1-i][j];
                                tiles[size-1-i][j]=tiles[size-2-i][j];
                                tiles[size-2-i][j]=temp;
                            } count--;
                        }
                    }
                }
            }
            temp=0; count=size-2;
            while(count>0) {
                for(int j=0; j<size;j++) {
                    for(int i=1; i<size-1; i++) {
                        if(tiles[size-1-i][j]==0) {
                        temp=tiles[size-1-i][j];
                        tiles[size-1-i][j]=tiles[size-2-i][j];
                        tiles[size-2-i][j]=temp;
                    }
                }
            } count--;  
        } 
    }
    
    public int getScore() {
        return myScore;
    }

}
