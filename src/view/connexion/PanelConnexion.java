package view.connexion;

import javax.swing.*;
import java.awt.*;

public class PanelConnexion extends JPanel {

    private final PanelPresentation panelPresentation;
    private final PanelLogin panelLogin;

    public PanelConnexion() {
        setLayout(new GridLayout(1, 2, 0, 0));

        panelPresentation = new PanelPresentation();
        panelLogin = new PanelLogin();

        add(panelPresentation);
        add(panelLogin);
    }

    public PanelPresentation getPanelPresentation() {
        return panelPresentation;
    }

    public PanelLogin getPanelLogin() {
        return panelLogin;
    }
}
