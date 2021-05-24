package controller;

import exceptions.BasicException;
import model.Etudiant;
import view.PanelConnexion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Connexion implements ActionListener, KeyListener {

    private static boolean adminMode = true;

    private static Etudiant connectedStudent;

    private final PanelConnexion panelConnexion;

    public Connexion() {
        panelConnexion = new PanelConnexion(this);
    }

    public static boolean isAdminMode() {
        return adminMode;
    }

    public static void setAdminMode(boolean adminMode) {
        Connexion.adminMode = adminMode;
    }

    public static Etudiant getConnectedStudent() {
        if (adminMode) return null;
        return connectedStudent;
    }

    public static void setConnectedStudent(Etudiant connectedStudent) throws BasicException {
        if (adminMode) {
            throw new BasicException("Un étudiant ne peut pas être défini si la connexion s'est faite en mode admin");
        }
        Connexion.connectedStudent = connectedStudent;
    }

    public PanelConnexion getPanelConnexion() {
        return panelConnexion;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
