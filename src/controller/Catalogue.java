package controller;

import view.PanelCatalogue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Catalogue implements MouseListener, ActionListener {

    private final PanelCatalogue panelCatalogue;

    public Catalogue() {
        panelCatalogue = new PanelCatalogue(this);
    }

    public PanelCatalogue getPanelCatalogue() {
        return panelCatalogue;
    }

    public void ActionListener(ActionEvent evt) {
        if (evt.getActionCommand().equals("ajout")) {
            
        }
        if (evt.getActionCommand().equals("suppression")) {

        }
        if (evt.getActionCommand().equals("emprunt")) {

        }
        if (evt.getActionCommand().equals("reservation")) {

        }
        if (evt.getActionCommand().equals("recherche")) {

        }
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
