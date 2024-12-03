package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TampilanMenu extends AbstractTampilanMenu {

    @Override
    public void initializeFrame() {
        JFrame frame = createFrame();
        JLayeredPane layeredPane = new JLayeredPane();
        frame.add(layeredPane);

        // Tambahkan panel gambar latar belakang dan panel tombol
        layeredPane.add(createImagePanel(), Integer.valueOf(0));
        layeredPane.add(createButtonPanel(), Integer.valueOf(1));

        frame.setVisible(true);
    }

    // Membuat JFrame
    private JFrame createFrame() {
        JFrame frame = new JFrame("Ular Tangga");
        frame.setSize(650, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return frame;
    }

    // Membuat panel untuk gambar latar belakang
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

    // Membuat panel untuk tombol
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0)); // Margin antar tombol
        buttonPanel.setBounds(225, 150, 200, 200); // Posisi tetap

        // Tambahkan tombol
        buttonPanel.add(createStyledButton("Play", e -> showPlayMessage()));
        buttonPanel.add(Box.createVerticalStrut(20)); // Spasi antar tombol
        buttonPanel.add(createStyledButton("Score", e -> showScoreMessage()));
        buttonPanel.add(Box.createVerticalStrut(20)); // Spasi antar tombol
        buttonPanel.add(createStyledButton("Exit", e -> exitGame()));

        return buttonPanel;
    }

    // Membuat tombol dengan gaya tertentu
    private JButton createStyledButton(String label, ActionListener actionListener) {
        JButton button = new JButton(label);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setPreferredSize(new Dimension(200, 40));
        button.setMaximumSize(new Dimension(200, 40));
        button.setBackground(new Color(128, 128, 1)); // Warna hijau
        button.setForeground(Color.BLACK); // Warna teks hitam
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.addActionListener(actionListener);
        return button;
    }

    @Override
    public void showPlayMessage() {
        JOptionPane.showMessageDialog(null, "Selamat Bermain!!!");
    }

    @Override
    public void showScoreMessage() {
        JOptionPane.showMessageDialog(null, "Fitur Skor Belum Ada");
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
        // Tidak digunakan lagi karena gambar latar belakang dibuat di createImagePanel
    }
}
