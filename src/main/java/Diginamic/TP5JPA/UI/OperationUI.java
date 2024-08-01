package Diginamic.TP5JPA.UI;

import Diginamic.TP5JPA.BusinessObject.Operation;
import Diginamic.TP5JPA.DAO.CompteDAO;
import Diginamic.TP5JPA.DAO.OperationDAO;
import Diginamic.TP5JPA.DAO.VirementDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.List;

public class OperationUI {
    private JFrame frame;
    private JTextField txtDate;
    private JTextField txtMontant;
    private JTextField txtMotif;
    private JTextField txtCompteId;
    private JTextField txtVirementId;
    private JTextArea txtOperationList;
    private OperationDAO operationDAO;
    private CompteDAO compteDAO;
    private VirementDAO virementDAO;

    public OperationUI() {
        operationDAO = new OperationDAO();
        compteDAO = new CompteDAO();
        virementDAO = new VirementDAO();

        frame = new JFrame("Operation Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

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

        frame.add(panel, BorderLayout.NORTH);
        frame.add(new JScrollPane(txtOperationList), BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private void saveOperation() {
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
        JOptionPane.showMessageDialog(frame, "Operation saved successfully!");
    }

    private void loadOperations() {
        List<Operation> operations = operationDAO.findAll();
        txtOperationList.setText("");
        for (Operation operation : operations) {
            txtOperationList.append(operation.getDate() + " " + operation.getMontant() + " " + operation.getMotif() + "\n");
        }
    }


}