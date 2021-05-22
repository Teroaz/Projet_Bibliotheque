package view;

import controller.Navigation;

import javax.swing.*;
import java.awt.*;

public class PanelMenu extends JPanel {

    private final Navigation navigation;

    public PanelMenu() {
        setLayout(new BorderLayout());

        navigation = new Navigation(this);
        add(navigation.getPanelNavigation(), BorderLayout.NORTH);
    }

    public Navigation getNavigation() {
        return navigation;
    }
}
