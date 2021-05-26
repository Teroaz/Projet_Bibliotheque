package controller;

import view.PanelMenu;
import view.PanelNavigation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu implements ActionListener {

    private final PanelMenu panelMenu;
    private PanelNavigation panelNavigation;

    public Menu() {
        this.panelMenu = new PanelMenu(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        panelNavigation.markAsActive(button);
    }

    public PanelMenu getPanelMenu() {
        return panelMenu;
    }

    public PanelNavigation getPanelNavigation() {
        return panelNavigation;
    }

    public void setPanelNavigation(PanelNavigation panelNavigation) {
        this.panelNavigation = panelNavigation;
    }
}
