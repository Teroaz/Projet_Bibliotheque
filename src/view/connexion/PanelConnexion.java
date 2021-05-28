package view.connexion;

import controller.Connexion;

import javax.swing.*;
import java.awt.*;

public class PanelConnexion extends JPanel {

    private final JTextField nomTextField;
    private final JTextField mailTextField;
    private final JPasswordField passwordField;
    private final JTextField prenomTextField;
    private final JButton connectButton;


    private final Font labelSaisieFont = new Font(Font.SANS_SERIF, Font.BOLD, 16);

    private final Connexion connexionController;

    public PanelConnexion(Connexion connexionController) {
        this.connexionController = connexionController;

        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel nomLabel = new JLabel("Nom : ");
        nomLabel.setDisplayedMnemonic('N');
        nomLabel.setFont(labelSaisieFont);

        nomTextField = new JTextField(15);
        nomTextField.addKeyListener(connexionController);

        nomLabel.setLabelFor(nomTextField);
        add(nomLabel, gbc);

        gbc.gridx++;
        add(nomTextField, gbc);


        gbc.gridx = 0;
        gbc.gridy = 1;

        JLabel prenomLabel = new JLabel("Prénom : ");
        prenomLabel.setDisplayedMnemonic('P');
        prenomLabel.setFont(labelSaisieFont);

        prenomTextField = new JTextField(15);
        prenomTextField.addKeyListener(connexionController);

        prenomLabel.setLabelFor(prenomTextField);

        add(prenomLabel, gbc);
        gbc.gridx++;

        add(prenomTextField, gbc);


        gbc.gridx = 0;
        gbc.gridy = 2;

        JLabel mailLabel = new JLabel("Mail : ");
        mailLabel.setDisplayedMnemonic('M');
        mailLabel.setFont(labelSaisieFont);

        mailTextField = new JTextField(15);
        mailTextField.addKeyListener(connexionController);

        mailLabel.setLabelFor(mailTextField);

        add(mailLabel, gbc);
        gbc.gridx++;

        add(mailTextField, gbc);


        gbc.gridx = 0;
        gbc.gridy = 3;

        JLabel passwordLabel = new JLabel("Mot de passe : ");
        passwordLabel.setDisplayedMnemonic('O');
        passwordLabel.setFont(labelSaisieFont);

        passwordField = new JPasswordField(15);
        passwordField.addKeyListener(connexionController);

        passwordLabel.setLabelFor(passwordField);

        add(passwordLabel, gbc);
        gbc.gridx++;

        add(passwordField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;


        connectButton = new JButton("Se connecter");
        connectButton.addActionListener(connexionController);
        connectButton.setMnemonic('C');

        add(connectButton, gbc);

        gbc.gridy = 5;
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
}
