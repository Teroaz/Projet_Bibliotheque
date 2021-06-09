package controller;

import exceptions.BasicException;
import view.FenetreBibliotheque;

public class PanelSwitcher {

    private static Connexion connexion;
    private static Menu menu;

    private static PanelSwitcher instance;

    public PanelSwitcher() {
        instance = this;

        connexion = new Connexion();
    }

    public static PanelSwitcher getInstance() {
        return instance;
    }

    public Connexion getConnexion() {
        return connexion;
    }

    public static Menu getMenu() {
        return menu;
    }

    public static void switchToMenu() {
        FenetreBibliotheque instance = FenetreBibliotheque.getInstance();
        menu = new Menu();

        instance.setContentPane(menu.getPanelMenu());
        instance.revalidate();
    }

    public static void switchToConnexion() {
        FenetreBibliotheque instance = FenetreBibliotheque.getInstance();
        instance.setContentPane(connexion.getPanelConnexion());
        instance.setTitle("Bibliothèque | Menu de connexion");
        Connexion.setAdminMode(false);
        try {
            Connexion.setConnectedStudent(null);
        } catch (BasicException e) {
            e.printStackTrace();
        }
        connexion.getPanelConnexion().getPanelLogin().resetFields();
        instance.revalidate();
    }
}