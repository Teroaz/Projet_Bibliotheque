package view;

import controller.Catalogue;
import model.Livre;

import javax.swing.*;
import java.util.Collection;

public class PanelCatalogue extends JPanel {

    //ModeleLivre tableLivre = new ModeleLivre();

    private final Catalogue catController;

    public PanelCatalogue(Catalogue catController) {

        this.catController = catController;

        Collection<Livre> livres = Livre.catalogue.values();

        ModeleLivre modele = new ModeleLivre(livres);
        JTable tableLivre = new JTable(modele);
        JScrollPane scrollPane = new JScrollPane(tableLivre,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.add(scrollPane);
    }

    public Catalogue getCatController() {
        return catController;
    }
}
