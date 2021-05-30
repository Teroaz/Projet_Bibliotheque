package view.menu.emprunts_reservations.reservation;

import controller.EmpruntReservation;
import model.design.Couleurs;
import utils.swing_utils.ColumnsAutoSizer;
import utils.swing_utils.JTableUtils;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class JTableReservation extends JTable {

    private final ModeleReservation modeleReservation;
    private final JScrollPane scrollPane;
    private final EmpruntReservation resController;

    public JTableReservation(ModeleReservation modeleReservation) {
        super(modeleReservation);
        resController = EmpruntReservation.getInstance();

        this.modeleReservation = modeleReservation;

        scrollPane = new JScrollPane(this,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        scrollPane.setPreferredSize(new Dimension(450, 400));

        JTableUtils.beautifyHeader(getTableHeader(), Couleurs.BLEU_CLAIR.getCouleur(), Couleurs.BLEU_FONCE.getCouleur(), 12);
        ColumnsAutoSizer.sizeColumnsToFit(this);

        setRowHeight(20);

        setDefaultRenderer(String.class, new ReservationRenderer());
        setDefaultRenderer(Integer.class, new ReservationRenderer());
        setDefaultRenderer(Date.class, new ReservationRenderer());

        addMouseListener(resController);
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public EmpruntReservation getResController() {
        return resController;
    }

    public ModeleReservation getModeleReservation() {
        return modeleReservation;
    }
}
