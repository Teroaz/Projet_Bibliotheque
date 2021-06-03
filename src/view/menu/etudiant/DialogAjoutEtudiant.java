package view.menu.etudiant;

import controller.PanelSwitcher;
import model.Etudiant;
import model.design.Couleurs;
import utils.CryptUtils;
import utils.ValidationUtils;
import utils.swing_utils.JFrameUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DialogAjoutEtudiant extends JDialog implements ActionListener, KeyListener {

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
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
        setTitle("Ajout d'un étudiant");
        setSize(400, 400);
        setLocation(JFrameUtils.centerFrameCoords(getWidth(), getHeight()));

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

        int nextValidID = 1;
        int i = 1;

        while (Etudiant.liste.containsKey(i)) {
            i++;
        }

        nextValidID = i;

        texteId.setText(nextValidID + "");
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

        setBackground(Couleurs.BLEU_CLAIR.getCouleur());
        panel.setBackground(getBackground());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == boutonAnnuler) {
            setVisible(false);
        }

        if (e.getSource() == boutonOk) {
            String nom = texteNom.getText();
            String prenom = textePrenom.getText();
            String email = texteEmail.getText();
            String mdp = String.valueOf(texteMdp.getPassword());

            if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || mdp.isEmpty()) {
                labelMessage.setText("Un ou plusieurs de vos champs ne sont pas rempli ! ");
                labelMessage.setVisible(true);
                labelProbleme.setVisible(true);
            }

            int id = Integer.parseInt(texteId.getText());

            if (ValidationUtils.isValidMail(email.trim())) {
                labelMessage.setText("L'email n'est pas valide !");
                labelMessage.setVisible(true);
                labelProbleme.setVisible(true);
            }

            Etudiant.ajoutEtudiant(id, nom, prenom, email, CryptUtils.encrypt(mdp));
            PanelSwitcher.getMenu().getGestionEtudiant().getPanelEtudiant().getTableEtudiant().getModeleEtudiant().updateEtudiant(Etudiant.liste.values());
            setVisible(false);
        }
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

        boolean validMail = ValidationUtils.isValidMail(mail.trim());

        if (!validMail) {
            texteEmail.setForeground(Couleurs.ROUGE.getCouleur());
        } else {
            texteEmail.setForeground(null);
        }

        boutonOk.setEnabled(validMail && !mdp.isEmpty() && !prenom.isEmpty() && !nom.isEmpty());
    }

}
