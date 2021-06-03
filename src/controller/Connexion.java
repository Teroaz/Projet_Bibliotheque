package controller;

import exceptions.BasicException;
import exceptions.ConfigurationException;
import model.Etudiant;
import model.design.Couleurs;
import utils.ValidationUtils;
import view.connexion.PanelConnexion;
import view.connexion.PanelLogin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class Connexion implements ActionListener, KeyListener {

    private String adminUsername;
    private String adminMail;
    private String adminPassword;

    private static boolean adminMode = false;
    private static Etudiant connectedStudent;

    private final PanelConnexion panelConnexion;

    private static Connexion instance;

    public Connexion() {
        instance = this;
        try {
            chargerProperties();
        } catch (ConfigurationException e) {
            adminUsername = "admin";
            adminMail = "admin@biblio.fr";
            adminPassword = "admin";
        }
        panelConnexion = new PanelConnexion();
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

    public static Connexion getInstance() {
        return instance;
    }

    public PanelConnexion getPanelConnexion() {
        return panelConnexion;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PanelLogin panelLogin = panelConnexion.getPanelLogin();

        if (e.getSource() != panelLogin.getConnectButton()) return;

        tryConnection();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        PanelLogin panelLogin = panelConnexion.getPanelLogin();
        if (e.getKeyChar() == '!') {
            panelLogin.autoInputAdmin();
        } else if (e.getKeyChar() == '=') {
            panelLogin.autoInputEleve();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 10) {
            tryConnection();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        PanelLogin panelLogin = panelConnexion.getPanelLogin();
        JTextField mailTextField = panelLogin.getMailTextField();
        JPasswordField passwordField = panelLogin.getPasswordField();
        String givenPassword = String.valueOf(passwordField.getPassword());

        boolean validMail = ValidationUtils.isValidMail(mailTextField.getText().trim());

        if (!validMail) {
            panelLogin.getMailTextField().setForeground(Couleurs.ROUGE.getCouleur());
        } else {
            panelLogin.getMailTextField().setForeground(null);
        }

        panelLogin.getConnectButton().setEnabled(validMail && !givenPassword.isEmpty());
    }

    private void chargerProperties() throws ConfigurationException {

        try {
            Properties properties = new Properties();
            FileInputStream fichierProperties;

            fichierProperties = new FileInputStream("src/resources/connexion.properties");
            properties.load(fichierProperties);

            adminUsername = properties.getProperty("ADMIN_USERNAME");
            adminMail = properties.getProperty("ADMIN_MAIL");
            adminPassword = properties.getProperty("ADMIN_MOT_DE_PASSE");

        } catch (IOException e) {
            throw new ConfigurationException("Le fichier connexion.properties est introuvable ou mal configuré, mise en place des identifiants par défaut. (nom : admin, prénom : admin, mail : admin@biblio.fr, password : admin");
        }
    }

    public void tryConnection() {
        PanelLogin panelLogin = panelConnexion.getPanelLogin();

        String mailSaisi = panelLogin.getMailTextField().getText().trim();
        String mdpSaisie = String.valueOf(panelLogin.getPasswordField().getPassword());

        if (mailSaisi.equals(adminMail) && mdpSaisie.equals(adminPassword)) {
            adminMode = true;
            PanelSwitcher.switchToMenu();
        } else {
            ArrayList<Etudiant> res = Etudiant.searchByMail(mailSaisi);
            if (res == null || res.size() != 1) {
                panelLogin.invalidCredentials();
                return;
            }

            Etudiant etu = res.get(0);

            if (etu.getEmail().equalsIgnoreCase(mailSaisi)) {
                if (etu.validatePassword(mdpSaisie)) {
                    connectedStudent = etu;
                    PanelSwitcher.switchToMenu();
                } else {
                    panelLogin.invalidCredentials();
                }
            } else {
                panelLogin.invalidCredentials();
            }
        }
    }

    public String getAdminMail() {
        return adminMail;
    }

    public String getAdminPassword() {
        return adminPassword;
    }
}
