package Game;

import javax.swing.*;
import java.awt.event.ActionListener;

public abstract class AbstractTampilanMenu implements GameInterface {
    public abstract void initializeFrame();
    public abstract JButton createButton(JPanel panel, String label, int x, int y, int width, int height, ActionListener actionListener);
    public abstract void addImage(JPanel panel, String imagePath);
}
