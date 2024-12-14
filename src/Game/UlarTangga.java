package Game;

import javax.swing.*;

import DataBase.DatabaseHandler;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class UlarTangga extends JFrame implements ActionListener {

    private String player1Name;
    private String player2Name;

    private int player1Position = 1;
    private int player2Position = 1;
    private boolean isPlayer1Turn = true;

    private JButton buttonGo = new JButton("Kocok Dadu");
    private JButton buttonPause = new JButton("Pause");
    private JPanel boardPanel;
    private JPanel currentTurnPanel;
    private JLabel turnNameLabel;
    private JLabel turnIconLabel;
    private Random rand = new Random();

    private Dadu diceButton = new Dadu();
    private final int[] snakeAndLadderMap = new int[101];

    public UlarTangga(String player1Name, String player2Name) {
        super("Permainan Ular Tangga");
        this.player1Name = player1Name;
        this.player2Name = player2Name;

        initializeSnakeAndLadderMap();

        Container c = this.getContentPane();
        c.setLayout(null);

        showPlayerNames(c);

        currentTurnPanel = new JPanel();
        currentTurnPanel.setBounds(325, 565, 200, 40);
        currentTurnPanel.setBackground(Color.LIGHT_GRAY);
        currentTurnPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));

        turnIconLabel = new JLabel();
        turnNameLabel = new JLabel("Giliran: " + player1Name);
        turnNameLabel.setFont(new Font("Arial", Font.BOLD, 16));

        currentTurnPanel.add(turnIconLabel);
        currentTurnPanel.add(turnNameLabel);

        c.add(currentTurnPanel);
        updateTurnLabel();
        
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

        buttonGo.setBounds(150, 570, 125, 30);
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

    private void initializeSnakeAndLadderMap() {
        snakeAndLadderMap[5] = 57;
        snakeAndLadderMap[14] = 48;
        snakeAndLadderMap[53] = 72;
        snakeAndLadderMap[63] = 82;

        snakeAndLadderMap[31] = 10;
        snakeAndLadderMap[43] = 19;
        snakeAndLadderMap[74] = 55;
        snakeAndLadderMap[97] = 65;

        for (int i = 1; i <= 100; i++) {
            if (snakeAndLadderMap[i] == 0) {
                snakeAndLadderMap[i] = i;
            }
        }
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

        Tangga.drawTangga(g);
        Ular.drawUlar(g);

        Pion.drawPlayer(g, player1Position, true);
        Pion.drawPlayer(g, player2Position, false);
    }

    private void drawCell(Graphics g, int row, int col, int cellSize, int squareNumber) {
        int x = col * cellSize;
        int y = (10 - 1 - row) * cellSize;

        if ((row + col) % 2 == 0) {
            g.setColor(new Color(154, 205, 49));
        } else {
            g.setColor(new Color(245, 222, 179));
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
        Thread diceRollThread = new Thread(() -> {
            long startTime = System.currentTimeMillis();
            while (System.currentTimeMillis() - startTime < 1000) {
                int newValue = rand.nextInt(6) + 1;
                diceButton.setCurrentDiceValue(newValue);
                repaint();

                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }

            int finalValue = rand.nextInt(6) + 1;
            diceButton.setCurrentDiceValue(finalValue);
            repaint();
            movePlayer(finalValue);
        });

        diceRollThread.start();
    }

    private void movePlayer(int diceValue) {
        new Thread(() -> {
            int currentPosition = isPlayer1Turn ? player1Position : player2Position;
            int targetPosition = currentPosition + diceValue;
    
            if (targetPosition > 100) {
                targetPosition = currentPosition;
            }
    
            for (int i = currentPosition + 1; i <= targetPosition; i++) {
                if (isPlayer1Turn) {
                    player1Position = i;
                } else {
                    player2Position = i;
                }
                repaint();
    
                try {
                    Thread.sleep(500); 
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
    
            targetPosition = snakeAndLadderMap[targetPosition];
            if (isPlayer1Turn) {
                player1Position = targetPosition;
            } else {
                player2Position = targetPosition;
            }
            repaint();
    
            if (targetPosition == 100) {
                String winner = isPlayer1Turn ? player1Name : player2Name;
                showWinMessage(winner);
                resetGame();
                return;
            }
    
            if (diceValue != 6) {
                isPlayer1Turn = !isPlayer1Turn;
            }
            updateTurnLabel();
        }).start();
    }    

    private void showWinMessage(String winner) {
        DatabaseHandler.simpanDataPemain(winner, 1);

        JDialog winDialog = new JDialog(this, "Kemenangan", true);
        winDialog.setLayout(new BorderLayout());

        JLabel winLabel = new JLabel(winner + " menang!", SwingConstants.CENTER);
        winLabel.setFont(new Font("Arial", Font.BOLD, 24));
        winLabel.setForeground(Color.WHITE);
        winLabel.setOpaque(true);
        winLabel.setBackground(new Color(212, 175, 55));
        winDialog.add(winLabel, BorderLayout.CENTER);

        JButton okButton = new JButton("OK");
        okButton.setFont(new Font("Arial", Font.BOLD, 16));
        okButton.setBackground(new Color(154, 205, 49));
        okButton.setForeground(Color.WHITE);
        okButton.addActionListener(e -> {
            winDialog.dispose();
            resetGame();
        });
        winDialog.add(okButton, BorderLayout.SOUTH);

        winDialog.setSize(300, 150);
        winDialog.setLocationRelativeTo(this);
        winDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        winDialog.setVisible(true);
    }    

    private void updateTurnLabel() {
        String currentPlayer = isPlayer1Turn ? player1Name : player2Name;
        turnNameLabel.setText("Giliran: " + currentPlayer);
    
        // Set ikon pion berdasarkan pemain
        ImageIcon playerIcon = new ImageIcon(
            isPlayer1Turn ? "res/Papan/Pion/p1.png" : "res/Papan/Pion/p2.png"
        );
    
        // Resize ikon agar sesuai
        Image resizedIcon = playerIcon.getImage().getScaledInstance(50, 30, Image.SCALE_SMOOTH);
        turnIconLabel.setIcon(new ImageIcon(resizedIcon));
    }    

    public void resetGame() {
        player1Position = 1;
        player2Position = 1;
        isPlayer1Turn = true;
        diceButton.setCurrentDiceValue(1);
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        diceButton.drawDice(g, 575, 81);
    }
}
