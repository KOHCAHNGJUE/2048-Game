
package Movement;

import Game.NewJFrame1;
import Game.boink;
import Game.lose;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.WindowConstants;


public class Game2048_6x6 extends JPanel
{
      private static final Color BG_COLOR = new Color(0xbbada0);
  private static final String FONT_NAME = "Arial";
  private static final int TILE_SIZE = 80;
  private static final int TILES_MARGIN = 15;

  private final int size = 6;
  private int myTiles[][]=new int[size][size];
  private int undo[][]=new int[size][size];
  Tile movement = new Tile(size,size);
  boolean myWin = false;
  boolean myLose = false;
  int myScore = 0;
  int x;
  int y;
  Graphics g;
 
  

  public Game2048_6x6() {
    setPreferredSize(new Dimension(400, 435));
    setFocusable(true);
    resetGame();
    
    addKeyListener(new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            movement.setBefore(myTiles, size, size);
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
          resetGame();
          repaint();
        }
        if (!canMove()) {
          myLose = true;
        }

        if (!myWin && !myLose) {
          switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
            left();
            repaint();
            if(hasEqualNeighbour(myTiles, size))
        {
            try {
                boink boink = new boink();
            } catch (IOException ex) {
                Logger.getLogger(Main2048.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            break;
            
            case KeyEvent.VK_RIGHT:
            right();
            repaint();
            if(hasEqualNeighbour(myTiles, size))
        {
            try {
                boink boink = new boink();
            } catch (IOException ex) {
                Logger.getLogger(Main2048.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            break;
            
            case KeyEvent.VK_DOWN:
            down();
            repaint();
            if(hasEqualNeighbour(myTiles, size))
        {
            try {
                boink boink = new boink();
            } catch (IOException ex) {
                Logger.getLogger(Main2048.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            break;
        
            case KeyEvent.VK_UP:
            up();
            repaint();
            movement.setAfter(myTiles,size,size);
            if(hasEqualNeighbour(myTiles, size))
        {
            try {
                boink boink = new boink();
            } catch (IOException ex) {
                Logger.getLogger(Main2048.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            break; 
            
            //add another keypressed for undo button
            case KeyEvent.VK_BACK_SPACE:
            confUndo();
            repaint();
            movement.setAfter(myTiles,size,size);
            if(hasEqualNeighbour(myTiles, size))
        {
            try {
                boink boink = new boink();
            } catch (IOException ex) {
                Logger.getLogger(Main2048.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            break;
            }
        }

        if (!myWin && !canMove()) {
          myLose = true;
            }
        }
    });
  }
  
    public void up() {
        Up up = new Up(myTiles,size,myScore);
        movement.setAfter(myTiles,size,size);
        if (movement.compareBeforeAfter(size, size) == true) {
            myScore = up.getScore();
            generateNewTile(myTiles, size);
            setUndo();
        }
        else setBefore();
    }

    public void down() {
        Down down = new Down(myTiles,size,myScore);
        movement.setAfter(myTiles,size,size);
        if ( movement.compareBeforeAfter(size, size) == true) {
            myScore = down.getScore();
            generateNewTile(myTiles, size);
            setUndo();
        }
        else setBefore();
    }

    public void left() {
        Left left = new Left(myTiles,size,myScore);
        movement.setAfter(myTiles,size,size);
        if (movement.compareBeforeAfter(size, size) == true) {
            myScore = left.getScore();
            generateNewTile(myTiles, size);
            setUndo();
        }
        else setBefore();
    }

    public void right() {
        Right right = new Right(myTiles,size,myScore);
        movement.setAfter(myTiles,size,size);
        if (movement.compareBeforeAfter(size, size) == true) {
            myScore = right.getScore();
            generateNewTile(myTiles, size);
            setUndo();
        }
        else setBefore();
    }

    public void resetGame() {
        myScore = 0;
        myWin = false;
        myLose = false;
   
        for (int j = 0; j< size; j++) {
            for (int i = 0; i < size; i++) {
                myTiles[j][i] = 0;
            }
        }
        
    generateNewTile(myTiles, size);
    generateNewTile(myTiles, size);
    }

    private void setBefore() {
        int[][] before = movement.printBefore(size, size);
        for (int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                System.arraycopy(before[i],0, myTiles[i],0,size);
            }
        }
    }
    
    private void setUndo() {
        int[][] before = movement.printBefore(size, size);
        for (int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                System.arraycopy(before[i],0, undo[i],0,size);
            }
        }
    }
    
    private void confUndo() {
        for (int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                System.arraycopy(undo[i],0, myTiles[i],0,size);
            }
        }
    }
    
    private static boolean hasEmptyTile(int[][] tiles, int size){
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                if (tiles[i][j] == 0){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean generateNewTile(int[][] tiles, int size){
        if (!(hasEmptyTile(tiles, size))){
            return false;
        }

        Random random = new Random();
        int x;
        
        int y;
        
        //iterate until an empty tile if found
        boolean test = true;
        while (true){

            x = random.nextInt(size);
            y = random.nextInt(size);
            
            if (tiles[x][y] == 0){
                tiles[x][y] = getNewTileValue();
                test = false;
                return true;
            }
        }
    }
  
  
    //get tile value of either 2 or 4   
    private static int getNewTileValue(){
        Random random = new Random();
        int rng = random.nextInt(2) + 1;
        return (rng * 2);
    }

    public static boolean noPossibleMove(int[][]tiles, int size){    
        if (hasEmptyTile(tiles,size)){
            return false;
        }
        return !(hasEqualNeighbour(tiles,size));}
  
    private static boolean hasEqualNeighbour(int[][]tiles, int size) {
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                //check the tile in the right of the chosen tile. Ignore last column.
                if (j < size - 1){
                    if (tiles[i][j] == tiles[i][j + 1]) {
                        return true;
                    }
                }
                //check the tile below the chosen tile. Ignore last row.
                if (i < size - 1){
                    if (tiles[i][j] == tiles[i + 1][j]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    boolean canMove() {
        if (hasEqualNeighbour(myTiles,size)) {
        return true;
    }
        if(!noPossibleMove(myTiles,size))
        return true;
    
    else
    return false;
    }
 
    // @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(new Color(0x3c3c3c));
        g.fillRect(0, 0, this.getSize().width, this.getSize().height);
        g.setColor(new Color(0xd9f0f8));
        for (int y = 0; y < 6; y++) {
        for (int x = 0; x < 6; x++) {
        int a= 15 + (15*y)+(65*y);
        int b=75 + (15*x)+(65*x);
        g.fillRoundRect(a, b, 65, 65, 15, 15);}
        }
    g.setColor(new Color(0x8b8c8c));
    g.fillRoundRect(320, 8, 160, 60, 15, 15);
        
      
    for(int y = 0; y < 6; y++) {
        for(int x = 0; x < 6; x++) {
            if(myTiles[y][x]!=0) { 
            g.setColor(getBackground(myTiles[y][x]));
            String a = getString(myTiles[y][x]);
            int aa= 15 + (15*y)+(65*y);
            int bb=75 + (15*x)+(65*x);
            g.fillRoundRect(aa, bb, 65, 65, 15, 15); 
            g.setColor(new Color(0x050505));
            g.setFont(new Font(FONT_NAME, Font.BOLD, 35));
            g.drawString(a, aa+20, bb+43);
            }
        }
    }

    if(myLose) {
       lose a = new lose();
        a.setVisible(true);
        a.setResizable(false);
        a.setSize(400,490);
        a.setLocationRelativeTo(null);
        a.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
    
    g.setColor(new Color(0xd4d5d5));
    g.setFont(new Font(FONT_NAME, Font.BOLD, 20));
    g.drawString("Score", 330, 25);
    g.setFont(new Font(FONT_NAME, Font.BOLD, 40));
    g.drawString(String.valueOf(myScore), 380, 60);
    }
  
public String getString(int value) {
      switch (value) {
        case 2:         return "A";
        case 4:         return "B";
        case 8:         return "C";
        case 16:        return "D";
        case 32:        return "E";
        case 64:        return "F";
        case 128:       return "G";
        case 256:       return "H";
        case 512:       return "I";
        case 1024:      return "J";
        case 2048:      return "K";
        case 4096:      return "L";
        case 8192:      return "M";
        case 16384:     return "N";
        case 32768:     return "O";
        case 65536:     return "P";
        case 131072:    return "Q";
        case 262144:    return "R";
        case 524288:    return "S";
        case 1048576:   return "T";
        case 2097152:   return "U";
        case 4194304:   return "V";
        case 8388608:   return "W";
        case 16777216:  return "X";
        case 33554432:  return "Y";
        case 67108864:  return "Z";
      }
    return "";
    }
//  private void drawTile(Graphics g, int tile[][], int x, int y) {
//
//
//    if (myWin || myLose) {
//      g.setColor(new Color(255, 255, 255, 150));
//      g.fillRect(0, 0, getWidth(), getHeight());
//      g.setColor(new Color(78, 139, 202));
//      g.setFont(new Font(FONT_NAME, Font.BOLD, 48));
//      if (myWin) {
//        g.drawString("You won!", 68, 150);
//      }
//      if (myLose) {
//        g.drawString("Game over!", 75, 130);
//        g.drawString("You lose!", 90, 200);
//      }
//      if (myWin || myLose) {
//        g.setFont(new Font(FONT_NAME, Font.PLAIN, 16));
//        g.setColor(new Color(128, 128, 128, 255));
//        g.drawString("Press ESC to play again", 115, 397);
//      }
//    }
//
//  }

    public Color getBackground(int value) {
      switch (value) {
        case 2:    return new Color(0xffffa7);
        case 4:    return new Color(0xfedaa0);
        case 8:    return new Color(0xf8d73c);
        case 16:   return new Color(0xfbb03b);
        case 32:   return new Color(0xf37b60);
        case 64:   return new Color(0xf85e3c);
        case 128:  return new Color(0xedcf72);
        case 256:  return new Color(0xedcc61);
        case 512:  return new Color(0xedc850);
        case 1024: return new Color(0xedc53f);
        case 2048: return new Color(0xedc22e);
      }
      return new Color(0xcdc1b4);
    }
  
  
 
    public static void main(String[] args) throws IOException {

    
    NewJFrame1 a=new NewJFrame1();
    a.setVisible(true);
    a.setSize(400,435);
    a.setLocationRelativeTo(null);
   
    a.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    
     

  }}


