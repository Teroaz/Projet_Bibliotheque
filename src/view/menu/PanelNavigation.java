package view.menu;

import controller.Connexion;
import controller.Menu;
import model.Etudiant;
import model.design.Couleurs;
import utils.swing_utils.JButtonUtils;
import view.FenetreBibliotheque;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PanelNavigation extends JPanel {

    private final Font buttonFont = new Font(Font.SANS_SERIF, Font.BOLD, 20);
    private final String[] buttonsLabels;
    private final ArrayList<JButton> buttons = new ArrayList<>();
    private final Menu navController;

    public PanelNavigation(Menu navController) {

        this.navController = navController;

        setLayout(new GridBagLayout());
        setBackground(Couleurs.BLEU_FONCE.getCouleur());

        if (Connexion.isAdminMode()) {
            buttonsLabels = new String[]{"Étudiants", "Livres & Exemplaires", "Réservations & Emprunts", "Déconnexion"};
        } else {
            buttonsLabels = new String[]{"Mon espace", "Catalogue", "Déconnexion"};
        }

        addButtons();
        registerClicksListeners();
    }

    public Font getButtonFont() {
        return buttonFont;
    }

    public String[] getButtonsLabels() {
        return buttonsLabels;
    }

    public ArrayList<JButton> getButtons() {
        return buttons;
    }

    public Menu getNavController() {
        return navController;
    }

    @Override
    public Insets getInsets() {
        if (Connexion.isAdminMode()) {
            return new Insets(15, 25, 15, 25);
        } else {
            return new Insets(15, 170, 15, 170);
        }
    }

    public void addButtons() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0;

        for (int i = 0; i < buttonsLabels.length; i++) {
            gbc.gridx = i;
            JButton jButton = new JButton(buttonsLabels[i]);
            jButton.setFont(buttonFont);
            JButtonUtils.beautifyButton(jButton, Couleurs.BLEU_CLAIR.getCouleur(), Couleurs.BLEU_FONCE.getCouleur(), 2);
            buttons.add(jButton);
            add(buttons.get(i), gbc);
        }
    }

    public void registerClicksListeners() {
        buttons.forEach(b -> b.addActionListener(navController));
    }

    public void markAsActive(JButton button) {
        Etudiant coStu = Connexion.getConnectedStudent();
        FenetreBibliotheque.getInstance().changeTitle("Bibliothèque | " + button.getText() + " - Connecté en tant que " + (Connexion.isAdminMode() ? "Admin" : coStu.getPrenom() + " " + coStu.getNom()));

        for (JButton jbutton : buttons) {
            if (!jbutton.getText().equalsIgnoreCase(button.getText())) {
                jbutton.setBackground(Couleurs.BLEU_FONCE.getCouleur());
            } else {
                button.setBackground(Couleurs.BLEU_SOMBRE.getCouleur());
            }
        }
    }
}
