package view;

import controller.PanelSwitcher;
import model.Emprunt;
import model.Reservation;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class FenetreBibliotheque extends JFrame {

    private final int width = 930;
    private final int height = 650;

    private static FenetreBibliotheque instance;

    public FenetreBibliotheque() {
        super("Bibliothèque | Menu de connexion");

        instance = this;

        PanelSwitcher panelSwitcher = new PanelSwitcher();

        setContentPane(panelSwitcher.getConnexion().getPanelConnexion());

        setResizable(false);
        setVisible(true);
        setDefaultLookAndFeelDecorated(true);
        setLocation(recupCoordsPourCentrer());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(width, height);

        Emprunt.chargerEmprunt();
        Reservation.chargerReservation();
    }

    private Point recupCoordsPourCentrer() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = screenSize.width / 2 - width / 2;
        int y = screenSize.height / 2 - height / 2;
        return new Point(x, y);
    }

    public void changeTitle(String title) {
        super.setTitle(title);
    }

    public static FenetreBibliotheque getInstance() {
        return instance;
    }
}
