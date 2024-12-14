package Game;

import DataBase.DatabaseHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;

public class Leaderboard {

    public static void show(JFrame parent) {
        List<String> leaderboard = DatabaseHandler.showLeaderboard();

        if (leaderboard.isEmpty()) {
            JOptionPane.showMessageDialog(parent, "Leaderboard kosong.");
            return;
        }

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
        table.setDefaultRenderer(Object.class, cellRenderer);

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setBackground(new Color(128, 128, 255));
        tableHeader.setForeground(Color.WHITE);
        tableHeader.setFont(new Font("Arial", Font.BOLD, 16));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        JDialog dialog = new JDialog(parent, "Leaderboard", true);
        dialog.setLayout(new BorderLayout());
        dialog.add(scrollPane, BorderLayout.CENTER);

        JButton closeButton = new JButton("Tutup");
        closeButton.addActionListener(e -> dialog.dispose());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.setSize(500, 400);
        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
    }
}
