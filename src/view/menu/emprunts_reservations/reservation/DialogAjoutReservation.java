package view.menu.emprunts_reservations.reservation;

import model.Etudiant;
import model.Livre;
import model.Reservation;
import model.design.Couleurs;
import utils.swing_utils.JFrameUtils;
import view.FenetreBibliotheque;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DialogAjoutReservation extends JDialog implements ActionListener {

    JButton boutonOk = new JButton("OK");
    JButton boutonAnnuler = new JButton("Annuler");

    JComboBox choixEtudiant;
    JComboBox choixLivre;

    JPanel panel = new JPanel();

    public DialogAjoutReservation() {
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
        setTitle("Ajout d'une réservation");
        setSize(400, 400);
        setLocation(JFrameUtils.centerFrameCoords(getWidth(), getHeight()));

        panel.setLayout(new GridLayout(3,2,20,120));

        JLabel labelEtudiant = new JLabel("Étudiant", JLabel.CENTER);
        JLabel labelLivre = new JLabel("Livre", JLabel.CENTER);

        ArrayList listeEtudiant = new ArrayList(Etudiant.liste.values());
        String [] etudiant = new String[listeEtudiant.size()];
        for (int i=0; i<etudiant.length; i++) {
            Etudiant etu = (Etudiant) listeEtudiant.get(i);
            etudiant[i] = etu.getNomPrenomId();
        }

        ArrayList listeLivre = new ArrayList(Livre.catalogue.values());
        String [] livre = new String[listeLivre.size()];
        for (int i=0; i<livre.length; i++) {
            Livre liv = (Livre) listeLivre.get(i);
            livre[i] = liv.getTitre() + ", " + liv.getAuteur().auteurNP();
        }

        choixEtudiant = new JComboBox(etudiant);
        choixLivre = new JComboBox(livre);

        boutonAnnuler.addActionListener(this);
        boutonOk.addActionListener(this);

        panel.add(labelLivre);
        panel.add(choixLivre);
        panel.add(labelEtudiant);
        panel.add(choixEtudiant);
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
//            Reservation.ajoutReservation();
            setVisible(false);
        }
    }
}
