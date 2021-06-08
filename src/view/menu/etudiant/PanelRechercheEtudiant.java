package view.menu.etudiant;

import controller.GestionEtudiant;
import model.design.Couleurs;

import javax.swing.*;

public class PanelRechercheEtudiant extends JPanel {

    private final JTextField texteRecherche = new JTextField(15);
    private final JComboBox<String> choixRecherche = new JComboBox<>(new String[]{"prénom et nom", "mail"});

    private final GestionEtudiant gestionEtudiant;
    private final JLabel labelGauche;
    private final JLabel labelDroit;

    public PanelRechercheEtudiant() {

        gestionEtudiant = GestionEtudiant.getInstance();

        labelGauche = new JLabel("Je recherche les étudiants dont le");

        add(labelGauche);
        add(choixRecherche);

        choixRecherche.setSelectedIndex(0);
        choixRecherche.addActionListener(e -> {
            labelGauche.setText("Je recherche les étudiants dont le ");
            texteRecherche.setText("");
//            gestionEtudiant.getPanelCatalogue().getModeleCatalogue().updateCatalogue(Livre.catalogue.values());
        });

        labelDroit = new JLabel("est ");

        texteRecherche.addKeyListener(gestionEtudiant);

        add(labelDroit);
        add(texteRecherche);

        setBorder(BorderFactory.createTitledBorder("Recherche"));
        setBackground(Couleurs.BLEU_CLAIR.getCouleur());
    }

    public JTextField getTexteRecherche() {
        return texteRecherche;
    }

    public JComboBox<String> getChoixRecherche() {
        return choixRecherche;
    }
}
