package controller;

import model.Etudiant;
import view.menu.emprunts_reservations.PanelEmpruntReservation;
import view.menu.emprunts_reservations.emprunt.DialogAjoutEmprunt;
import view.menu.emprunts_reservations.reservation.DialogAjoutReservation;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

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
        System.out.println("e.getActionCommand");
        if (e.getActionCommand().equals("ajoutEmp")) {
            new DialogAjoutEmprunt();
        }
        if (e.getActionCommand().equals("ajoutRes")) {
            new DialogAjoutReservation();
        }
        if (e.getActionCommand().equals("modificationEmp")) {
        }
        if (e.getActionCommand().equals("modificationRes")) {
        }
        if (e.getActionCommand().equals("suppressionEmp")) {
        }
        if (e.getActionCommand().equals("suppressionRes")) {
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
