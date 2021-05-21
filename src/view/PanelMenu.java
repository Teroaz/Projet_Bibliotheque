package view;

import javax.swing.*;
import java.awt.*;

public class PanelMenu extends JPanel {

    public PanelMenu() {
        BorderLayout layout = new BorderLayout();
        setLayout(layout);

        PanelNavigationOld panelTop = new PanelNavigationOld();
        panelTop.setBackground(new Color(224, 174, 92));
        add(panelTop, BorderLayout.NORTH);

        JPanel panelGauche = new JPanel();
        panelGauche.add(new JButton("gauche"));
        panelGauche.setBackground(new Color(243, 96, 75));
        add(panelGauche);

        JPanel panelCentre = new JPanel();
        panelCentre.add(new JButton("centre"));
        panelCentre.setBackground(new Color(69, 113, 198));
        add(panelCentre);
    }
}
