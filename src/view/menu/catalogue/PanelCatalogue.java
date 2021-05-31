package view.menu.catalogue;

import controller.Catalogue;
import model.design.Couleurs;

import javax.swing.*;
import java.awt.*;

public class PanelCatalogue extends JPanel {

    private final PanelRechercheCatalogue panelRechercheCatalogue;

    private final PanelGestionCatalogue panelGestionCatalogue;
    private final JPanel panelAffichage = new JPanel();

    private final Catalogue catController;

    private final JTableCatalogue tableCatalogue;
    private final ModeleCatalogue modeleCatalogue;

    public PanelCatalogue() {
        catController = Catalogue.getInstance();

        setLayout(new BorderLayout());

        modeleCatalogue = new ModeleCatalogue(catController);
        tableCatalogue = new JTableCatalogue(modeleCatalogue, catController);
        modeleCatalogue.registerListeners(tableCatalogue);

        panelRechercheCatalogue = new PanelRechercheCatalogue();
        panelGestionCatalogue = new PanelGestionCatalogue();

        panelAffichage.add(tableCatalogue.getScrollPane());
        panelAffichage.setBorder(BorderFactory.createTitledBorder("Catalogue"));

        panelAffichage.setBackground(Couleurs.BLEU_CLAIR.getCouleur());

        add(panelAffichage, BorderLayout.CENTER);

        add(panelGestionCatalogue, BorderLayout.EAST);

        add(panelRechercheCatalogue, BorderLayout.NORTH);

        setBackground(Couleurs.BLEU_CLAIR.getCouleur());
    }

    public JTableCatalogue getTableCatalogue() {
        return tableCatalogue;
    }

    public ModeleCatalogue getModeleCatalogue() {
        return modeleCatalogue;
    }


    public JPanel getPanelAffichage() {
        return panelAffichage;
    }


    public PanelRechercheCatalogue getPanelRechercheCatalogue() {
        return panelRechercheCatalogue;
    }

    public PanelGestionCatalogue getPanelGestionCatalogue() {
        return panelGestionCatalogue;
    }

    @Override
    public Insets getInsets() {
        return new Insets(20, 20, 20, 20);
    }
}
