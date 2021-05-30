package view.menu.emprunts_reservations.emprunt;

import controller.EmpruntReservation;
import model.design.Couleurs;
import utils.swing_utils.ColumnsAutoSizer;
import utils.swing_utils.JTableUtils;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class JTableEmprunt extends JTable {

    private final ModeleEmprunt modeleEmprunt;
    private final JScrollPane scrollPane;
    private final EmpruntReservation empController;

    public JTableEmprunt(ModeleEmprunt modeleEmprunt) {
        super(modeleEmprunt);
        empController = EmpruntReservation.getInstance();
        this.modeleEmprunt = modeleEmprunt;

        scrollPane = new JScrollPane(this,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        scrollPane.setPreferredSize(new Dimension(450, 400));

        JTableUtils.beautifyHeader(getTableHeader(), Couleurs.BLEU_CLAIR.getCouleur(), Couleurs.BLEU_FONCE.getCouleur(), 12);
        ColumnsAutoSizer.sizeColumnsToFit(this);

        setRowHeight(20);

        setDefaultRenderer(String.class, new EmpruntRenderer());
        setDefaultRenderer(Integer.class, new EmpruntRenderer());
        setDefaultRenderer(Date.class, new EmpruntRenderer());

        addMouseListener(empController);
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public EmpruntReservation getEmpController() {
        return empController;
    }

    public ModeleEmprunt getModeleEmprunt() {
        return modeleEmprunt;
    }
}
