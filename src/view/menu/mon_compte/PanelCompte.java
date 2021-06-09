package view.menu.mon_compte;

import model.Emprunt;
import model.Etudiant;
import model.design.Couleurs;
import utils.swing_utils.JButtonUtils;

import javax.swing.*;
import java.awt.*;

public class PanelCompte extends JPanel {
    JLabel nom_mail;


    public PanelCompte(Etudiant etudiant) {
        setLayout(new GridLayout(1, 2));

        JPanel panelWest = new JPanel();
        JPanel panelEst = new JPanel();

        GridBagConstraints contrainte = new GridBagConstraints();
        JLabel info_conn = new JLabel("Informations de connexion:");
        JLabel prenom = new JLabel("Prénom");
        JLabel nom = new JLabel("Nom");
        JLabel mail2 = new JLabel("Mail:");
        JLabel mdp2 = new JLabel("Mot de passe:");

        JLabel prenom_bd = new JLabel(etudiant.getPrenom());
        JLabel nom_bd = new JLabel(etudiant.getNom());
        nom_mail = new JLabel(etudiant.getEmail());
        JLabel motdepasse = new JLabel(etudiant.getMdp(true));

        panelEst.setLayout(new GridBagLayout());
        panelWest.setLayout(new GridBagLayout());

        panelEst.setBorder(BorderFactory.createTitledBorder("Livre"));
        panelWest.setBorder(BorderFactory.createTitledBorder("Connexion:"));

        contrainte.insets = new Insets(10, 10, 10, 0);

        contrainte.gridx = 0;
        contrainte.gridy = 0;
        panelEst.add(info_conn, contrainte);

        contrainte.gridx = 0;
        contrainte.gridy = 1;
        panelEst.add(prenom, contrainte);

        contrainte.gridx = 1;
        contrainte.gridy = 1;
        panelEst.add(prenom_bd, contrainte);

        contrainte.gridx = 0;
        contrainte.gridy = 2;
        panelEst.add(nom, contrainte);

        contrainte.gridx = 1;
        contrainte.gridy = 2;
        panelEst.add(nom_bd, contrainte);

        contrainte.gridx = 1;
        contrainte.gridy = 3;
        panelEst.add(nom_mail, contrainte);

        contrainte.gridx = 0;
        contrainte.gridy = 3;
        panelEst.add(mail2, contrainte);

        contrainte.gridx = 0;
        contrainte.gridy = 4;
        panelEst.add(mdp2, contrainte);

        contrainte.gridx = 1;
        contrainte.gridy = 4;
        panelEst.add(motdepasse, contrainte);

        JPanel panelLivres = new JPanel();
        panelLivres.setLayout(new GridLayout(5, 2));

        for (int i = 0; i < 5; i++) {
            contrainte.gridx = 0;
            contrainte.gridy = 0;
            JPanel panelLivre = new JPanel();
            panelLivre.setLayout(new GridBagLayout());
            JLabel livreInfo;
            try {
                Emprunt emprunt = etudiant.getEmprunts().get(i);
                livreInfo = new JLabel(emprunt.getExemplaire().getLivre().getTitre());
                livreInfo.setBackground(Couleurs.BLEU_CLAIR.getCouleur());

                JButton boutonLivre = new JButton("Rendre");
                JButtonUtils.beautifyButton(boutonLivre, Couleurs.BLEU_CLAIR.getCouleur(), Couleurs.BLEU_SOMBRE.getCouleur(), 3);

                panelLivre.add(livreInfo, contrainte);
                contrainte.gridx++;
                panelLivre.add(boutonLivre, contrainte);
            } catch (Exception e) {
                livreInfo = new JLabel("Réservation disponible");
                livreInfo.setBackground(Couleurs.BLEU_CLAIR.getCouleur());
                panelLivre.add(livreInfo, contrainte);
            }
            panelLivres.add(panelLivre);
        }

        panelWest.add(panelLivres);

        setBackground(Couleurs.BLEU_CLAIR.getCouleur());
        panelWest.setBackground(Couleurs.BLEU_CLAIR.getCouleur());
        panelEst.setBackground(Couleurs.BLEU_CLAIR.getCouleur());

        add(panelWest);
        add(panelEst);
    }

    @Override
    public Insets getInsets() {
        return new Insets(20, 20, 20, 20);
    }
}

