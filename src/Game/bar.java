
package Game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class bar extends JPanel {
    
    public void paintComponent(Graphics g){
    super.paintComponent(g);
    
    ImageIcon a = new ImageIcon("bar.png");
    a.paintIcon(this, g, 190, 190);
            
        //     g.drawImage(a.getImage(), 190, 190, null);
    
    }
    
}
