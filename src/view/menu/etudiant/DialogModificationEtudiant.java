package view.menu.etudiant;

import controller.GestionEtudiant;
import model.Etudiant;
import utils.CryptUtils;
import utils.ValidationUtils;
import utils.swing_utils.JFrameUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogModificationEtudiant extends JDialog implements ActionListener {

    JButton boutonOk = new JButton("OK");
    JButton boutonAnnuler = new JButton("Annuler");

    JTextField texteEmail = new JTextField(15);

    JLabel labelProbleme = new JLabel("Problème : ");
    JLabel labelMessage = new JLabel("Problème : ");

    JPanel panel = new JPanel();

    public DialogModificationEtudiant() {
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
        setTitle("Modification email étudiant");
        setSize(400, 300);
        setLocation(JFrameUtils.centerFrameCoords(getWidth(), getHeight()));

        panel.setLayout(new GridLayout(4,2,20,20));

        JLabel labelEtudiant = new JLabel("Étudiant");
        JLabel labelNomPrenom = new JLabel(GestionEtudiant.getInstance().getTableSelectedEtudiant().getNomPrenomId());
        JLabel labelEmail = new JLabel("Nouvel email");
        labelProbleme.setVisible(false);
        labelMessage.setVisible(false);

        boutonAnnuler.addActionListener(this);
        boutonOk.addActionListener(this);

        panel.add(labelEtudiant);
        panel.add(labelNomPrenom);
        panel.add(labelEmail);
        panel.add(texteEmail);
        panel.add(labelProbleme);
        panel.add(labelMessage);
        panel.add(boutonOk);
        panel.add(boutonAnnuler);

        add(panel);
        setContentPane(panel);

//        setBackground(Couleurs.BLEU_CLAIR.getCouleur());
//        panel.setBackground(getBackground());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == boutonAnnuler) {
            setVisible(false);
        }
        if (e.getSource() == boutonOk) {
            String email = texteEmail.getText();

            boolean rempli = false;
            boolean mailValide = false;

            if (email.isEmpty()) {
                labelMessage.setText("Le champs email est vide ! ");
                labelMessage.setVisible(true);
                labelProbleme.setVisible(true);
            }

            rempli = true;

            if (ValidationUtils.isValidMail(email.trim())) {
                labelMessage.setText("L'email n'est pas valide ! ");
                labelMessage.setVisible(true);
                labelProbleme.setVisible(true);
            }

            mailValide = true;

            if (rempli && mailValide) {
                Etudiant.modificationEtudiant(GestionEtudiant.getInstance().getTableSelectedEtudiant().getId(), email);
                setVisible(false);
            }
        }
    }
}
