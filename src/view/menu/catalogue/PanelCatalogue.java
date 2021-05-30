package view.menu.catalogue;

import controller.Catalogue;
import model.design.Couleurs;

import javax.swing.*;
import java.awt.*;

public class PanelCatalogue extends JPanel {

    private final JButton boutonAjout = new JButton("Ajouter");
    private final JButton boutonModif = new JButton("Modifier");
//    private final JButton boutonSuppression = new JButton("Supprimer");
//    private final JButton boutonReservation = new JButton("Réserver");
//    private final JButton boutonEmprunt = new JButton("Emprunt");

    private final PanelRechercheCatalogue panelRechercheCatalogue;

    private final JPanel panelGestion = new JPanel();
    private final JPanel panelAffichage = new JPanel();

    private final Catalogue catController;

    private final JTableCatalogue tableCatalogue;
    private final ModeleCatalogue modeleCatalogue;

    public PanelCatalogue() {

        catController = Catalogue.getInstance();

        setLayout(new BorderLayout());
        panelGestion.setLayout(new GridLayout(4, 1, 5, 20));

        modeleCatalogue = new ModeleCatalogue(catController);
        tableCatalogue = new JTableCatalogue(modeleCatalogue, catController);
        modeleCatalogue.registerListeners(tableCatalogue);

        boutonModif.setEnabled(false);

        boutonAjout.setActionCommand("ajout");
        boutonModif.setActionCommand("modif");
//        boutonSuppression.setActionCommand("suppression");
//        boutonEmprunt.setActionCommand("emprunt");
//        boutonReservation.setActionCommand("reservation");

        panelRechercheCatalogue = new PanelRechercheCatalogue();

        panelAffichage.add(tableCatalogue.getScrollPane());
        panelAffichage.setBorder(BorderFactory.createTitledBorder("Catalogue"));

        panelGestion.add(boutonAjout);
        panelGestion.add(boutonModif);
//        panelGestion.add(boutonSuppression);
//        panelGestion.add(boutonReservation);
//        panelGestion.add(boutonEmprunt);
        panelGestion.setBorder(BorderFactory.createTitledBorder("Gestion du catalogue"));

        panelAffichage.setBackground(Couleurs.BLEU_CLAIR.getCouleur());
        panelGestion.setBackground(Couleurs.BLEU_CLAIR.getCouleur());

        add(panelAffichage, BorderLayout.CENTER);

        add(panelGestion, BorderLayout.EAST);

        add(panelRechercheCatalogue, BorderLayout.NORTH);

        setBackground(Couleurs.BLEU_CLAIR.getCouleur());

        enregistreEcouteur();
    }

    public Catalogue getCatController() {
        return catController;
    }

    public void enregistreEcouteur() {
        boutonAjout.addActionListener(catController);
        boutonModif.addActionListener(catController);
//        boutonSuppression.addActionListener(catController);
//        boutonReservation.addActionListener(catController);
//        boutonEmprunt.addActionListener(catController);
    }

    public JTableCatalogue getTableCatalogue() {
        return tableCatalogue;
    }

    public ModeleCatalogue getModeleCatalogue() {
        return modeleCatalogue;
    }

    public JButton getBoutonAjout() {
        return boutonAjout;
    }

//    public JButton getBoutonEmprunt() {
//        return boutonEmprunt;
//    }
//
//    public JButton getBoutonReservation() {
//        return boutonReservation;
//    }
//
//    public JButton getBoutonSuppression() {
//        return boutonSuppression;
//    }

    public JPanel getPanelAffichage() {
        return panelAffichage;
    }

    public JPanel getPanelGestion() {
        return panelGestion;
    }

    public PanelRechercheCatalogue getPanelRechercheCatalogue() {
        return panelRechercheCatalogue;
    }

    public JButton getBoutonModif() {
        return boutonModif;
    }

    @Override
    public Insets getInsets() {
        return new Insets(20, 20, 20, 20);
    }
}
