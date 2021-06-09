package view.menu.catalogue.exemplaires;

import model.design.Couleurs;
import utils.swing_utils.ColumnsAutoSizer;
import utils.swing_utils.JTableUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class JTableExemplaires extends JTable {

    private final ModeleExemplaires modeleExemplaires;
    private final JScrollPane scrollPane;

    public JTableExemplaires(ModeleExemplaires modeleExemplaires) {
        super(modeleExemplaires);
        this.modeleExemplaires = modeleExemplaires;

        scrollPane = new JScrollPane(this,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JTableUtils.beautifyHeader(tableHeader, Couleurs.BLEU_CLAIR.getCouleur(), Couleurs.BLEU_FONCE.getCouleur(), 14);
        ColumnsAutoSizer.sizeColumnsToFit(this);

        setRowHeight(20);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        getTableHeader().setReorderingAllowed(false);

        scrollPane.setPreferredSize(new Dimension(700, 400));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public ModeleExemplaires getModeleExemplaires() {
        return modeleExemplaires;
    }
}
