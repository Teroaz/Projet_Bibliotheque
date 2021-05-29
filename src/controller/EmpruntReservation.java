package controller;

import view.menu.emprunts_reservations.PanelEmpruntReservation;

import java.awt.*;
import java.awt.event.*;

public class EmpruntReservation implements ActionListener, MouseListener {

    private PanelEmpruntReservation panelEmpruntReservation;

    public EmpruntReservation() {
        panelEmpruntReservation = new PanelEmpruntReservation(this);
    }

    public PanelEmpruntReservation getPanelEmpruntReservation() {
        return panelEmpruntReservation;
    }

    public void setPanelEmpruntReservation (PanelEmpruntReservation panelEmpruntReservation) {
        this.panelEmpruntReservation = panelEmpruntReservation;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

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
}
