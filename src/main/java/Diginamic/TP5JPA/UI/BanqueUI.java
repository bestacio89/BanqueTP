package Diginamic.TP5JPA.UI;

import Diginamic.TP5JPA.BusinessObject.Banque;
import Diginamic.TP5JPA.DAO.BanqueDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BanqueUI {
    private JFrame frame;
    private JTextField txtNom;
    private JTextArea txtBanqueList;
    private BanqueDAO banqueDAO;

    public BanqueUI() {
        banqueDAO = new BanqueDAO();
        frame = new JFrame("Banque Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));

        panel.add(new JLabel("Nom:"));
        txtNom = new JTextField();
        panel.add(txtNom);

        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveBanque();
            }
        });
        panel.add(btnSave);

        JButton btnLoad = new JButton("Load");
        btnLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadBanques();
            }
        });
        panel.add(btnLoad);

        txtBanqueList = new JTextArea();
        txtBanqueList.setEditable(false);

        frame.add(panel, BorderLayout.NORTH);
        frame.add(new JScrollPane(txtBanqueList), BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private void saveBanque() {
        String nom = txtNom.getText();

        Banque banque = new Banque();
        banque.setNom(nom);

        banqueDAO.save(banque);
        JOptionPane.showMessageDialog(frame, "Banque saved successfully!");
    }

    private void loadBanques() {
        List<Banque> banques = banqueDAO.findAll();
        txtBanqueList.setText("");
        for (Banque banque : banques) {
            txtBanqueList.append(banque.getNom() + "\n");
        }
    }
}
