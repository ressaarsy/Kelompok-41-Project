package Game;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import DataBase.DatabaseHandler; // ditambahkan untuk mengambil data leaderboard dari basis data

import java.awt.*;
import java.awt.event.ActionListener;

public class TampilanMenu extends AbstractTampilanMenu {

    @Override
    public void initializeFrame() {
        JFrame frame = createFrame();
        JLayeredPane layeredPane = new JLayeredPane();
        frame.add(layeredPane);

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
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));
        buttonPanel.setBounds(225, 150, 200, 200); 

        // Tambahkan tombol
        buttonPanel.add(createStyledButton("Play", e -> showPlayMessage()));
        buttonPanel.add(Box.createVerticalStrut(20)); 
        buttonPanel.add(createStyledButton("Score", e -> showScoreMessage()));
        buttonPanel.add(Box.createVerticalStrut(20)); 
        buttonPanel.add(createStyledButton("Exit", e -> exitGame()));

        return buttonPanel;
    }

    private JButton createStyledButton(String label, ActionListener actionListener) {
        JButton button = new JButton(label);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setPreferredSize(new Dimension(200, 40));
        button.setMaximumSize(new Dimension(200, 40));
        button.setBackground(new Color(128, 128, 1)); 
        button.setForeground(Color.BLACK);
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
    public void showScoreMessage() { // ditambahkan menampilkan papan skor atau leaderboard 
        java.util.List<String> leaderboard = DatabaseHandler.showLeaderboard();

        if (leaderboard.isEmpty()) {
            JOptionPane.showMessageDialog(null, "leaderboard kosong.");
        } else {
            String[] columnNames = {"Nama", "Jumlah Menang"};
            String[][] tableData = new String[leaderboard.size()][2];

            for (int i = 0; i < leaderboard.size(); i++) {
                String[] entry = leaderboard.get(i).split(": ");
                tableData[i][0] = entry[0].trim();
                tableData[i][1] = entry[1].replace(" wins", "").trim();
            }

            JTable table = new JTable(tableData, columnNames);
            table.setFont(new Font("Arial", Font.PLAIN, 14));
            table.setRowHeight(30);
            table.setFillsViewportHeight(true);

            DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
            cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(
                        JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    if (row % 2 == 0) {
                        cell.setBackground(new Color(240, 248, 255));
                    } else {
                        cell.setBackground(new Color(255, 248, 220));
                    }
                    cell.setForeground(Color.BLACK);
                    ((DefaultTableCellRenderer) cell).setHorizontalAlignment(SwingConstants.CENTER);
                    return cell;
                }
            });

            JTableHeader tableHeader = table.getTableHeader();
            tableHeader.setBackground(new Color(128, 128, 255));
            tableHeader.setForeground(Color.WHITE);
            tableHeader.setFont(new Font("Arial", Font.BOLD, 16));

            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setPreferredSize(new Dimension(400, 300));

            JOptionPane.showMessageDialog(null, scrollPane, "leaderboard", JOptionPane.INFORMATION_MESSAGE);;
        }
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
}