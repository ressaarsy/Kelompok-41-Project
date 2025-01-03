package Game;

import javax.swing.*;
import java.awt.*;

public class TombolPause {

    private JFrame parentFrame;

    public TombolPause(JFrame parentFrame) {
        this.parentFrame = parentFrame;
    }

    public void showPauseMenu() {
        JDialog pauseDialog = new JDialog(parentFrame, "Pause Menu", true);
        pauseDialog.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        pauseDialog.setSize(300, 300);
        pauseDialog.setLocationRelativeTo(parentFrame);

        JButton resumeButton = new JButton("Resume");
        JButton restartButton = new JButton("Restart");
        JButton exitButton = new JButton("Exit");
        JButton helpButton = new JButton("Cara Bermain");

        designButton(resumeButton);
        designButton(restartButton);
        designButton(helpButton);
        designButton(exitButton);

        resumeButton.addActionListener(e -> pauseDialog.setVisible(false));

        restartButton.addActionListener(e -> {
            resetGame();
            JOptionPane.showMessageDialog(parentFrame, "Permainan dimulai ulang!");
            pauseDialog.setVisible(false);
        });

        exitButton.addActionListener(e -> exitGame());

        helpButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(parentFrame,
                "Cara Bermain Ular Tangga:\n\n" +
                "1. Dua pemain bergantian melempar dadu dan bergerak sesuai angka.\n" +
                "2. Jika mendarat di tangga, naik. Jika di ular, turun.\n" +
                "3. Pemain pertama yang mencapai kotak 100 menang.\n" +
                "4. Jika angka dadu lebih dari langkah ke 100, giliran dilewati.\n" +
                "5. Jika angka dadu 6, pemain mendapat giliran tambahan.\n\n" +
                "Selamat bermain dan semoga beruntung!");
        });             

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 10, 10);
        pauseDialog.add(resumeButton, constraints);

        constraints.gridy = 1;
        pauseDialog.add(restartButton, constraints);

        constraints.gridy = 2;
        pauseDialog.add(helpButton, constraints);

        constraints.gridy = 3;
        pauseDialog.add(exitButton, constraints);

        pauseDialog.setVisible(true);
    }

    private void designButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(200, 40));
        button.setBackground(new Color(154, 205, 49));
        button.setForeground(Color.WHITE);
    }

    private void resetGame() {
        if (parentFrame instanceof UlarTangga) {
            UlarTangga gameFrame = (UlarTangga) parentFrame;
            gameFrame.resetGame();
        } else {
            JOptionPane.showMessageDialog(parentFrame, "Fitur reset permainan tidak tersedia.");
        }
    }

    private void exitGame() {
        int confirm = JOptionPane.showConfirmDialog(parentFrame, "Apakah Anda yakin ingin keluar?", "Konfirmasi Keluar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            parentFrame.setVisible(false);
            new TampilanMenu().initializeFrame();
        }
    }
}
