package controller;

import exceptions.BasicException;
import exceptions.ConfigurationException;
import model.Etudiant;
import model.design.Couleurs;
import utils.ValidationUtils;
import view.PanelConnexion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class Connexion implements ActionListener, KeyListener {

    private static boolean adminMode = false;

    private String adminNom;
    private String adminPrenom;
    private String adminMail;
    private String adminPassword;

    private static Etudiant connectedStudent;

    private final PanelConnexion panelConnexion;

    public Connexion() {
        try {
            chargerProperties();
        } catch (ConfigurationException e) {
            adminNom = "admin";
            adminPrenom = "admin";
            adminMail = "admin@biblio.fr";
            adminPassword = "admin";
        }

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
        if (e.getSource() != panelConnexion.getConnectButton()) return;

        String prenomSaisi = panelConnexion.getPrenomTextField().getText().trim();
        String nomSaisi = panelConnexion.getNomTextField().getText().trim();
        String mailSaisi = panelConnexion.getMailTextField().getText().trim();
        String mdpSaisie = String.valueOf(panelConnexion.getPasswordField().getPassword());

        if (mdpSaisie.length() == 0) return;

        if (prenomSaisi.equals(adminPrenom) && nomSaisi.equals(adminNom) && mailSaisi.equals(adminMail) && mdpSaisie.equals(adminPassword)) {
            adminMode = true;
        } else if (ValidationUtils.isValidMail(mailSaisi)) {
            ArrayList<Etudiant> res = Etudiant.rechercherParNomPrenomEtMail(nomSaisi, prenomSaisi, mailSaisi);
            if (res.size() == 0) return;

            Etudiant etu = res.get(0);
            if (etu.getEmail().equalsIgnoreCase(mailSaisi) && etu.getPrenom().equalsIgnoreCase(prenomSaisi) && etu.getNom().equalsIgnoreCase(nomSaisi)) {
                if (etu.validatePassword(mdpSaisie)) {
                    connectedStudent = etu;
                }
            }
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == panelConnexion.getMailTextField()) {
            if (!ValidationUtils.isValidMail(panelConnexion.getMailTextField().getText().trim())) {
                panelConnexion.getMailTextField().setForeground(Couleurs.ROUGE.getCouleur());
            } else {
                panelConnexion.getMailTextField().setForeground(null);
            }
        }
    }

    private void chargerProperties() throws ConfigurationException {

        try {
            Properties properties = new Properties();
            FileInputStream fichierProperties;

            fichierProperties = new FileInputStream("src/resources/connexion.properties");
            properties.load(fichierProperties);

            adminNom = properties.getProperty("ADMIN_NOM");
            adminPrenom = properties.getProperty("ADMIN_PRENOM");
            adminMail = properties.getProperty("ADMIN_MAIL");
            adminPassword = properties.getProperty("ADMIN_MOT_DE_PASSE");

        } catch (IOException e) {
            throw new ConfigurationException("Le fichier connexion.properties est introuvable ou mal configuré, mise en place des identifiants par défaut. (nom : admin, prénom : admin, mail : admin@biblio.fr, password : admin");
        }
    }

}
