package view.menu.emprunts_reservations.emprunt;

import controller.PanelSwitcher;
import model.Emprunt;
import model.Etudiant;
import model.Livre;
import model.design.Couleurs;
import utils.CryptUtils;
import utils.swing_utils.JFrameUtils;
import view.FenetreBibliotheque;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

public class DialogAjoutEmprunt extends JDialog implements ActionListener {
    JButton boutonOk = new JButton("OK");
    JButton boutonAnnuler = new JButton("Annuler");

    JComboBox choixEtudiant;
    JComboBox choixLivre;

    JPanel panel = new JPanel();

    public DialogAjoutEmprunt() {
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
        setTitle("Ajout d'un emprunt");
        setSize(400, 400);
        setLocation(JFrameUtils.centerFrameCoords(getWidth(), getHeight()));

        panel.setLayout(new GridLayout(3,2,20,120));

        JLabel labelEtudiant = new JLabel("Étudiant", JLabel.CENTER);
        JLabel labelLivre = new JLabel("Livre", JLabel.CENTER);

        ArrayList <String> etudiantDispo = new ArrayList<>();
        for (Etudiant etudiant : Etudiant.liste.values()) {
            if (etudiant.peutEmprunterLivre())
                etudiantDispo.add(etudiant.getPrenom() +" "+ etudiant.getNom() +", " + etudiant.getId());
        }

        ArrayList <String> livresDispo = new ArrayList<>();
        for (Livre livre: Livre.catalogue.values()) {
            if (livre.disponible())
                livresDispo.add(livre.getTitre() +", "+ livre.getAuteur().auteurNP() +", "+ livre.getId());
        }

        choixEtudiant = new JComboBox(etudiantDispo.toArray());
        choixLivre = new JComboBox(livresDispo.toArray());

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
            String etudiant = String.valueOf(choixEtudiant.getSelectedItem());
            String [] stringEtudiant = etudiant.split(" ");
            int idEtudiant = Integer.parseInt(stringEtudiant[stringEtudiant.length-1]);

            String livre = String.valueOf(choixLivre.getSelectedItem());
            String [] stringLivre = livre.split(" ");
            int idLivre = Integer.parseInt(stringLivre[stringLivre.length-1]);
            int idEx = Livre.getIdExemplaireDispo(idLivre);

            Emprunt.ajoutEmprunt(new Date(), idEtudiant, idEx);
            PanelSwitcher.getMenu().getEmpruntReservation().getPanelEmpruntReservation().getPanelEmprunt().getTableEmprunt().getModeleEmprunt().updateEmprunts(Emprunt.emprunt);
            setVisible(false);
        }
    }
}
