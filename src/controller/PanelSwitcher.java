package controller;

import view.FenetreBibliotheque;

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
        instance.setContentPane(menu.getPanelMenu());
        instance.revalidate();
        menu.getPanelNavigation().markAsActive(menu.getPanelNavigation().getButtons().get(0));
    }

    public static void switchToConnexion() {
        FenetreBibliotheque instance = FenetreBibliotheque.getInstance();
        instance.setContentPane(connexion.getPanelConnexion());
        instance.revalidate();
    }
}
