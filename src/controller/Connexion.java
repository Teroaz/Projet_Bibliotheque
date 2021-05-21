package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Connexion implements ActionListener {

    private static boolean adminMode = false;

    public static boolean isAdminMode() {
        return adminMode;
    }

    public static void setAdminMode(boolean adminMode) {
        Connexion.adminMode = adminMode;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
