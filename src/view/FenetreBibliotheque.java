package view;

import controller.PanelSwitcher;
import utils.swing_utils.JFrameUtils;

import javax.swing.*;

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
        setLocation(JFrameUtils.centerFrameCoords(width, height));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(width, height);
    }


    public void changeTitle(String title) {
        super.setTitle(title);
    }

    public static FenetreBibliotheque getInstance() {
        return instance;
    }
}
