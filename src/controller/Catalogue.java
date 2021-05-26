package controller;

import view.PanelCatalogue;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Catalogue implements MouseListener {

    private final PanelCatalogue panelCatalogue;

    public Catalogue() {
        panelCatalogue = new PanelCatalogue(this);
    }

    public PanelCatalogue getPanelCatalogue() {
        return panelCatalogue;
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
