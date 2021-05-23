package view;

import javax.swing.*;

public class Catalogue extends JPanel {

    JTable tableLivre = new JTable();

    JButton ajout = new JButton("+");
    JButton suppression = new JButton("-");
    JButton reservation = new JButton("R");
    JButton emprunt = new JButton("E");

    JTextField boutonRecherche = new JTextField();

    JPanel gestion = new JPanel;
    JPanel recherche = new JPanel;
    JPanel affichage = new JPanel;

    public Catalogue() {
        gestion.setLayout(new GridLayout);

        gestion.add(ajout);
        gestion.add(suppression);
        gestion.add(reservation);
        gestion.add(emprunt);

        recherche.add(boutonRecherche);
    }
}
