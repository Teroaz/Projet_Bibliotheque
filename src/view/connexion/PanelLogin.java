package view.connexion;

import controller.Connexion;
import exceptions.BasicException;
import exceptions.RestrictionException;
import model.Etudiant;
import model.design.Couleurs;

import javax.swing.*;
import java.awt.*;

public class PanelLogin extends JPanel {

    private final Font labelSaisieFont = new Font(Font.SANS_SERIF, Font.BOLD, 16);

    private final JTextField mailTextField;
    private final JPasswordField passwordField;
    private final Connexion connexionController;
    private final JButton connectButton;

    public PanelLogin() {

        connexionController = Connexion.getInstance();
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        JLabel nomLabel = new JLabel("Nom : ");
        nomLabel.setDisplayedMnemonic('N');
        nomLabel.setFont(labelSaisieFont);

        gbc.gridx = 0;
        gbc.gridy = 1;

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
        gbc.anchor = GridBagConstraints.CENTER;

        connectButton = new JButton("Se connecter");
        connectButton.addActionListener(connexionController);
        connectButton.setMnemonic('C');
        connectButton.setEnabled(false);

        add(connectButton, gbc);

        gbc.gridy = 5;

        setBackground(Couleurs.VIOLET_CLAIR.getCouleur());
    }

    public void resetFields() {
        mailTextField.setText("");
        passwordField.setText("");
    }

    public JButton getConnectButton() {
        return connectButton;
    }

    public JTextField getMailTextField() {
        return mailTextField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public void invalidCredentials() {
        try {
            throw new RestrictionException("Le login ou le mot de passe est incorrect !");
        } catch (RestrictionException e) {
            e.printStackTrace();
        }
    }

    public void autoInputAdmin() {
        Connexion connexionInstance = Connexion.getInstance();
        mailTextField.setText(connexionInstance.getAdminMail());
        passwordField.setText(connexionInstance.getAdminPassword());
    }

    public void autoInputEleve() {
        Etudiant etudiant = Etudiant.getById(1);
        mailTextField.setText(etudiant.getEmail());
        passwordField.setText(etudiant.getMdp(true));
    }

}
