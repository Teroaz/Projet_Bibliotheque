package view;

import javax.swing.*;

public class Catalogue extends JPanel {

    JTable tableLivre = new JTable();

    JButton ajout = new JButton("+");
    JButton suppression = new Button("-");

    JPanel gestion = new JPanel;
    JPanel recherche = new JPanel;
    JPanel affichage = new JPanel;

    public Catalogue() {
        gestion.setLayout(new GridLayout);

    }
}
