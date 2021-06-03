package view.menu.mon_compte;
import model.Emprunt;
import model.Etudiant;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelCompte extends JPanel {
    JButton mail;
    JButton mdp;
    JLabel nom_mail;
    JLabel livre;


    public PanelCompte(Etudiant etudiant) {
        int x=1;
        setLayout(new GridBagLayout());
        JPanel panelWest = new JPanel();
        JPanel panelEst = new JPanel();
        JPanel panelSouth = new JPanel();
        GridBagConstraints contrainte = new GridBagConstraints();
        JLabel info_conn = new JLabel("Informations de connexion:");
        JLabel prenom = new JLabel("Prénom");
        JLabel prenom_bd = new JLabel(etudiant.getPrenom());
        JLabel nom = new JLabel("Nom");
        JLabel nom_bd = new JLabel(etudiant.getNom());
        contrainte.gridy=0;
        nom_mail = new JLabel(etudiant.getEmail());
        JLabel motdepasse = new JLabel(etudiant.getMdp(true));
        JLabel x_reserv = new JLabel("reservations disponibles:", x);
        // mail = new JButton("editer");
        // mdp = new JButton("éditer");
        JLabel mail2 = new JLabel("Mail:");
        JLabel mdp2 = new JLabel("Mot de passe:");
        //JLabel comm = new JLabel("Mes commentaires:");


        panelEst.setLayout(new GridBagLayout());
        panelWest.setLayout(new GridBagLayout());
        panelSouth.setLayout(new GridBagLayout());
        panelEst.setBorder(BorderFactory.createTitledBorder("Livre"));
        panelWest.setBorder(BorderFactory.createTitledBorder("Connexion:"));
        panelSouth.setBorder(BorderFactory.createTitledBorder("Commentaire"));

        contrainte.insets = new Insets(10, 10, 10, 0);

        contrainte.gridx = 2;
        contrainte.gridy = 0;
        add(panelEst, contrainte);

        contrainte.gridx = 1;
        contrainte.gridy = 0;
        add(panelWest, contrainte);

        contrainte.gridx = 1;
        contrainte.gridy = 1;
        add(panelSouth, contrainte);


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

       /* contrainte.gridx = 2;
        contrainte.gridy = 3;
        panelEst.add(mail, contrainte);*/

        contrainte.gridx = 0;
        contrainte.gridy = 4;
        panelEst.add(mdp2, contrainte);

        contrainte.gridx = 1;
        contrainte.gridy = 4;
        panelEst.add(motdepasse, contrainte);

        /*contrainte.gridx = 2;
        contrainte.gridy = 4;
        panelEst.add(mdp, contrainte);*/

        for (Emprunt emprunt: etudiant.getEmprunts()) {
            livre= new JLabel(emprunt.getExemplaire().getLivre().getTitre());
            contrainte.gridx = 0;
            contrainte.gridy++;
            panelWest.add(livre, contrainte);
            x++;
        }

        contrainte.gridx = 0;
        contrainte.gridy = 3;
        panelWest.add(x_reserv, contrainte);

       /* contrainte.gridx = 0;
        contrainte.gridy = 0;
        panelSouth.add(comm, contrainte);*/


    }
}

