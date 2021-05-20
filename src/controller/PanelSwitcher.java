package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelSwitcher implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public enum State {
        CONNEXION, CATALOGUE, GESTION_ETUDIANTS
    }
}
