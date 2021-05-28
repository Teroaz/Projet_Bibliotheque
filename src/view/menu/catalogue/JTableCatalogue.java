package view.menu.catalogue;

import controller.Catalogue;

import javax.swing.*;
import java.awt.*;

public class JTableCatalogue extends JTable {

    private final ModeleCatalogue modeleCatalogue;
    private final JScrollPane scrollPane;
    private final Catalogue catController;

    public JTableCatalogue(ModeleCatalogue modeleCatalogue, Catalogue catController) {
        super(modeleCatalogue);
        this.modeleCatalogue = modeleCatalogue;
        this.catController = catController;

        scrollPane = new JScrollPane(this,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        scrollPane.setPreferredSize(new Dimension(500, 500));

        getTableHeader().setBackground(new Color(198, 173, 137, 255));
        getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        //tableSemaine.getTableHeader().setForeground(new Color(0,0,0));
        setRowHeight(20);
        //tableCatalogue.setDefaultRenderer(String.class, new CelluleRenderer());
        //tableCatalogue.setDefaultRenderer(Integer.class, new CelluleRenderer());
        addMouseListener(catController);

        scrollPane.setPreferredSize(new Dimension(700,500));

    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public Catalogue getCatController() {
        return catController;
    }

    public ModeleCatalogue getModeleCatalogue() {
        return modeleCatalogue;
    }
}
