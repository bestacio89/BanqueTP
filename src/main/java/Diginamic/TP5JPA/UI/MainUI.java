package Diginamic.TP5JPA.UI;

import Diginamic.TP5JPA.Utilities.DatabaseUtil;

import javax.swing.*;
import java.awt.*;

public class MainUI {
    private JFrame frame;
    private JTabbedPane tabbedPane;

    public MainUI() {
        // Initialize DatabaseUtil
        try {
            DatabaseUtil.getEntityManager(); // Ensure the EntityManager is initialized
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error initializing database: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1); // Exit the application if the database can't be initialized
        }

        frame = new JFrame("Main Management UI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        tabbedPane = new JTabbedPane();

        // Create and add tabs for each entity
        tabbedPane.addTab("Client", new ClientUI());
        tabbedPane.addTab("Banque", new BanqueUI());
        tabbedPane.addTab("Compte", new CompteUI());
        tabbedPane.addTab("Operation", new OperationUI()); // Update to use OperationUI panel

        frame.add(tabbedPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainUI());
    }
}
