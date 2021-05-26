package view;

import controller.Menu;

import javax.swing.*;
import java.awt.*;

public class PanelMenu extends JPanel {

    private Menu menuController;

    public PanelMenu(Menu menuController) {

        setLayout(new BorderLayout());
        this.menuController = menuController;

        PanelNavigation panelNavigation = new PanelNavigation(menuController);
        menuController.setPanelNavigation(panelNavigation);
        add(panelNavigation, BorderLayout.NORTH);

    }

    public Menu getMenuController() {
        return menuController;
    }

}
