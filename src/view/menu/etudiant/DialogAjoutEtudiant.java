package view.menu.etudiant;

import controller.PanelSwitcher;
import model.Etudiant;
import model.design.Couleurs;
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

public class DialogAjoutEtudiant extends JDialog implements ActionListener, KeyListener {

    private final int width = 400;
    private final int height = 400;

    JButton boutonOk = new JButton("OK");
    JButton boutonAnnuler = new JButton("Annuler");

    JTextField texteId = new JTextField(15);
    JTextField texteNom = new JTextField(15);
    JTextField textePrenom = new JTextField(15);
    JTextField texteEmail = new JTextField(15);
    JPasswordField texteMdp = new JPasswordField(15);

    JLabel labelProbleme = new JLabel("Problème : ", JLabel.CENTER);
    JLabel labelMessage = new JLabel(" ", JLabel.CENTER);

    JPanel panel = new JPanel();

    public DialogAjoutEtudiant() {
        super(FenetreBibliotheque.getInstance(), "Bibliothèque | Etudiants - Ajout d'un étudiant", ModalityType.APPLICATION_MODAL);
        setModal(true);

        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        pack();
        setSize(width, height);
        setLocation(JFrameUtils.centerFrameCoords(width, height));

        panel.setLayout(new GridLayout(7, 2, 10, 20));

        JLabel labelId = new JLabel("ID Étudiant", JLabel.CENTER);
        JLabel labelNom = new JLabel("Nom", JLabel.CENTER);
        JLabel labelPrenom = new JLabel("Prénom", JLabel.CENTER);
        JLabel labelEmail = new JLabel("Email", JLabel.CENTER);
        JLabel labelMdp = new JLabel("Mot de passe", JLabel.CENTER);
        labelProbleme.setVisible(false);
        labelMessage.setVisible(false);
        boutonOk.setEnabled(false);

        boutonAnnuler.addActionListener(this);
        boutonOk.addActionListener(this);
        texteEmail.addKeyListener(this);
        texteId.addKeyListener(this);
        texteMdp.addKeyListener(this);
        textePrenom.addKeyListener(this);
        texteNom.addKeyListener(this);

        texteId.setText((Etudiant.getCurrentDatabaseID() + 1) + "");

        texteId.setEnabled(false);

        panel.add(labelId);
        panel.add(texteId);
        panel.add(labelNom);
        panel.add(texteNom);
        panel.add(labelPrenom);
        panel.add(textePrenom);
        panel.add(labelEmail);
        panel.add(texteEmail);
        panel.add(labelMdp);
        panel.add(texteMdp);
        panel.add(labelProbleme);
        panel.add(labelMessage);
        panel.add(boutonOk);
        panel.add(boutonAnnuler);

        add(panel);
        setContentPane(panel);
        setVisible(true);

        setBackground(Couleurs.BLEU_CLAIR.getCouleur());
        panel.setBackground(getBackground());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == boutonOk) {
            String nom = texteNom.getText();
            String prenom = textePrenom.getText();
            String email = texteEmail.getText();
            String mdp = String.valueOf(texteMdp.getPassword());

            Etudiant.ajoutEtudiant(nom, prenom, email, mdp);

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
        String prenom = textePrenom.getText();
        String nom = texteNom.getText();
        String mail = texteEmail.getText();
        String mdp = String.valueOf(texteMdp.getPassword());

        ArrayList<Etudiant> etudiantsWithSameMail = Etudiant.searchByMail(mail.trim());

        boolean validMail = ValidationUtils.isValidMail(mail.trim()) && (etudiantsWithSameMail != null && etudiantsWithSameMail.size() == 0);

        texteEmail.setForeground(!validMail ? Couleurs.ROUGE.getCouleur() : null);
        boutonOk.setEnabled(validMail && !mdp.isEmpty() && !prenom.isEmpty() && !nom.isEmpty());
    }
}
