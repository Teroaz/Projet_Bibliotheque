package controller;

import exceptions.BasicException;
import model.Etudiant;
import utils.ValidationUtils;
import view.PanelConnexion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

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
        String prenomSaisi = panelConnexion.getPrenomTextField().getText().trim();
        String nomSaisi = panelConnexion.getNomTextField().getText().trim();
        String mailSaisi = panelConnexion.getMailTextField().getText().trim();
        String mdpSaisie = String.valueOf(panelConnexion.getPasswordField().getPassword());

        if (ValidationUtils.isValidMail(mailSaisi)) {
            ArrayList<Etudiant> res = Etudiant.rechercherParNomPrenomEtMail(nomSaisi, prenomSaisi, mailSaisi);
            if (res.size() == 0 || mdpSaisie.length() == 0) return;

            Etudiant etu = res.get(0);

            if (etu.getEmail().equalsIgnoreCase(mailSaisi) && etu.getPrenom().equalsIgnoreCase(prenomSaisi) && etu.getNom().equalsIgnoreCase(nomSaisi)) {
                if (etu.validatePassword(mdpSaisie)) {
                    // Etudiant a saisi les bons identifiants
                }
            }
        }
    }

}
