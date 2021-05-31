package view.menu.emprunts_reservations;

import controller.EmpruntReservation;
import model.design.Couleurs;
import view.menu.emprunts_reservations.emprunt.PanelEmprunt;
import view.menu.emprunts_reservations.reservation.PanelReservation;

import javax.swing.*;
import java.awt.*;

public class PanelEmpruntReservation extends JPanel {

    private final PanelRechercheEmpRes recherche = new PanelRechercheEmpRes();
    private final PanelEmprunt panelEmprunt = new PanelEmprunt();
    private final PanelReservation panelReservation = new PanelReservation();
    private final EmpruntReservation empController;

    JPanel panelEmpruntReservation = new JPanel();

    public PanelEmpruntReservation() {
        empController = EmpruntReservation.getInstance();

        setLayout(new BorderLayout());
        setBackground(Couleurs.BLEU_CLAIR.getCouleur());

        panelEmpruntReservation.setLayout(new GridLayout(1, 2, 10, 0));

        panelEmpruntReservation.add(panelEmprunt);
        panelEmpruntReservation.add(panelReservation);

        add(recherche, BorderLayout.CENTER);
        add(panelEmpruntReservation, BorderLayout.SOUTH);

        panelReservation.setBackground(getBackground());
        panelEmpruntReservation.setBackground(getBackground());
    }

    public JPanel getPanelEmpruntReservation() {
        return panelEmpruntReservation;
    }

    public PanelEmprunt getPanelEmprunt() {
        return panelEmprunt;
    }

    public EmpruntReservation getEmpControleur() {
        return empController;
    }

    @Override
    public Insets getInsets() {
        return new Insets(20, 20, 20, 20);
    }
}
