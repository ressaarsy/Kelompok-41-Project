package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TampilanMenu extends AbstractTampilanMenu {

    private JFrame frame;

    @Override
    public void initializeFrame() {
        frame = createFrame();
        JLayeredPane layeredPane = new JLayeredPane();
        frame.add(layeredPane);

        layeredPane.add(createImagePanel(), Integer.valueOf(0));
        layeredPane.add(createButtonPanel(), Integer.valueOf(1));

        frame.setVisible(true);
    }

    private JFrame createFrame() {
        JFrame frame = new JFrame("Ular Tangga");
        frame.setSize(650, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return frame;
    }

    private JPanel createImagePanel() {
        JPanel imagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundIcon = new ImageIcon("res/img/BGMenu.png");
                g.drawImage(backgroundIcon.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };
        imagePanel.setBounds(0, 0, 650, 500);
        return imagePanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.setOpaque(false);
        buttonPanel.setBounds(0, 0, 650, 500);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("=SnakeBoard=");
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 36));
        titleLabel.setForeground(new Color(255, 255, 239));

        buttonPanel.add(titleLabel, gbc);

        gbc.gridy = 1;
        buttonPanel.add(createStyledButton("Play", e -> startGame()), gbc);

        gbc.gridy = 2;
        buttonPanel.add(createStyledButton("Score", e -> showScoreMessage()), gbc);

        gbc.gridy = 3;
        buttonPanel.add(createStyledButton("Exit", e -> exitGame()), gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        return buttonPanel;
    }

    private JButton createStyledButton(String label, ActionListener actionListener) {
        JButton button = new JButton(label);
        button.setPreferredSize(new Dimension(200, 40));
        button.setMaximumSize(new Dimension(200, 40));
        button.setBackground(new Color(128, 128, 1));
        button.setForeground(new Color(255, 255, 239));
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.addActionListener(actionListener);
        return button;
    }

    @Override
    public void showPlayMessage() {
        JOptionPane.showMessageDialog(null, "Selamat Bermain!!!");
    }

    private void startGame() {
        String player1Name = JOptionPane.showInputDialog(frame, "Masukkan nama Player 1:", "Input Nama", JOptionPane.PLAIN_MESSAGE);
        String player2Name = JOptionPane.showInputDialog(frame, "Masukkan nama Player 2:", "Input Nama", JOptionPane.PLAIN_MESSAGE);

        if (player1Name == null || player1Name.trim().isEmpty() || player2Name == null || player2Name.trim().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Nama tidak boleh kosong. Silakan coba lagi.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        frame.setVisible(false);
        new UlarTangga(player1Name, player2Name);
    }

    @Override
    public void showScoreMessage() {
        Leaderboard.show(frame);
    }

    @Override
    public void exitGame() {
        JOptionPane.showMessageDialog(null, "Terima Kasih Telah Bermain");
        System.exit(0);
    }

    @Override
    public JButton createButton(JPanel panel, String label, int x, int y, int width, int height, ActionListener actionListener) {
        JButton button = new JButton(label);
        button.setBounds(x, y, width, height);
        button.addActionListener(actionListener);
        panel.add(button);
        return button;
    }

    @Override
    public void addImage(JPanel panel, String imagePath) {
    }

    @Override
    public void callImage() {
        System.out.println("callImage method is called.");
    }
}
