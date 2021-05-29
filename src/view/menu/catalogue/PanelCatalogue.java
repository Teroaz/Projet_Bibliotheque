package view.menu.catalogue;

import controller.Catalogue;
import model.Livre;
import model.design.Couleurs;

import javax.swing.*;
import java.awt.*;

public class PanelCatalogue extends JPanel {

    private final JButton boutonAjout = new JButton("Ajouter");
    private final JButton boutonSuppression = new JButton("Supprimer");
    private final JButton boutonReservation = new JButton("Réserver");
    private final JButton boutonEmprunt = new JButton("Emprunt");

    private final JTextField texteRecherche = new JTextField(15);

    private final JComboBox<String> choixRecherche = new JComboBox<>(new String[]{"titre", "auteur"});

    private final JPanel gestion = new JPanel();
    private final JPanel recherche = new JPanel();
    private final JPanel affichage = new JPanel();


    private final JLabel label1;

    private final Catalogue catController;

    private final JTableCatalogue tableCatalogue;
    private final ModeleCatalogue modeleCatalogue;

    public PanelCatalogue(Catalogue catController) {
        this.catController = catController;

        setLayout(new BorderLayout());
        gestion.setLayout(new GridLayout(4, 1,5,20));

        modeleCatalogue = new ModeleCatalogue(catController);
        tableCatalogue = new JTableCatalogue(modeleCatalogue, catController);
        modeleCatalogue.registerListeners(tableCatalogue);

        boutonAjout.setActionCommand("ajout");
        boutonSuppression.setActionCommand("suppression");
        boutonEmprunt.setActionCommand("emprunt");
        boutonReservation.setActionCommand("reservation");

        affichage.add(tableCatalogue.getScrollPane());
        affichage.setBorder(BorderFactory.createTitledBorder("Catalogue"));

        gestion.add(boutonAjout);
        gestion.add(boutonSuppression);
        gestion.add(boutonReservation);
        gestion.add(boutonEmprunt);
        gestion.setBorder(BorderFactory.createTitledBorder("Gestion du catalogue"));

        label1 = new JLabel("Je recherche les livres dont le");
        recherche.add(label1);

        recherche.add(choixRecherche);
        choixRecherche.setSelectedIndex(0);
        choixRecherche.addActionListener(e -> {
            label1.setText("Je recherche les livres dont l" + (choixRecherche.getSelectedIndex() == 0 ? "e" : "'"));
            texteRecherche.setText("");
            modeleCatalogue.updateCatalogue(Livre.catalogue.values());
        });

        JLabel label2 = new JLabel("contient ");
        recherche.add(label2);

        recherche.add(texteRecherche);
        recherche.setBorder(BorderFactory.createTitledBorder("Recherche d'un livre"));

        affichage.setBackground(Couleurs.BLEU_CLAIR.getCouleur());
        gestion.setBackground(Couleurs.BLEU_CLAIR.getCouleur());
        recherche.setBackground(Couleurs.BLEU_CLAIR.getCouleur());

        add(affichage, BorderLayout.CENTER);

        add(gestion, BorderLayout.EAST);

        add(recherche, BorderLayout.NORTH);

        setBackground(Couleurs.BLEU_CLAIR.getCouleur());

        enregistreEcouteur();
    }

    public Catalogue getCatController() {
        return catController;
    }

    public void enregistreEcouteur() {
        boutonAjout.addActionListener(catController);
        boutonSuppression.addActionListener(catController);
        boutonReservation.addActionListener(catController);
        boutonEmprunt.addActionListener(catController);

        texteRecherche.addKeyListener(catController);
    }

    public JTableCatalogue getTableCatalogue() {
        return tableCatalogue;
    }

    public ModeleCatalogue getModeleCatalogue() {
        return modeleCatalogue;
    }

    public JButton getBoutonAjout() {
        return boutonAjout;
    }

    public JButton getBoutonEmprunt() {
        return boutonEmprunt;
    }

    public JButton getBoutonReservation() {
        return boutonReservation;
    }

    public JButton getBoutonSuppression() {
        return boutonSuppression;
    }

    public JTextField getTexteRecherche() {
        return texteRecherche;
    }

    public JPanel getAffichage() {
        return affichage;
    }

    public JPanel getGestion() {
        return gestion;
    }

    public JPanel getRecherche() {
        return recherche;
    }

    public JComboBox<String> getChoixRecherche() {
        return choixRecherche;
    }

    public JLabel getLabel1() {
        return label1;
    }

    @Override
    public Insets getInsets() {
        return new Insets(20, 20, 20, 20);
    }
}
