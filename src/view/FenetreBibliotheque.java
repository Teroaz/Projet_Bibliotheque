package view;

import javax.swing.*;
import java.awt.*;

public class FenetreBibliotheque extends JFrame {

    private final int width = 930;
    private final int height = 600;

    public FenetreBibliotheque() {
        super("Projet Bibliothèque");

        PanelMenu contentPane = new PanelMenu();
        setContentPane(contentPane);

        setResizable(false);
        setVisible(true);
        setDefaultLookAndFeelDecorated(true);
        setLocation(recupCoordsPourCentrer());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(width, height);
    }

    private Point recupCoordsPourCentrer() {
        Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
        int x = tailleEcran.width / 2 - width / 2;
        int y = tailleEcran.height / 2 - height / 2;
        return new Point(x, y);
    }
}
