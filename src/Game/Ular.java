package Game;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Ular {

    public static void drawUlar(Graphics g) {
        Image ular2 = new ImageIcon("res/Papan/Ular/ular2.png").getImage();
        Image ular3 = new ImageIcon("res/Papan/Ular/ular3.png").getImage();
        Image ular4 = new ImageIcon("res/Papan/Ular/ular4.png").getImage();
        Image ular5 = new ImageIcon("res/Papan/Ular/ular5.png").getImage();

        g.drawImage(ular2, 25, 250, 150, 200, null);
        g.drawImage(ular3, 130, 5, 120, 180, null);
        g.drawImage(ular4, 400, 325, 140, 160, null);
        g.drawImage(ular5, 250, 115, 100, 120, null);
    }
}
