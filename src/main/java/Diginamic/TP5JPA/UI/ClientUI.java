package Diginamic.TP5JPA.UI;

import Diginamic.TP5JPA.BusinessObject.*;
import Diginamic.TP5JPA.DAO.ClientDAO;
import Diginamic.TP5JPA.DAO.CompteDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClientUI extends JPanel {
    private JTextField nomField;
    private JTextField prenomField;
    private JTextField dateNaissanceField;
    private JTextField numeroRueField;
    private JTextField rueField;
    private JTextField codePostalField;
    private JTextField villeField;
    private JTextField comptesField;
    private JTextArea txtClientList;
    private ClientDAO clientDAO;
    private CompteDAO compteDAO;

    public ClientUI() {
        clientDAO = new ClientDAO();
        compteDAO = new CompteDAO();
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(9, 2));

        panel.add(new JLabel("Nom:"));
        nomField = new JTextField();
        panel.add(nomField);

        panel.add(new JLabel("Prenom:"));
        prenomField = new JTextField();
        panel.add(prenomField);

        panel.add(new JLabel("Date Naissance (YYYY-MM-DD):"));
        dateNaissanceField = new JTextField();
        panel.add(dateNaissanceField);

        panel.add(new JLabel("Numero Rue:"));
        numeroRueField = new JTextField();
        panel.add(numeroRueField);

        panel.add(new JLabel("Rue:"));
        rueField = new JTextField();
        panel.add(rueField);

        panel.add(new JLabel("Code Postal:"));
        codePostalField = new JTextField();
        panel.add(codePostalField);

        panel.add(new JLabel("Ville:"));
        villeField = new JTextField();
        panel.add(villeField);

        panel.add(new JLabel("Comptes (comma-separated IDs):"));
        comptesField = new JTextField();
        panel.add(comptesField);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveClient();
            }
        });
        panel.add(saveButton);

        txtClientList = new JTextArea();
        txtClientList.setEditable(false);

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(txtClientList), BorderLayout.CENTER);

        loadClients();
    }

    private void saveClient() {
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        LocalDate dateNaissance = LocalDate.parse(dateNaissanceField.getText());
        int numeroRue = Integer.parseInt(numeroRueField.getText());
        String rue = rueField.getText();
        int codePostal = Integer.parseInt(codePostalField.getText());
        String ville = villeField.getText();
        Adresse adresse = new Adresse(numeroRue, rue, codePostal, ville);

        List<Compte> comptes = new ArrayList<>();
        String[] compteIds = comptesField.getText().split(",");
        for (String id : compteIds) {
            Long compteId = Long.parseLong(id.trim());
            Compte compte = findCompteById(compteId); // Implement this method to fetch Compte from the database
            if (compte != null) {
                comptes.add(compte);
            }
        }

        Client client = new Client();
        client.setNom(nom);
        client.setPrenom(prenom);
        client.setDateNaissance(dateNaissance);
        client.setAdresse(adresse);
        client.setComptes(comptes);

        clientDAO.save(client); // Implement this method to save Client to the database
        JOptionPane.showMessageDialog(this, "Client saved successfully!");
        loadClients(); // Refresh list after saving
    }

    private Compte findCompteById(Long compteId) {
        // Implement database lookup for Compte by ID
        return compteDAO.findById(compteId);
    }

    private void loadClients() {
        List<Client> clients = clientDAO.findAll();
        txtClientList.setText("");
        for (Client client : clients) {
            txtClientList.append(client.getNom() + " " + client.getPrenom() + "\n");
        }
    }
}
