package Game;

import java.awt.*;

public class Dadu {

    private int currentDiceValue = 1;

    public void drawDice(Graphics g, int x, int y) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, 50, 50);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, 50, 50);

        switch (currentDiceValue) {
            case 1 -> drawDot(g, x + 25, y + 25);
            case 2 -> {
                drawDot(g, x + 10, y + 10);
                drawDot(g, x + 40, y + 40);
            }
            case 3 -> {
                drawDot(g, x + 10, y + 10);
                drawDot(g, x + 25, y + 25);
                drawDot(g, x + 40, y + 40);
            }
            case 4 -> {
                drawDot(g, x + 10, y + 10);
                drawDot(g, x + 10, y + 40);
                drawDot(g, x + 40, y + 10);
                drawDot(g, x + 40, y + 40);
            }
            case 5 -> {
                drawDot(g, x + 10, y + 10);
                drawDot(g, x + 10, y + 40);
                drawDot(g, x + 40, y + 10);
                drawDot(g, x + 40, y + 40);
                drawDot(g, x + 25, y + 25);
            }
            case 6 -> {
                drawDot(g, x + 10, y + 10);
                drawDot(g, x + 10, y + 25);
                drawDot(g, x + 10, y + 40);
                drawDot(g, x + 40, y + 10);
                drawDot(g, x + 40, y + 25);
                drawDot(g, x + 40, y + 40);
            }
        }
    }

    private void drawDot(Graphics g, int x, int y) {
        g.fillOval(x - 5, y - 5, 10, 10);
    }

    public void setCurrentDiceValue(int value) {
        currentDiceValue = value;
    }

    public int getCurrentDiceValue() {
        return currentDiceValue;
    }
}