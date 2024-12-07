package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class UlarTangga extends JFrame implements ActionListener {

    private String player1Name;
    private String player2Name;

    private JButton buttonGo = new JButton("Kocok Dadu");
    private JButton buttonPause = new JButton("Pause");
    private JPanel boardPanel;
    private Random rand = new Random();

    private Dadu diceButton = new Dadu();

    public UlarTangga(String player1Name, String player2Name) {
        super("Permainan Ular Tangga");
        this.player1Name = player1Name;
        this.player2Name = player2Name;

        Container c = this.getContentPane();
        c.setLayout(null);

        showPlayerNames(c);

        boardPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawBoard(g);
            }
        };
        boardPanel.setBounds(50, 50, 500, 500);
        boardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        buttonPause.setBounds(520, 10, 100, 30);
        buttonPause.addActionListener(this);

        buttonGo.setBounds(275, 570, 125, 30);
        buttonGo.addActionListener(this);

        c.add(boardPanel);
        c.add(buttonPause);
        c.add(buttonGo);

        this.setSize(650, 650);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void showPlayerNames(Container c) {
        JLabel playerNamesLabel = new JLabel(player1Name + " vs " + player2Name);
        playerNamesLabel.setFont(new Font("Arial", Font.BOLD, 18));
        playerNamesLabel.setBounds(50, 10, 500, 30);
        playerNamesLabel.setHorizontalAlignment(SwingConstants.CENTER);
        c.add(playerNamesLabel);
    }

    private void drawBoard(Graphics g) {
        int rows = 10;
        int cols = 10;
        int cellSize = 50;
        int squareNumber = 1;

        Font boldFont = new Font("Arial", Font.BOLD, 20);
        g.setFont(boldFont);

        for (int row = 0; row < rows; row++) {
            if (row % 2 == 0) {
                for (int col = 0; col < cols; col++) {
                    drawCell(g, row, col, cellSize, squareNumber++);
                }
            } else {
                for (int col = cols - 1; col >= 0; col--) {
                    drawCell(g, row, col, cellSize, squareNumber++);
                }
            }
        }
    }

    private void drawCell(Graphics g, int row, int col, int cellSize, int squareNumber) {
        int x = col * cellSize;
        int y = (10 - 1 - row) * cellSize;

        if ((row + col) % 2 == 0) {
            g.setColor(new Color(0x88c26d));
        } else {
            g.setColor(new Color(0xf5deb3));
        }
        g.fillRect(x, y, cellSize, cellSize);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, cellSize, cellSize);

        String number = String.valueOf(squareNumber);
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(number);
        int textHeight = fm.getHeight();

        int xText = x + (cellSize - textWidth) / 2;
        int yText = y + (cellSize + textHeight) / 2 - 2;
        g.setColor(Color.BLACK);
        g.drawString(number, xText, yText);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonGo) {
            startDiceRoll();
        } else if (e.getSource() == buttonPause) {
            TombolPause pauseButton = new TombolPause(this);
            pauseButton.showPauseMenu();
        }
    }

    private void startDiceRoll() {
        Thread diceRollThread = new Thread(new Runnable() {
            @Override
            public void run() {
                long startTime = System.currentTimeMillis();
                while (System.currentTimeMillis() - startTime < 1000) {
                    int newValue = rand.nextInt(6) + 1;
                    diceButton.setCurrentDiceValue(newValue);
    
                    repaint();
    
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
    
                int finalValue = rand.nextInt(6) + 1;
                diceButton.setCurrentDiceValue(finalValue);
                repaint();
            }
        });
    
        diceRollThread.start();
    }    

    public void resetGame() {
        diceButton.setCurrentDiceValue(1);
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        diceButton.drawDice(g, 575, 81);
    }
}
