package view.menu.catalogue;

import controller.Catalogue;
import controller.Connexion;
import model.Exemplaire;
import model.Livre;
import model.design.Couleurs;
import utils.swing_utils.JButtonUtils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PanelGestionCatalogue extends JPanel {

    private JButton boutonAjout;
    private final JButton boutonExemplaires;
    private JButton boutonReserver;

    private final Font buttonFont = new Font(Font.SANS_SERIF, Font.BOLD, 16);

    private final Catalogue catController;

    public PanelGestionCatalogue() {
        catController = Catalogue.getInstance();

        GridLayout gridLayout = new GridLayout(6, 1);
        gridLayout.setVgap(25);
        setLayout(gridLayout);

        if (Connexion.isAdminMode()) {
            boutonAjout = new JButton("Ajouter");
            boutonAjout.addActionListener(catController);
            boutonAjout.setActionCommand("ajout");
            boutonAjout.setFont(buttonFont);
            JButtonUtils.beautifyButton(boutonAjout, Couleurs.BLEU_FONCE.getCouleur(), Couleurs.BLEU_CLAIR.getCouleur(), 4);
            add(boutonAjout);
        } else {
            boutonReserver = new JButton("Réserver");
            boutonReserver.addActionListener(catController);
            boutonReserver.setActionCommand("réserver");
            boutonReserver.setFont(buttonFont);
            JButtonUtils.beautifyButton(boutonReserver, Couleurs.BLEU_FONCE.getCouleur(), Couleurs.BLEU_CLAIR.getCouleur(), 4);
            add(boutonReserver);
        }

        boutonExemplaires = new JButton("Exemplaires");
        boutonExemplaires.addActionListener(catController);
        boutonExemplaires.setActionCommand("exemplaires");
        boutonExemplaires.setFont(buttonFont);
        JButtonUtils.beautifyButton(boutonExemplaires, Couleurs.BLEU_FONCE.getCouleur(), Couleurs.BLEU_CLAIR.getCouleur(), 4);
        add(boutonExemplaires);

        disableLivreRelativeButtons();
        setBorder(BorderFactory.createTitledBorder("Menu du catalogue"));
        setBackground(Couleurs.BLEU_CLAIR.getCouleur());
    }


    public void disableLivreRelativeButtons() {
        if (!Connexion.isAdminMode()) {
            boutonReserver.setEnabled(false);
        }

        boutonExemplaires.setText("Exemplaires");
        boutonExemplaires.setEnabled(false);

    }

    public void enableLivreRelativeButtons() {

        Livre selectedLivre = Catalogue.getInstance().getTableSelectedLivre();
        ArrayList<Exemplaire> exemplaires = selectedLivre.getExemplaires();

        if (!Connexion.isAdminMode()) {
            if (exemplaires.size() > 0 && Connexion.getConnectedStudent().peutReserverLivre(selectedLivre)) {
                boutonReserver.setEnabled(true);
            }
        }

        boutonExemplaires.setText(exemplaires.size() + " Exemplaire" + (exemplaires.size() > 1 ? "s" : ""));
        boutonExemplaires.setEnabled(true);
    }

    public JButton getBoutonAjout() {
        return boutonAjout;
    }

    public Catalogue getCatController() {
        return catController;
    }

    public JButton getBoutonExemplaires() {
        return boutonExemplaires;
    }

    public JButton getBoutonReserver() {
        return boutonReserver;
    }

    @Override
    public Insets getInsets() {
        return super.getInsets();
    }
}
