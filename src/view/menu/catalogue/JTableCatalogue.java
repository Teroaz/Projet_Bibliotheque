package view.menu.catalogue;

import controller.Catalogue;
import model.design.Couleurs;
import utils.swing_utils.ColumnsAutoSizer;
import utils.swing_utils.JTableUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
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

        JTableUtils.beautifyHeader(tableHeader, Couleurs.MARRON_FONCE.getCouleur(), Couleurs.BEIGE.getCouleur());
        ColumnsAutoSizer.sizeColumnsToFit(this);

        setRowHeight(20);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        getTableHeader().setReorderingAllowed(false);

        scrollPane.setPreferredSize(new Dimension(700, 400));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
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

    @Override
    public Class<?> getColumnClass(int column) {
        return getValueAt(0, column).getClass();
    }
}
