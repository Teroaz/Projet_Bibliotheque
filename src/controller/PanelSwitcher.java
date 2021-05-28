package controller;

import view.FenetreBibliotheque;
import view.menu.PanelMenu;

public class PanelSwitcher {

    private static Connexion connexion;
    private static Menu menu;

    public PanelSwitcher() {
        connexion = new Connexion();
        menu = new Menu();
    }

    public Connexion getConnexion() {
        return connexion;
    }

    public Menu getMenu() {
        return menu;
    }

    public static void switchToMenu() {
        FenetreBibliotheque instance = FenetreBibliotheque.getInstance();

        menu.setPanelMenu(new PanelMenu(menu));
        instance.setContentPane(menu.getPanelMenu());

        instance.revalidate();
    }

    public static void switchToConnexion() {
        FenetreBibliotheque instance = FenetreBibliotheque.getInstance();
        instance.setContentPane(connexion.getPanelConnexion());
        instance.setTitle("Bibliothèque | Menu de connexion");
        connexion.getPanelConnexion().resetFields();
        instance.revalidate();
    }
}