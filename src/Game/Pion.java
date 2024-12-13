package Game;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import java.awt.Image;

public class Pion {

    public static void drawPlayer(Graphics g, int position, boolean isLeft) {
        int cellSize = 50;
        int row = (position - 1) / 10;
        int col = (position - 1) % 10;

        if (row % 2 == 1) {
            col = 9 - col;
        }

        int x = col * cellSize;
        int y = (10 - 1 - row) * cellSize;

        if (isLeft) {
            x += -15;
        } else {
            x += cellSize - 35;
        }

        int pionWidth = (int) (cellSize * 1);
        int pionHeight = (int) (cellSize * 1);

        Image playerImage = isLeft
            ? new ImageIcon("res/Papan/Pion/p1.png").getImage() 
            : new ImageIcon("res/Papan/Pion/p2.png").getImage();

        g.drawImage(playerImage, x, y, pionWidth, pionHeight, null);
    }
}
