package view;

import controller.Connexion;

import javax.swing.*;
import java.awt.*;

public class FenetreBibliotheque extends JFrame {

    private final int width = 930;
    private final int height = 600;

    private static FenetreBibliotheque instance;

    public FenetreBibliotheque() {
        super("Projet Bibliothèque");

        instance = this;

        Connexion connection = new Connexion();
//        PanelMenu contentPane = new PanelMenu();
        setContentPane(connection.getPanelConnexion());

        setResizable(false);
        setVisible(true);
        setDefaultLookAndFeelDecorated(true);
        setLocation(recupCoordsPourCentrer());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(width, height);
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
