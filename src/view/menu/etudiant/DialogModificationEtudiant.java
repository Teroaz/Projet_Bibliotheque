package view.menu.etudiant;

import controller.GestionEtudiant;
import model.Etudiant;
import utils.swing_utils.JFrameUtils;
import view.FenetreBibliotheque;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DialogModificationEtudiant extends JDialog implements ActionListener, KeyListener {

    private final int width = 400;
    private final int height = 300;

    private JTextField textFieldEmail;
    private JTextField textFieldPrenom;
    private JTextField textFieldNom;
    private JTextField textFieldPassword;

    private JButton boutonOk = new JButton("OK");
    private JButton boutonAnnuler = new JButton("Annuler");

    private final Etudiant etudiant;

    public DialogModificationEtudiant() {
        super(FenetreBibliotheque.getInstance(), "Bibliothèque | Etudiants - Modification d'un étudiant", ModalityType.APPLICATION_MODAL);
        setModal(true);
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        pack();
        setTitle("Modification email étudiant");
        setSize(width, height);
        setLocation(JFrameUtils.centerFrameCoords(width, height));

        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;

        etudiant = GestionEtudiant.getInstance().getTableSelectedEtudiant();

        JLabel labelNom = new JLabel("Nom : ");
        add(labelNom, gbc);

        textFieldNom = new JTextField(15);
        textFieldNom.setText(etudiant.getNom());
        gbc.gridx++;
        add(textFieldNom, gbc);


        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel labelPrenom = new JLabel("Prénom : ");
        add(labelPrenom, gbc);

        textFieldPrenom = new JTextField(15);
        textFieldPrenom.setText(etudiant.getPrenom());
        gbc.gridx++;
        add(textFieldPrenom, gbc);


        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel labelEmail = new JLabel("Email : ");
        add(labelEmail, gbc);

        textFieldEmail = new JTextField(15);
        textFieldEmail.setText(etudiant.getEmail());
        gbc.gridx++;
        add(textFieldEmail, gbc);


        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel labelMdp = new JLabel("Mot de passe : ");
        add(labelMdp, gbc);

        textFieldPassword = new JPasswordField(15);
        textFieldPassword.setText(etudiant.getMdp(true));
        gbc.gridx++;
        add(textFieldPassword, gbc);

        boutonAnnuler.addActionListener(this);
        boutonOk.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy = 4;
        boutonOk.setEnabled(false);
        add(boutonOk, gbc);
        gbc.gridx++;
        add(boutonAnnuler, gbc);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == boutonOk) {
            String email = textFieldEmail.getText();

            boolean rempli;
            boolean mailValide = false;

            GestionEtudiant.getInstance().getTableSelectedEtudiant().setEmail(email);
        }

        setVisible(false);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    @Override
    public Insets getInsets() {
        return new Insets(10, 0, 10, 0);
    }
}
