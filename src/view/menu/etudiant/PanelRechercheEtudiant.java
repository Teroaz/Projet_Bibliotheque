package view.menu.etudiant;

import controller.GestionEtudiant;
import model.design.Couleurs;

import javax.swing.*;

public class PanelRechercheEtudiant extends JPanel {
    private final JButton boutonRecherche;
    private final JTextField texteRecherche;

    private final GestionEtudiant etuController;

    public PanelRechercheEtudiant() {

        etuController = GestionEtudiant.getInstance();

        boutonRecherche = new JButton("Recherche");
        boutonRecherche.setActionCommand("recherche");

        texteRecherche = new JTextField(20);

        add(texteRecherche);
        add(boutonRecherche);

        setBorder(BorderFactory.createTitledBorder("Recherche"));
        setBackground(Couleurs.BLEU_CLAIR.getCouleur());

//        texteRecherche.addKeyListener(empResController);
    }

    public JButton getBoutonRecherche() {
        return boutonRecherche;
    }

    public JTextField getTexteRecherche() {
        return texteRecherche;
    }

    public GestionEtudiant getEtuController() {
        return etuController;
    }
}
