package Diginamic.TP5JPA.UI;

import Diginamic.TP5JPA.BusinessObject.Compte;
import Diginamic.TP5JPA.DAO.BanqueDAO;
import Diginamic.TP5JPA.DAO.CompteDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CompteUI {
    private JFrame frame;
    private JTextField txtNumero;
    private JTextField txtSolde;
    private JTextField txtBanqueId;
    private JTextArea txtCompteList;
    private CompteDAO compteDAO;
    private BanqueDAO banqueDAO;

    public CompteUI() {
        compteDAO = new CompteDAO();
        frame = new JFrame("Compte Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        panel.add(new JLabel("Numero:"));
        txtNumero = new JTextField();
        panel.add(txtNumero);

        panel.add(new JLabel("Solde:"));
        txtSolde = new JTextField();
        panel.add(txtSolde);

        panel.add(new JLabel("Banque ID:"));
        txtBanqueId = new JTextField();
        panel.add(txtBanqueId);

        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveCompte();
            }
        });
        panel.add(btnSave);

        JButton btnLoad = new JButton("Load");
        btnLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadComptes();
            }
        });
        panel.add(btnLoad);

        txtCompteList = new JTextArea();
        txtCompteList.setEditable(false);

        frame.add(panel, BorderLayout.NORTH);
        frame.add(new JScrollPane(txtCompteList), BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private void saveCompte() {
        String numero = txtNumero.getText();
        double solde = Double.parseDouble(txtSolde.getText());
        Long banqueId = Long.parseLong(txtBanqueId.getText());

        Compte compte = new Compte();
        compte.setNumero(numero);
        compte.setSolde(solde);
        compte.setBanque(banqueDAO.findById(banqueId));

        compteDAO.save(compte);
        JOptionPane.showMessageDialog(frame, "Compte saved successfully!");
    }

    private void loadComptes() {
        List<Compte> comptes = compteDAO.findAll();
        txtCompteList.setText("");
        for (Compte compte : comptes) {
            txtCompteList.append(compte.getNumero() + " " + compte.getSolde() + " " + compte.getBanque().getNom() + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CompteUI());
    }
}
