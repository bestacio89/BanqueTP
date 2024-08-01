import Diginamic.TP5JPA.BusinessObject.Client;
import Diginamic.TP5JPA.DAO.ClientDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

public class ClientUI {
    private JFrame frame;
    private JTextField txtNom;
    private JTextField txtPrenom;
    private JTextField txtDateNaissance;
    private JTextArea txtClientList;
    private ClientDAO clientDAO;

    public ClientUI() {
        clientDAO = new ClientDAO();
        frame = new JFrame("Client Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        panel.add(new JLabel("Nom:"));
        txtNom = new JTextField();
        panel.add(txtNom);

        panel.add(new JLabel("Prenom:"));
        txtPrenom = new JTextField();
        panel.add(txtPrenom);

        panel.add(new JLabel("Date de Naissance (YYYY-MM-DD):"));
        txtDateNaissance = new JTextField();
        panel.add(txtDateNaissance);

        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveClient();
            }
        });
        panel.add(btnSave);

        JButton btnLoad = new JButton("Load");
        btnLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadClients();
            }
        });
        panel.add(btnLoad);

        txtClientList = new JTextArea();
        txtClientList.setEditable(false);

        frame.add(panel, BorderLayout.NORTH);
        frame.add(new JScrollPane(txtClientList), BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private void saveClient() {
        String nom = txtNom.getText();
        String prenom = txtPrenom.getText();
        LocalDate dateNaissance = LocalDate.parse(txtDateNaissance.getText());

        Client client = new Client();
        client.setNom(nom);
        client.setPrenom(prenom);
        client.setDateNaissance(dateNaissance);

        clientDAO.save(client);
        JOptionPane.showMessageDialog(frame, "Client saved successfully!");
    }

    private void loadClients() {
        List<Client> clients = clientDAO.findAll();
        txtClientList.setText("");
        for (Client client : clients) {
            txtClientList.append(client.getNom() + " " + client.getPrenom() + " " + client.getDateNaissance() + "\n");
        }
    }


}
