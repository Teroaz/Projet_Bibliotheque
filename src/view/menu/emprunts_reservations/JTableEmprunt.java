package view.menu.emprunts_reservations;

import controller.Catalogue;
import controller.EmpruntReservation;
import view.menu.catalogue.ModeleCatalogue;

import javax.swing.*;
import java.awt.*;

public class JTableEmprunt extends JTable{

    private final ModeleEmprunt modeleEmprunt;
    private final JScrollPane scrollPane;
    private final EmpruntReservation empController;

    public JTableEmprunt(ModeleEmprunt modeleEmprunt, EmpruntReservation empController) {
        super(modeleEmprunt);
        this.modeleEmprunt = modeleEmprunt;
        this.empController = empController;

        scrollPane = new JScrollPane(this,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        scrollPane.setPreferredSize(new Dimension(450, 400));

        getTableHeader().setBackground(new Color(198, 173, 137, 255));
        getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        //tableSemaine.getTableHeader().setForeground(new Color(0,0,0));
        setRowHeight(20);
        //tableCatalogue.setDefaultRenderer(String.class, new CelluleRenderer());
        //tableCatalogue.setDefaultRenderer(Integer.class, new CelluleRenderer());
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
