package view.menu.mon_compte;

import model.design.Couleurs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelCompte extends JPanel {
    /*2 JButtons, 3 JLabel, 3JLabel+JButtons sur la meme ligne
     */
    JButton mail;
    JButton mdp;



    public PanelCompte(){
        setLayout(new GridBagLayout());
        JPanel panelWest = new JPanel();
        JPanel panelEst = new JPanel();
        JPanel panelSouth = new JPanel();

        JLabel info_conn = new JLabel("Informations de connexion:");
        JLabel prenom = new JLabel("Prénom");
        JLabel nom = new JLabel("Nom");
        JLabel livre1 = new JLabel("Livre 1: Philippe le bg aui kway qui est calmement posé sur sa ptite chaise oklm");
        JLabel livre2 = new JLabel("Saimana en train de coder un truc et elle y arrive pas(looser)");
        JLabel livre3 = new JLabel("Louis ce gros bg en train de coder super bien habillé");
        JLabel nom_mail = new JLabel("phsarafian@gmail.com");
        JLabel motdepasse = new JLabel("mot_de_passe_crypté");
        mail = new JButton("editer");
        mdp = new JButton("éditer");
        JLabel mail2 = new JLabel("Mail:");
        JLabel mdp2= new JLabel("Mot de passe:");
        JLabel comm = new JLabel("Mes commentaires:");

        panelEst.setLayout(new GridBagLayout());
        panelWest.setLayout(new GridBagLayout());
        panelSouth.setLayout(new GridBagLayout());
        panelEst.setBorder(BorderFactory.createTitledBorder("Livre"));
        panelWest.setBorder(BorderFactory.createTitledBorder("Connexion:"));
        panelSouth.setBorder(BorderFactory.createTitledBorder("Commentaire"));

        GridBagConstraints contrainte = new GridBagConstraints();
        contrainte.insets = new Insets(10,10,10,0);

        contrainte.gridx=2; contrainte.gridy=0;
        add(panelEst,contrainte);

        contrainte.gridx=1; contrainte.gridy=0;
        add(panelWest,contrainte);

        contrainte.gridx=1; contrainte.gridy=1;
        add(panelSouth,contrainte);



        contrainte.gridx = 0; contrainte.gridy = 0;
        panelEst.add(info_conn,contrainte);

        contrainte.gridx = 0; contrainte.gridy = 1;
        panelEst.add(prenom,contrainte);

        contrainte.gridx = 0; contrainte.gridy = 2;
        panelEst.add(nom,contrainte);

        contrainte.gridx = 1; contrainte.gridy = 3;
        panelEst.add(nom_mail,contrainte);

        contrainte.gridx=0; contrainte.gridy=3;
        panelEst.add(mail2,contrainte);

        contrainte.gridx=2; contrainte.gridy=3;
        panelEst.add(mail,contrainte);

        contrainte.gridx=0; contrainte.gridy=4;
        panelEst.add(mdp2,contrainte);

        contrainte.gridx=1; contrainte.gridy=4;
        panelEst.add(motdepasse,contrainte);

        contrainte.gridx=2; contrainte.gridy=4;
        panelEst.add(mdp,contrainte);

        contrainte.gridx=0; contrainte.gridy=0;
        panelWest.add(livre1,contrainte);

        contrainte.gridx=0; contrainte.gridy=1;
        panelWest.add(livre2,contrainte);

        contrainte.gridx=0; contrainte.gridy=2;
        panelWest.add(livre3,contrainte);

        contrainte.gridx=0; contrainte.gridy=0;
        panelSouth.add(comm,contrainte);



    }
}
