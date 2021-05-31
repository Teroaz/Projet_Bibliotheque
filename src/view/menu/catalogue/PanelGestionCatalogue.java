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
    private JButton boutonModif;
    private final JButton boutonExemplaires;
    private final JButton boutonCommentaires;
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

            boutonModif = new JButton("Modifier");
            boutonModif.addActionListener(catController);
            boutonModif.setActionCommand("modif");
            boutonModif.setFont(buttonFont);
            JButtonUtils.beautifyButton(boutonModif, Couleurs.BLEU_FONCE.getCouleur(), Couleurs.BLEU_CLAIR.getCouleur(), 4);
            add(boutonModif);
        } else {
            boutonReserver = new JButton("Réserver");
            boutonReserver.addActionListener(catController);
            boutonReserver.setActionCommand("reserver");
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

        boutonCommentaires = new JButton("Commentaires");
        boutonCommentaires.addActionListener(catController);
        boutonCommentaires.setActionCommand("commentaires");
        boutonCommentaires.setFont(buttonFont);
        JButtonUtils.beautifyButton(boutonCommentaires, Couleurs.BLEU_FONCE.getCouleur(), Couleurs.BLEU_CLAIR.getCouleur(), 4);
        add(boutonCommentaires);

        disableLivreRelativeButtons();
        setBorder(BorderFactory.createTitledBorder("Menu du catalogue"));
        setBackground(Couleurs.BLEU_CLAIR.getCouleur());
    }


    public void disableLivreRelativeButtons() {
        if (Connexion.isAdminMode()) {
            boutonModif.setEnabled(false);
        } else {
            boutonReserver.setEnabled(false);
        }

        boutonExemplaires.setText("Exemplaires");
        boutonExemplaires.setEnabled(false);

        boutonCommentaires.setText("Commentaires");
        boutonCommentaires.setEnabled(false);
    }

    public void enableLivreRelativeButtons() {

        Livre selectedLivre = Catalogue.getInstance().getTableSelectedLivre();
        ArrayList<Exemplaire> exemplaires = selectedLivre.getExemplaires();

        if (Connexion.isAdminMode()) {
            boutonModif.setEnabled(true);
        } else {
            if (exemplaires.size() > 0) {
                boutonReserver.setEnabled(true);
            }
        }
        if (exemplaires.size() > 0) {
            boutonExemplaires.setText(exemplaires.size() + " Exemplaires");
            boutonExemplaires.setEnabled(true);
        }
        boutonCommentaires.setEnabled(true);
    }

    public JButton getBoutonModif() {
        return boutonModif;
    }

    public JButton getBoutonAjout() {
        return boutonAjout;
    }

    public Catalogue getCatController() {
        return catController;
    }

    public JButton getBoutonCommentaires() {
        return boutonCommentaires;
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
