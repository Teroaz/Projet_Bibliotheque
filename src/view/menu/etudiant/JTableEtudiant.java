package view.menu.etudiant;

import controller.Catalogue;
import controller.GestionEtudiant;
import model.design.Couleurs;
import utils.swing_utils.ColumnsAutoSizer;
import utils.swing_utils.JTableUtils;
import view.menu.catalogue.ModeleCatalogue;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class JTableEtudiant extends JTable {

    private final ModeleEtudiant modeleEtudiant;
    private final JScrollPane scrollPane;
    private final GestionEtudiant etuController;

    public JTableEtudiant(ModeleEtudiant modeleEtudiant, GestionEtudiant etuController) {
        super(modeleEtudiant);
        this.modeleEtudiant = modeleEtudiant;
        this.etuController = etuController;

        scrollPane = new JScrollPane(this,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JTableUtils.beautifyHeader(tableHeader, Couleurs.BLEU_CLAIR.getCouleur(), Couleurs.BLEU_FONCE.getCouleur(), 14);
        ColumnsAutoSizer.sizeColumnsToFit(this);

        setRowHeight(20);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        getTableHeader().setReorderingAllowed(false);

        scrollPane.setPreferredSize(new Dimension(600, 400));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public GestionEtudiant getGestionEtudiant() {
        return etuController;
    }

    public ModeleEtudiant getModeleEtudiant() {
        return modeleEtudiant;
    }

//    @Override
//    public Class<?> getColumnClass(int column) {
//        return getValueAt(0, column).getClass();
//    }
}
