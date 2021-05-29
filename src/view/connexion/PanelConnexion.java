package view.connexion;

import controller.Connexion;
import model.design.Couleurs;
import utils.DateUtils;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class PanelConnexion extends JPanel {

    private final JTextField nomTextField;
    private final JTextField mailTextField;
    private final JPasswordField passwordField;
    private final JTextField prenomTextField;
    private final JButton connectButton;

    private final Font labelSaisieFont = new Font(Font.SANS_SERIF, Font.BOLD, 16);

    private final Connexion connexionController;
    private final JLabel labelDate;
    private final JLabel labelHeure;

    JPanel panelTitre = new JPanel();
    JPanel panelLogin = new JPanel();

    public PanelConnexion(Connexion connexionController) {
        this.connexionController = connexionController;

        setLayout(new GridLayout(1, 2, 0, 0));
        panelLogin.setLayout(new GridBagLayout());
        panelTitre.setLayout(new GridLayout(3, 1, 15, 15));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        JLabel nomLabel = new JLabel("Nom : ");
        nomLabel.setDisplayedMnemonic('N');
        nomLabel.setFont(labelSaisieFont);

        nomTextField = new JTextField(15);
        nomTextField.addKeyListener(connexionController);

        nomLabel.setLabelFor(nomTextField);
        panelLogin.add(nomLabel, gbc);

        gbc.gridx++;
        panelLogin.add(nomTextField, gbc);


        gbc.gridx = 0;
        gbc.gridy = 1;

        JLabel prenomLabel = new JLabel("Prénom : ");
        prenomLabel.setDisplayedMnemonic('P');
        prenomLabel.setFont(labelSaisieFont);

        prenomTextField = new JTextField(15);
        prenomTextField.addKeyListener(connexionController);

        prenomLabel.setLabelFor(prenomTextField);

        panelLogin.add(prenomLabel, gbc);
        gbc.gridx++;

        panelLogin.add(prenomTextField, gbc);


        gbc.gridx = 0;
        gbc.gridy = 2;

        JLabel mailLabel = new JLabel("Mail : ");
        mailLabel.setDisplayedMnemonic('M');
        mailLabel.setFont(labelSaisieFont);

        mailTextField = new JTextField(15);
        mailTextField.addKeyListener(connexionController);

        mailLabel.setLabelFor(mailTextField);

        panelLogin.add(mailLabel, gbc);
        gbc.gridx++;

        panelLogin.add(mailTextField, gbc);


        gbc.gridx = 0;
        gbc.gridy = 3;

        JLabel passwordLabel = new JLabel("Mot de passe : ");
        passwordLabel.setDisplayedMnemonic('O');
        passwordLabel.setFont(labelSaisieFont);

        passwordField = new JPasswordField(15);
        passwordField.addKeyListener(connexionController);

        passwordLabel.setLabelFor(passwordField);

        panelLogin.add(passwordLabel, gbc);
        gbc.gridx++;

        panelLogin.add(passwordField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.CENTER;

        connectButton = new JButton("Se connecter");
        connectButton.addActionListener(connexionController);
        connectButton.setMnemonic('C');

        panelLogin.add(connectButton, gbc);

        gbc.gridy = 5;

        panelLogin.setBackground(Couleurs.VIOLET_CLAIR.getCouleur());
        panelTitre.setBackground(Couleurs.VIOLET.getCouleur());
//        panelLogin.setSize(new Dimension(FenetreBibliotheque.getInstance().getWidth()/2 - 20, FenetreBibliotheque.getInstance().getHeight()-1));
//        panelTitre.setSize(new Dimension(FenetreBibliotheque.getInstance().getWidth()/2 - 20, FenetreBibliotheque.getInstance().getHeight()-1));

        JLabel labelTitre = new JLabel("Bibliothèque", JLabel.CENTER);
        labelTitre.setFont(new Font("Georgia", Font.ITALIC | Font.BOLD, 60));
        panelTitre.add(labelTitre);

        labelDate = new JLabel(DateUtils.toStringDate(new Date()), JLabel.CENTER);
        labelDate.setFont(new Font("Arial", Font.PLAIN, 25));
        panelTitre.add(labelDate);

        labelHeure = new JLabel(DateUtils.toStringHeure(new Date()), JLabel.CENTER);
        labelHeure.setFont(new Font("Arial", Font.PLAIN, 25));
        panelTitre.add(labelHeure);

        refreshDateHeure();

        add(panelTitre);
        add(panelLogin);
//        addKeyListener(connexionController);
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JTextField getMailTextField() {
        return mailTextField;
    }

    public JTextField getNomTextField() {
        return nomTextField;
    }

    public JTextField getPrenomTextField() {
        return prenomTextField;
    }

    public JButton getConnectButton() {
        return connectButton;
    }

    public Connexion getConnexionController() {
        return connexionController;
    }

    public void resetFields() {

        nomTextField.setText("");
        mailTextField.setText("");
        passwordField.setText("");
        prenomTextField.setText("");

    }

    private void refreshDateHeure() {
        java.util.Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Date currDate = new Date();
                labelDate.setText(DateUtils.toStringDate(currDate));
                labelHeure.setText(DateUtils.toStringHeure(currDate));
            }
        }, 0, 1000);
    }

}
