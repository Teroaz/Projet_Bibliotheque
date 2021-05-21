package view;

import controller.Connexion;
import model.design.Icones;

import javax.swing.*;
import java.awt.*;

public class PanelNavigationOld extends JPanel {

    private final Font buttonFont = new Font(Font.SANS_SERIF, Font.BOLD, 16);

    private final String[] etudiantButtonsLabels = {"Étudiants", "Livres & Exemplaires", "Réservations & Emprunts", "Déconnexion"};
    private final ImageIcon[] etudiantButtonsIcons = {Icones.REPERTOIRE_ETUDIANTS.getIcone()};
    private final JButton[] etudiantButtons = new JButton[etudiantButtonsLabels.length];

    public PanelNavigationOld() {

        GridLayout gridLayout = new GridLayout();
        setLayout(gridLayout);

        gridLayout.setHgap(10);
        gridLayout.setVgap(20);

        if (Connexion.isAdminMode()) {

        } else {
            for (int i = 0; i < etudiantButtonsLabels.length; i++) {
                String intitule = etudiantButtonsLabels[i];
                etudiantButtons[i] = new JButton(intitule);
                etudiantButtons[i].setFont(buttonFont);
                etudiantButtons[i].setMargin(new Insets(10, 0, 10, 0));
                add(etudiantButtons[i]);
            }
        }


    }

    @Override
    public Insets getInsets() {
        return new Insets(10, 20, 10, 20);
    }
}
