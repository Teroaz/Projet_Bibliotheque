package controller;

import view.PanelMenu;
import view.PanelNavigation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Navigation implements ActionListener {

    private final PanelMenu panelMenu;
    private final PanelNavigation panelNavigation;

    public Navigation(PanelMenu panelMenu) {
        this.panelMenu = panelMenu;

        panelNavigation = new PanelNavigation(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getSource());
    }

    public PanelMenu getPanelMenu() {
        return panelMenu;
    }

    public PanelNavigation getPanelNavigation() {
        return panelNavigation;
    }
}
