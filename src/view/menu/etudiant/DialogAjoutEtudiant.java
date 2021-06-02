package view.menu.etudiant;

import model.Etudiant;
import model.Livre;
import model.design.Couleurs;
import utils.CryptUtils;
import utils.ValidationUtils;
import utils.swing_utils.JFrameUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DialogAjoutEtudiant extends JDialog implements ActionListener {

    JButton boutonOk = new JButton("OK");
    JButton boutonAnnuler = new JButton("Annuler");

    JTextField texteId = new JTextField(15);
    JTextField texteNom = new JTextField(15);
    JTextField textePrenom = new JTextField(15);
    JTextField texteEmail = new JTextField(15);
    JTextField texteMdp = new JTextField(15);

    JLabel labelProbleme = new JLabel("Problème : ", JLabel.CENTER);
    JLabel labelMessage = new JLabel(" ", JLabel.CENTER);

    JPanel panel = new JPanel();

    public DialogAjoutEtudiant() {
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
        setTitle("Ajout d'une réservation");
        setSize(400, 400);
        setLocation(JFrameUtils.centerFrameCoords(getWidth(), getHeight()));

        panel.setLayout(new GridLayout(7,2,10,20));

        JLabel labelId = new JLabel("ID Étudiant", JLabel.CENTER);
        JLabel labelNom = new JLabel("Nom", JLabel.CENTER);
        JLabel labelPrenom = new JLabel("Prénom", JLabel.CENTER);
        JLabel labelEmail = new JLabel("Email", JLabel.CENTER);
        JLabel labelMdp = new JLabel("Mot de passe", JLabel.CENTER);
        labelProbleme.setVisible(false);
        labelMessage.setVisible(false);

        boutonAnnuler.addActionListener(this);
        boutonOk.addActionListener(this);

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

        setBackground(Couleurs.BLEU_CLAIR.getCouleur());
        panel.setBackground(getBackground());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == boutonAnnuler) {
            setVisible(false);
        }
        if (e.getSource() == boutonOk) {
            String texteId = this.texteId.getText();
            String nom = texteNom.getText();
            String prenom = textePrenom.getText();
            String email = texteEmail.getText();
            String mdp = texteMdp.getText();

            boolean rempli = false;
            boolean idUnique = false;
            boolean mailValide = false;

            if (texteId.isEmpty() || nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || mdp.isEmpty()) {
                labelMessage.setText("Un ou plusieurs de vos champs ne sont pas rempli ! ");
                labelMessage.setVisible(true);
                labelProbleme.setVisible(true);
            }

            rempli = true;
            int id = Integer.parseInt(texteId);

            if (Etudiant.isIdExistant(id)) {
                labelMessage.setText("L'ID étudiant existe déjà ! ");
                labelMessage.setVisible(true);
                labelProbleme.setVisible(true);
            }

            idUnique = true;

            if (ValidationUtils.isValidMail(email.trim())) {
                labelMessage.setText("L'email n'est pas valide ! ");
                labelMessage.setVisible(true);
                labelProbleme.setVisible(true);
            }

            mailValide = true;

            if (rempli && idUnique && mailValide) {
                Etudiant.ajoutEtudiant(id, nom, prenom, email, CryptUtils.encrypt(mdp));
                setVisible(false);
            }
        }
    }
}
