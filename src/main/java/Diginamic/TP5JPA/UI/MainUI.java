package Diginamic.TP5JPA.UI;

import Diginamic.TP5JPA.BusinessObject.Banque;
import Diginamic.TP5JPA.BusinessObject.Client;
import Diginamic.TP5JPA.BusinessObject.Compte;
import Diginamic.TP5JPA.BusinessObject.Operation;
import Diginamic.TP5JPA.DAO.*;
import Diginamic.TP5JPA.Utilities.DatabaseUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
        tabbedPane.addTab("Client", createClientPanel());
        tabbedPane.addTab("Banque", createBanquePanel());
        tabbedPane.addTab("Compte", createComptePanel());
        tabbedPane.addTab("Operation", createOperationPanel());

        frame.add(tabbedPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private JPanel createClientPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JTextField txtNom = new JTextField();
        JTextField txtPrenom = new JTextField();
        JTextField txtDateNaissance = new JTextField();
        JTextArea txtClientList = new JTextArea();
        txtClientList.setEditable(false);

        ClientDAO clientDAO = new ClientDAO();

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2));

        inputPanel.add(new JLabel("Nom:"));
        inputPanel.add(txtNom);
        inputPanel.add(new JLabel("Prenom:"));
        inputPanel.add(txtPrenom);
        inputPanel.add(new JLabel("Date de Naissance (YYYY-MM-DD):"));
        inputPanel.add(txtDateNaissance);

        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(e -> {
            try {
                saveClient(txtNom.getText(), txtPrenom.getText(), txtDateNaissance.getText(), clientDAO);
                loadClients(txtClientList, clientDAO);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error saving client: " + ex.getMessage(), "Save Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton btnLoad = new JButton("Load");
        btnLoad.addActionListener(e -> {
            try {
                loadClients(txtClientList, clientDAO);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error loading clients: " + ex.getMessage(), "Load Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnSave);
        buttonPanel.add(btnLoad);

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(txtClientList), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createBanquePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JTextField txtNom = new JTextField();
        JTextArea txtBanqueList = new JTextArea();
        txtBanqueList.setEditable(false);

        BanqueDAO banqueDAO = new BanqueDAO();

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2));

        inputPanel.add(new JLabel("Nom:"));
        inputPanel.add(txtNom);

        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(e -> {
            try {
                saveBanque(txtNom.getText(), banqueDAO);
                loadBanques(txtBanqueList, banqueDAO);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error saving banque: " + ex.getMessage(), "Save Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton btnLoad = new JButton("Load");
        btnLoad.addActionListener(e -> {
            try {
                loadBanques(txtBanqueList, banqueDAO);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error loading banques: " + ex.getMessage(), "Load Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnSave);
        buttonPanel.add(btnLoad);

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(txtBanqueList), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createComptePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JTextField txtNumero = new JTextField();
        JTextField txtSolde = new JTextField();
        JTextField txtBanqueId = new JTextField();
        JTextArea txtCompteList = new JTextArea();
        txtCompteList.setEditable(false);

        CompteDAO compteDAO = new CompteDAO();
        BanqueDAO banqueDAO = new BanqueDAO();

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2));

        inputPanel.add(new JLabel("Numero:"));
        inputPanel.add(txtNumero);
        inputPanel.add(new JLabel("Solde:"));
        inputPanel.add(txtSolde);
        inputPanel.add(new JLabel("Banque ID:"));
        inputPanel.add(txtBanqueId);

        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(e -> {
            try {
                saveCompte(txtNumero.getText(), Double.parseDouble(txtSolde.getText()), Long.parseLong(txtBanqueId.getText()), compteDAO, banqueDAO);
                loadComptes(txtCompteList, compteDAO);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error saving compte: " + ex.getMessage(), "Save Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton btnLoad = new JButton("Load");
        btnLoad.addActionListener(e -> {
            try {
                loadComptes(txtCompteList, compteDAO);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error loading comptes: " + ex.getMessage(), "Load Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnSave);
        buttonPanel.add(btnLoad);

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(txtCompteList), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createOperationPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JTextField txtDate = new JTextField();
        JTextField txtMontant = new JTextField();
        JTextField txtMotif = new JTextField();
        JTextField txtCompteId = new JTextField();
        JTextField txtVirementId = new JTextField();
        JTextArea txtOperationList = new JTextArea();
        txtOperationList.setEditable(false);

        OperationDAO operationDAO = new OperationDAO();
        CompteDAO compteDAO = new CompteDAO();
        VirementDAO virementDAO = new VirementDAO();

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2));

        inputPanel.add(new JLabel("Date (YYYY-MM-DD HH:MM:SS):"));
        inputPanel.add(txtDate);
        inputPanel.add(new JLabel("Montant:"));
        inputPanel.add(txtMontant);
        inputPanel.add(new JLabel("Motif:"));
        inputPanel.add(txtMotif);
        inputPanel.add(new JLabel("Compte ID:"));
        inputPanel.add(txtCompteId);
        inputPanel.add(new JLabel("Virement ID:"));
        inputPanel.add(txtVirementId);

        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(e -> {
            try {
                saveOperation(txtDate.getText(), Double.parseDouble(txtMontant.getText()), txtMotif.getText(), Long.parseLong(txtCompteId.getText()), Long.parseLong(txtVirementId.getText()), operationDAO, compteDAO, virementDAO);
                loadOperations(txtOperationList, operationDAO);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error saving operation: " + ex.getMessage(), "Save Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton btnLoad = new JButton("Load");
        btnLoad.addActionListener(e -> {
            try {
                loadOperations(txtOperationList, operationDAO);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error loading operations: " + ex.getMessage(), "Load Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnSave);
        buttonPanel.add(btnLoad);

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(txtOperationList), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    // Helper methods for saving and loading data
    private void saveClient(String nom, String prenom, String dateNaissance, ClientDAO clientDAO) {
        Client client = new Client();
        client.setNom(nom);
        client.setPrenom(prenom);
        client.setDateNaissance(LocalDate.parse(dateNaissance));
        clientDAO.save(client);
    }

    private void loadClients(JTextArea txtClientList, ClientDAO clientDAO) {
        txtClientList.setText("");
        for (Client client : clientDAO.findAll()) {
            txtClientList.append(client.getNom() + " " + client.getPrenom() + " " + client.getDateNaissance() + "\n");
        }
    }

    private void saveBanque(String nom, BanqueDAO banqueDAO) {
        Banque banque = new Banque();
        banque.setNom(nom);
        banqueDAO.save(banque);
    }

    private void loadBanques(JTextArea txtBanqueList, BanqueDAO banqueDAO) {
        txtBanqueList.setText("");
        for (Banque banque : banqueDAO.findAll()) {
            txtBanqueList.append(banque.getNom() + "\n");
        }
    }

    private void saveCompte(String numero, double solde, Long banqueId, CompteDAO compteDAO, BanqueDAO banqueDAO) {
        Compte compte = new Compte();
        compte.setNumero(numero);
        compte.setSolde(solde);
        compte.setBanque(banqueDAO.findById(banqueId));
        compteDAO.save(compte);
    }

    private void loadComptes(JTextArea txtCompteList, CompteDAO compteDAO) {
        txtCompteList.setText("");
        for (Compte compte : compteDAO.findAll()) {
            txtCompteList.append(compte.getNumero() + " " + compte.getSolde() + " " + compte.getBanque().getNom() + "\n");
        }
    }

    private void saveOperation(String date, double montant, String motif, Long compteId, Long virementId, OperationDAO operationDAO, CompteDAO compteDAO, VirementDAO virementDAO) {
        Operation operation = new Operation();
        operation.setDate(LocalDateTime.parse(date));
        operation.setMontant(montant);
        operation.setMotif(motif);
        operation.setCompte(compteDAO.findById(compteId));
        operation.setVirement(virementDAO.findById(virementId));
        operationDAO.save(operation);
    }

    private void loadOperations(JTextArea txtOperationList, OperationDAO operationDAO) {
        txtOperationList.setText("");
        for (Operation operation : operationDAO.findAll()) {
            txtOperationList.append(operation.getDate() + " " + operation.getMontant() + " " + operation.getMotif() + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainUI());
    }
}
