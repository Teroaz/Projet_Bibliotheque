package view.menu.catalogue;

import controller.Catalogue;
import model.Livre;

import javax.swing.*;
import java.awt.*;

public class PanelCatalogue extends JPanel {

    JButton boutonAjout = new JButton("Ajouter");
    JButton boutonSuppression = new JButton("Supprimer");
    JButton boutonReservation = new JButton("Réserver");
    JButton boutonEmprunt = new JButton("Emprunt");
    JButton boutonRecherche = new JButton("Recherche");

    JTextField texteRecherche = new JTextField(15);

    JPanel gestion = new JPanel();
    JPanel recherche = new JPanel();
    JPanel affichage = new JPanel();
    private final Catalogue catController;

    public PanelCatalogue(Catalogue catController) {
        this.catController = catController;

        setLayout(new BorderLayout());
        gestion.setLayout(new GridLayout(4, 1));

        ModeleCatalogue modeleCatalogue = new ModeleCatalogue();
        JTableCatalogue tableCatalogue = new JTableCatalogue(modeleCatalogue, catController);

        boutonAjout.setActionCommand("ajout");
        boutonSuppression.setActionCommand("suppression");
        boutonEmprunt.setActionCommand("emprunt");
        boutonReservation.setActionCommand("reservation");
        boutonRecherche.setActionCommand("recherche");

        affichage.add(tableCatalogue.getScrollPane());

        gestion.add(boutonAjout);
        gestion.add(boutonSuppression);
        gestion.add(boutonReservation);
        gestion.add(boutonEmprunt);

        recherche.add(texteRecherche);
        recherche.add(boutonRecherche);

        add(affichage, BorderLayout.CENTER);
        add(gestion, BorderLayout.EAST);
        add(recherche, BorderLayout.NORTH);

        setBackground(new Color(240, 230, 200));
    }

    public Catalogue getCatController() {
        return catController;
    }

    public void enregistreEcouteur(Catalogue catalogue) {
        boutonAjout.addActionListener(catalogue);
        boutonSuppression.addActionListener(catalogue);
        boutonReservation.addActionListener(catalogue);
        boutonEmprunt.addActionListener(catalogue);
        boutonRecherche.addActionListener(catalogue);
    }

}
