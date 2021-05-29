package view.menu;

import controller.Catalogue;
import controller.EmpruntReservation;
import controller.Menu;
import view.connexion.PanelConnexion;
import view.menu.emprunts_reservations.PanelEmpruntReservation;

import javax.swing.*;
import java.awt.*;

public class PanelMenu extends JPanel {

    private Menu menuController;
//    JPanel panelAffiche = new JPanel();
    PanelNavigation panelNavigation;

    public PanelMenu(Menu menuController) {
        Catalogue catalogue = new Catalogue();
//        panelAffiche = catalogue.getPanelCatalogue();

        setLayout(new BorderLayout());
        this.menuController = menuController;

        panelNavigation = new PanelNavigation(menuController);
        menuController.setPanelNavigation(panelNavigation);
        add(panelNavigation, BorderLayout.NORTH);
        panelNavigation.markAsActive(panelNavigation.getButtons().get(1));

        add(catalogue.getPanelCatalogue(), BorderLayout.CENTER);
    }

    public Menu getMenuController() {
        return menuController;
    }

    public PanelNavigation getPanelNavigation() {
        return panelNavigation;
    }

//    public void setPanelAffiche(PanelEmpruntReservation panelEmpruntReservation) {
//        panelAffiche = panelEmpruntReservation;
//        System.out.println("refresh emprunt");
//        panelAffiche.revalidate();
//        panelAffiche.repaint();
//    }
}
