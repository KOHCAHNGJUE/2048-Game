//2048testME
package Movement;


public class Up {
    private static int myScore;
    public Up(int myTiles[][],int size,int myScore){
   
        Up(myTiles,size,myScore);
  
    }
    
    
        private static int checkUp(int i, int[][] tiles, int size) {
        int count=0; int j=0;
        for(j=0; j<size-1; j++) {
            if(tiles[i][j]==0) {
                count++;
            }
            else
                break;
        } return count;
    }    
    
    private static void slideToUp(int i, int j, int[][] tiles, int size) {
        int temp=0; j=0;
        for(j=j; j<size-1; j++) {
            temp=tiles[i][j];
            tiles[i][j]=tiles[i][j+1];
            tiles[i][j+1]=temp;
        }
    }
    
    private static void Up(int[][] tiles, int size, int point) {
        myScore = point;
        int count=0;
        //removing 0s from the right
        for(int i=0; i<size; i++) {
            count = checkUp(i,tiles,size);
            while(count>0) {
                for(int j=0; j<count; j++) {
                    slideToUp(i,j,tiles,size);
                    count--;
                }
            }
        }        
        //remove 0s in between numbers
        int temp=0; count=size-2;
        while(count>0) {
            for(int i=0; i<size; i++) {
                for(int j=1; j<size-1; j++) {
                    if(tiles[i][j]==0) {
                    temp=tiles[i][j];
                    tiles[i][j]=tiles[i][j+1];
                    tiles[i][j+1]=temp;
                    }
                }
            } count--;  
        }
        //should merge if equal
        count=size-2;
            for(int i=0; i<size; i++) {
                for(int j=0; j<size-1; j++) {
                    if(tiles[i][j]==tiles[i][j+1]) {
                        tiles[i][j]=(tiles[i][j+1])*2;
                        myScore+=tiles[i][j+1];
                        tiles[i][j+1]=0;
//                        System.out.println(point);
                        while(count>0) {
                            if(tiles[i][j]==0) {
                                temp=tiles[i][j+1];
                                tiles[i][j]=tiles[i][j+1];
                                tiles[i][j+1]=temp;
                            } count--;
                        }
                    }
                }
            }
            temp=0; count=size-2;
            while(count>0) {
                for(int i=0; i<size; i++) {
                    for(int j=1; j<size-1; j++) {
                        if(tiles[i][j]==0) {
                        temp=tiles[i][j];
                        tiles[i][j]=tiles[i][j+1];
                        tiles[i][j+1]=temp;
                    }
                }
            } count--;  
        } 
    } 
    
    public int getScore() {
        return myScore;
    }
}
