package controller;

import view.FenetreBibliotheque;
import view.menu.PanelMenu;
import view.menu.emprunts_reservations.PanelEmpruntReservation;

public class PanelSwitcher {

    private static Connexion connexion;
    private static Menu menu;
    private static EmpruntReservation empruntReservation;

    public PanelSwitcher() {
        connexion = new Connexion();
        menu = new Menu();
        empruntReservation = new EmpruntReservation();
    }

    public Connexion getConnexion() {
        return connexion;
    }

    public static Menu getMenu() {
        return menu;
    }

    public EmpruntReservation getEmpruntReservation() { return empruntReservation; }

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

    public static void switchToEmpruntReservation() {
        FenetreBibliotheque instance = FenetreBibliotheque.getInstance();

        empruntReservation.setPanelEmpruntReservation(new PanelEmpruntReservation(empruntReservation));
        instance.setContentPane(empruntReservation.getPanelEmpruntReservation());
        instance.setTitle("Bibliothèque | Emprunts & Réservations");

        instance.revalidate();
    }
}