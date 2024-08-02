package Diginamic.TP5JPA.UI.Comptes;

import Diginamic.TP5JPA.BusinessObject.*;
import Diginamic.TP5JPA.BusinessObject.Comptes.AssuranceVie;
import Diginamic.TP5JPA.BusinessObject.Comptes.Compte;
import Diginamic.TP5JPA.BusinessObject.Comptes.LivretA;
import Diginamic.TP5JPA.BusinessObject.ValueObject.Adresse;
import Diginamic.TP5JPA.DAO.Specific.Comptes.CompteDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CompteUI extends JPanel {
    private JTextField numeroField;
    private JTextField soldeField;
    private JTextField livretAInterestRateField;
    private JTextField assuranceVieEndDateField;
    private JTextField numeroRueField;
    private JTextField rueField;
    private JTextField codePostalField;
    private JTextField villeField;
    private JTextField banqueNameField;
    private JTextField clientsField;
    private JTextArea txtCompteList;
    private CompteDAO compteDAO;

    public CompteUI() {
        compteDAO = new CompteDAO();
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(11, 2));

        panel.add(new JLabel("Numero:"));
        numeroField = new JTextField();
        panel.add(numeroField);

        panel.add(new JLabel("Solde:"));
        soldeField = new JTextField();
        panel.add(soldeField);

        panel.add(new JLabel("LivretA Interest Rate:"));
        livretAInterestRateField = new JTextField();
        panel.add(livretAInterestRateField);

        panel.add(new JLabel("AssuranceVie End Date (YYYY-MM-DD):"));
        assuranceVieEndDateField = new JTextField();
        panel.add(assuranceVieEndDateField);

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

        panel.add(new JLabel("Banque Name:"));
        banqueNameField = new JTextField();
        panel.add(banqueNameField);

        panel.add(new JLabel("Clients (comma-separated IDs):"));
        clientsField = new JTextField();
        panel.add(clientsField);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveCompte();
            }
        });
        panel.add(saveButton);

        txtCompteList = new JTextArea();
        txtCompteList.setEditable(false);

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(txtCompteList), BorderLayout.CENTER);

        loadComptes();
    }

    private void saveCompte() {
        String numero = numeroField.getText();
        double solde = Double.parseDouble(soldeField.getText());
        double livretAInterestRate = Double.parseDouble(livretAInterestRateField.getText());
        LocalDate assuranceVieEndDate = LocalDate.parse(assuranceVieEndDateField.getText());
        int numeroRue = Integer.parseInt(numeroRueField.getText());
        String rue = rueField.getText();
        int codePostal = Integer.parseInt(codePostalField.getText());
        String ville = villeField.getText();
        String banqueName = banqueNameField.getText();

        LivretA livretA = new LivretA();
        livretA.setTaux(livretAInterestRate);

        AssuranceVie assuranceVie = new AssuranceVie();
        assuranceVie.setDateFin(assuranceVieEndDate);

        Adresse adresse = new Adresse(numeroRue, rue, codePostal, ville);

        Banque banque = new Banque();
        banque.setNom(banqueName);

        List<Client> clients = new ArrayList<>();
        String[] clientIds = clientsField.getText().split(",");
        for (String id : clientIds) {
            Long clientId = Long.parseLong(id.trim());
            Client client = findClientById(clientId); // Implement this method to fetch Client from the database
            if (client != null) {
                clients.add(client);
            }
        }

        Compte compte = new Compte();
        compte.setNumero(numero);
        compte.setSolde(solde);
        compte.setLivretA(livretA);
        compte.setAssuranceVie(assuranceVie);
        compte.setAdresse(adresse);
        compte.setBanque(banque);
        compte.setClients(clients);

        compteDAO.save(compte); // Implement this method to save Comptes to the database
        JOptionPane.showMessageDialog(this, "Comptes saved successfully!");
        loadComptes(); // Refresh list after saving
    }

    private Client findClientById(Long clientId) {
        // Implement database lookup for Client by ID
        return null; // Placeholder
    }

    private void loadComptes() {
        List<Compte> comptes = compteDAO.findAll();
        txtCompteList.setText("");
        for (Compte compte : comptes) {
            txtCompteList.append(compte.getNumero() + " " + compte.getSolde() + "\n");
        }
    }
}
