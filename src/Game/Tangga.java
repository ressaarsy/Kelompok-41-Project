package Game;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import java.awt.Image;

public class Tangga {

    public static void drawTangga(Graphics g) {
        Image tangga1 = new ImageIcon("res/Papan/Tangga/Tangga1.png").getImage();
        Image tangga2 = new ImageIcon("res/Papan/Tangga/Tangga2.png").getImage();
        Image tangga3 = new ImageIcon("res/Papan/Tangga/Tangga3.png").getImage();
        Image tangga4 = new ImageIcon("res/Papan/Tangga/Tangga4.png").getImage();

        g.drawImage(tangga1, 100, 215, 200, 270, null); 
        g.drawImage(tangga2, 285, 260, 130, 190, null); 
        g.drawImage(tangga3, 340, 110, 115, 140, null); 
        g.drawImage(tangga4, 65, 55, 70, 140, null);
    }
}
