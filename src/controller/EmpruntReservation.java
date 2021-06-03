package controller;

import view.menu.emprunts_reservations.PanelEmpruntReservation;
import view.menu.emprunts_reservations.emprunt.DialogAjoutEmprunt;
import view.menu.emprunts_reservations.emprunt.DialogModificationEmprunt;
import view.menu.emprunts_reservations.emprunt.DialogSuppressionEmprunt;
import view.menu.emprunts_reservations.reservation.DialogAjoutReservation;
import view.menu.emprunts_reservations.reservation.DialogSuppressionReservation;

import java.awt.event.*;

public class EmpruntReservation implements ActionListener, MouseListener, KeyListener {

    private final PanelEmpruntReservation panelEmpruntReservation;
    private static EmpruntReservation instance;

    public EmpruntReservation() {
        instance = this;
        panelEmpruntReservation = new PanelEmpruntReservation();
    }

    public static EmpruntReservation getInstance() {
        return instance;
    }

    public PanelEmpruntReservation getPanelEmpruntReservation() {
        return panelEmpruntReservation;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "ajoutEmp" -> new DialogAjoutEmprunt();
            case "ajoutRes" -> new DialogAjoutReservation();
            case "modificationEmp" -> new DialogModificationEmprunt();
            case "suppressionEmp" -> new DialogSuppressionEmprunt();
            case "suppressionRes" -> new DialogSuppressionReservation();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
