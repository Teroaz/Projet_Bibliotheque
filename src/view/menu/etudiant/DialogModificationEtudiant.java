package view.menu.etudiant;

import controller.GestionEtudiant;
import controller.PanelSwitcher;
import model.Etudiant;
import model.design.Couleurs;
import utils.CollectionUtils;
import utils.ValidationUtils;
import utils.swing_utils.ColumnsAutoSizer;
import utils.swing_utils.JFrameUtils;
import view.FenetreBibliotheque;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class DialogModificationEtudiant extends JDialog implements ActionListener, KeyListener {

    private final int width = 400;
    private final int height = 300;

    private JTextField textFieldEmail;
    private JTextField textFieldPrenom;
    private JTextField textFieldNom;
    private JPasswordField textFieldPassword;

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
        textFieldNom.addKeyListener(this);
        gbc.gridx++;
        add(textFieldNom, gbc);


        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel labelPrenom = new JLabel("Prénom : ");
        add(labelPrenom, gbc);

        textFieldPrenom = new JTextField(15);
        textFieldPrenom.setText(etudiant.getPrenom());
        textFieldPrenom.addKeyListener(this);
        gbc.gridx++;
        add(textFieldPrenom, gbc);


        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel labelEmail = new JLabel("Email : ");
        add(labelEmail, gbc);

        textFieldEmail = new JTextField(15);
        textFieldEmail.setText(etudiant.getEmail());
        textFieldEmail.addKeyListener(this);
        gbc.gridx++;
        add(textFieldEmail, gbc);


        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel labelMdp = new JLabel("Mot de passe : ");
        add(labelMdp, gbc);

        textFieldPassword = new JPasswordField(15);
        textFieldPassword.setText(etudiant.getMdp(true));
        textFieldPassword.addKeyListener(this);
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
            String prenom = textFieldPrenom.getText().trim();
            String nom = textFieldNom.getText().trim();
            String mail = textFieldEmail.getText().trim();
            String mdp = String.valueOf(textFieldPassword.getPassword());

            if (!prenom.equals(etudiant.getPrenom())) {
                etudiant.setPrenom(prenom);
            }
            if (!nom.equals(etudiant.getNom())) {
                etudiant.setNom(nom);
            }
            if (!mail.equals(etudiant.getEmail())) {
                etudiant.setEmail(mail);
            }
            if (!mdp.equals(etudiant.getMdp(true))) {
                etudiant.setMdp(mdp);
            }

            PanelEtudiant panelEtudiant = PanelSwitcher.getMenu().getGestionEtudiant().getPanelEtudiant();
            panelEtudiant.getTableEtudiant().getModeleEtudiant().updateEtudiant(Etudiant.liste.values());
            ColumnsAutoSizer.sizeColumnsToFit(panelEtudiant.getTableEtudiant());
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
        String prenom = textFieldPrenom.getText().trim();
        String nom = textFieldNom.getText().trim();
        String mail = textFieldEmail.getText().trim();
        String mdp = String.valueOf(textFieldPassword.getPassword());

        ArrayList<Etudiant> etudiantsWithSameMail = Etudiant.searchByMail(mail.trim());
        boolean validMail;

        if (etudiantsWithSameMail == null) {
            validMail = false;
        } else {
            ArrayList<Etudiant> othersEtudiantsWithSameMail = CollectionUtils.streamToArrayList(etudiantsWithSameMail.stream().filter(it -> it.getId() != etudiant.getId()));
            validMail = ValidationUtils.isValidMail(mail.trim()) && (othersEtudiantsWithSameMail.size() == 0);
        }

        textFieldEmail.setForeground(!validMail ? Couleurs.ROUGE.getCouleur() : null);

        boolean noneEmptyField = !prenom.isEmpty() && !nom.isEmpty() && !mail.isEmpty() && !mdp.isEmpty();

        boolean informationModif = !prenom.equals(etudiant.getPrenom()) || !nom.equals(etudiant.getNom()) || !mail.equals(etudiant.getEmail()) || !mdp.equals(etudiant.getMdp(true));

        boutonOk.setEnabled(validMail && noneEmptyField && informationModif);
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    @Override
    public Insets getInsets() {
        return new Insets(10, 0, 10, 0);
    }
}
