package view.menu.mon_compte;

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
        setLayout(new BorderLayout());
        JPanel panelWest = new JPanel();
        JPanel panelEst = new JPanel();
        JPanel panelSouth = new JPanel();

        JLabel info_conn = new JLabel("Informations de connexion:");
        JLabel prenom = new JLabel("Prénom");
        JLabel nom = new JLabel("Nom");

        /*panelEst.setBounds(500,80,430,320);
        panelWest.setBounds(30,80,460,320);
        panelSouth.setBounds(30, 450,910,200);*/
        panelEst.setLayout(new GridBagLayout());
        panelWest.setLayout(new GridBagLayout());
        panelSouth.setLayout(new GridBagLayout());

        mail = new JButton("editer");
        mdp = new JButton("mdp");

        GridBagConstraints contrainte = new GridBagConstraints();

        contrainte.gridx = 1; contrainte.gridy = 1;
        panelEst.add(info_conn,contrainte);

        contrainte.gridx = 1; contrainte.gridy = 2;
        panelEst.add(prenom,contrainte);

        add(panelEst,BorderLayout.EAST);
        add(panelSouth,BorderLayout.SOUTH);
        add(panelWest,BorderLayout.EAST);



    }
}
