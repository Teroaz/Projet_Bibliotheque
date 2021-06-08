package view.menu.catalogue;

import controller.Catalogue;
import model.Livre;
import model.design.Couleurs;

import javax.swing.*;

public class PanelRechercheCatalogue extends JPanel {

    private final JTextField texteRecherche = new JTextField(15);

    private final JComboBox<String> choixRecherche = new JComboBox<>(new String[]{"titre", "auteur"});

    private final Catalogue catController;
    private final JLabel labelGauche;
    private final JLabel labelDroit;

    public PanelRechercheCatalogue() {
        catController = Catalogue.getInstance();

        labelGauche = new JLabel("Je recherche les livres dont le");

        add(labelGauche);
        add(choixRecherche);

        choixRecherche.setSelectedIndex(0);
        choixRecherche.addActionListener(e -> {
            labelGauche.setText("Je recherche les livres dont l" + (choixRecherche.getSelectedIndex() == 0 ? "e" : "'"));
            texteRecherche.setText("");
            catController.getPanelCatalogue().getModeleCatalogue().updateCatalogue(Livre.catalogue.values());
        });

        labelDroit = new JLabel("est ");

        texteRecherche.addKeyListener(catController);

        add(labelDroit);
        add(texteRecherche);

        setBorder(BorderFactory.createTitledBorder("Recherche"));
        setBackground(Couleurs.BLEU_CLAIR.getCouleur());
    }

    public JComboBox<String> getChoixRecherche() {
        return choixRecherche;
    }

    public JTextField getTexteRecherche() {
        return texteRecherche;
    }
}
