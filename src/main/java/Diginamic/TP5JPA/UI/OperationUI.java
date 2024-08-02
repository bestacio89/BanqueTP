package Diginamic.TP5JPA.UI;

import Diginamic.TP5JPA.BusinessObject.Operation;
import Diginamic.TP5JPA.DAO.Specific.CompteDAO;
import Diginamic.TP5JPA.DAO.Specific.OperationDAO;
import Diginamic.TP5JPA.DAO.Specific.VirementDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.List;

public class OperationUI extends JPanel {
    private JTextField txtDate;
    private JTextField txtMontant;
    private JTextField txtMotif;
    private JTextField txtCompteId;
    private JTextField txtVirementId;
    private JTextField txtSearchAccountId;
    private JTextArea txtOperationList;
    private OperationDAO operationDAO;
    private CompteDAO compteDAO;
    private VirementDAO virementDAO;

    public OperationUI() {
        operationDAO = new OperationDAO();
        compteDAO = new CompteDAO();
        virementDAO = new VirementDAO();

        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

        panel.add(new JLabel("Date (YYYY-MM-DD HH:MM:SS):"));
        txtDate = new JTextField();
        panel.add(txtDate);

        panel.add(new JLabel("Montant:"));
        txtMontant = new JTextField();
        panel.add(txtMontant);

        panel.add(new JLabel("Motif:"));
        txtMotif = new JTextField();
        panel.add(txtMotif);

        panel.add(new JLabel("Compte ID:"));
        txtCompteId = new JTextField();
        panel.add(txtCompteId);

        panel.add(new JLabel("Virement ID:"));
        txtVirementId = new JTextField();
        panel.add(txtVirementId);

        panel.add(new JLabel("Search Account ID:"));
        txtSearchAccountId = new JTextField();
        panel.add(txtSearchAccountId);

        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveOperation();
            }
        });
        panel.add(btnSave);

        JButton btnLoad = new JButton("Load");
        btnLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadOperations();
            }
        });
        panel.add(btnLoad);

        txtOperationList = new JTextArea();
        txtOperationList.setEditable(false);

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(txtOperationList), BorderLayout.CENTER);
    }

    private void saveOperation() {
        try {
            LocalDateTime date = LocalDateTime.parse(txtDate.getText());
            double montant = Double.parseDouble(txtMontant.getText());
            String motif = txtMotif.getText();
            Long compteId = Long.parseLong(txtCompteId.getText());
            Long virementId = Long.parseLong(txtVirementId.getText());

            Operation operation = new Operation();
            operation.setDate(date);
            operation.setMontant(montant);
            operation.setMotif(motif);
            operation.setCompte(compteDAO.findById(compteId));
            operation.setVirement(virementDAO.findById(virementId));

            operationDAO.save(operation);
            JOptionPane.showMessageDialog(this, "Operation saved successfully!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error saving operation: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadOperations() {
        try {
            Long accountId = Long.parseLong(txtSearchAccountId.getText());
            List<Operation> operations = operationDAO.findByAccountId(accountId);
            txtOperationList.setText("");
            for (Operation operation : operations) {
                txtOperationList.append(operation.getDate() + " " + operation.getMontant() + " " + operation.getMotif() + "\n");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading operations: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
