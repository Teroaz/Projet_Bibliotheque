package view.menu;

import controller.Catalogue;
import controller.Menu;

import javax.swing.*;
import java.awt.*;

public class PanelMenu extends JPanel {

    private Menu menuController;

    public PanelMenu(Menu menuController) {
        Catalogue catalogue = new Catalogue();

        setLayout(new BorderLayout());
        this.menuController = menuController;

        PanelNavigation panelNavigation = new PanelNavigation(menuController);
        menuController.setPanelNavigation(panelNavigation);
        add(panelNavigation, BorderLayout.NORTH);
        panelNavigation.markAsActive(panelNavigation.getButtons().get(0));

        add(catalogue.getPanelCatalogue(), BorderLayout.CENTER);
    }

    public Menu getMenuController() {
        return menuController;
    }

}
