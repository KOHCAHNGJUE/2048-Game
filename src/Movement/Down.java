//2048testME
package Movement;

public class Down {
    private static int myScore;
    public Down(int myTiles[][],int size, int myScore){

        Down(myTiles,size,myScore);
  
    }
        private static int checkDown(int i, int[][] tiles, int size) {
        int count=0; int j=0;
        for(j=0; j<size-1; j++) {
            if(tiles[i][size-j-1]==0) {
                count++;
            }
            else
                break;
        } return count;
    }
    
    private static void slideToDown(int i, int j, int[][] tiles, int size) {
        int temp=0; j=0;
        for(j=j; j<size-1; j++) {
            temp=tiles[i][size-1-j];
            tiles[i][size-1-j]=tiles[i][size-2-j];
            tiles[i][size-2-j]=temp;
        }
    }
    
    private static void Down(int[][] tiles, int size, int point) {
        myScore = point;
        int count=0;
        //removing 0s from the right
        for(int i=0; i<size; i++) {
            count = checkDown(i,tiles,size);
            while(count>0) {
                for(int j=0; j<count; j++) {
                    slideToDown(i,j,tiles,size);
                    count--;
                }
            }
        }        
        //remove 0s in between numbers
        int temp=0; count=size-2;
        while(count>0) {
            for(int i=0; i<size; i++) {
                for(int j=1; j<size-1; j++) {
                    if(tiles[i][size-1-j]==0) {
                    temp=tiles[i][size-1-j];
                    tiles[i][size-1-j]=tiles[i][size-2-j];
                    tiles[i][size-2-j]=temp;
                    }
                }
            } count--;  
        }
        //should merge if equal
        count=size-2;
            for(int i=0; i<size; i++) {
                for(int j=0; j<size-1; j++) {
                    if(tiles[i][size-1-j]==tiles[i][size-2-j]) {
                        tiles[i][size-1-j]=(tiles[i][size-2-j])*2;
                        myScore+=tiles[i][size-2-j];
                        tiles[i][size-2-j]=0;
//                        System.out.println(point);
                        while(count>0) {
                            if(tiles[i][size-1-j]==0) {
                                temp=tiles[i][size-1-j];
                                tiles[i][size-1-j]=tiles[i][size-2-j];
                                tiles[i][size-2-j]=temp;
                            } count--;
                        }
                    }
                }
            }
            temp=0; count=size-2;
            while(count>0) {
                for(int i=0; i<size; i++) {
                    for(int j=1; j<size-1; j++) {
                        if(tiles[i][size-1-j]==0) {
                        temp=tiles[i][size-1-j];
                        tiles[i][size-1-j]=tiles[i][size-2-j];
                        tiles[i][size-2-j]=temp;
                    }
                }
            } count--;  
        }
    }
    
    public int getScore() {
        return myScore;
    }
}
