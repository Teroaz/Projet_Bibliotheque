package view.menu.etudiant;

import controller.GestionEtudiant;
import model.design.Couleurs;

import javax.swing.*;
import java.awt.*;

public class PanelEtudiant extends JPanel {

    private final JButton boutonAjout = new JButton("Ajouter");
    private final JButton boutonModif = new JButton("Modifier");
    private final JButton boutonSuppression = new JButton("Supprimer");

    private final PanelRechercheEtudiant panelRechercheEtudiant;

    private final JPanel panelGestion = new JPanel();
    private final JPanel panelAffichage = new JPanel();

    private final GestionEtudiant etuController;

    private final JTableEtudiant tableEtudiant;
    private final ModeleEtudiant modeleEtudiant;

    public PanelEtudiant() {

        etuController = GestionEtudiant.getInstance();

        setLayout(new BorderLayout());
        panelGestion.setLayout(new GridLayout(4, 1, 5, 20));

        modeleEtudiant = new ModeleEtudiant(etuController);
        tableEtudiant = new JTableEtudiant(modeleEtudiant, etuController);
        modeleEtudiant.registerListeners(tableEtudiant);

        boutonModif.setEnabled(false);
        boutonSuppression.setEnabled(false);

        boutonAjout.setActionCommand("ajout");
        boutonModif.setActionCommand("modification");
        boutonSuppression.setActionCommand("suppression");

        panelRechercheEtudiant = new PanelRechercheEtudiant();

        panelAffichage.add(tableEtudiant.getScrollPane());
        panelAffichage.setBorder(BorderFactory.createTitledBorder("Étudiants"));

        panelGestion.add(boutonAjout);
        panelGestion.add(boutonModif);
        panelGestion.add(boutonSuppression);
        panelGestion.setBorder(BorderFactory.createTitledBorder("Gestion des étudiants"));

        panelAffichage.setBackground(Couleurs.BLEU_CLAIR.getCouleur());
        panelGestion.setBackground(Couleurs.BLEU_CLAIR.getCouleur());

        add(panelAffichage, BorderLayout.CENTER);

        add(panelGestion, BorderLayout.EAST);

        add(panelRechercheEtudiant, BorderLayout.NORTH);

        setBackground(Couleurs.BLEU_CLAIR.getCouleur());

        enregistreEcouteur();
    }

    public GestionEtudiant getEtuController() {
        return etuController;
    }

    public void enregistreEcouteur() {
        boutonAjout.addActionListener(etuController);
        boutonModif.addActionListener(etuController);
        boutonSuppression.addActionListener(etuController);
    }

    public JTableEtudiant getTableEtudiant() {
        return tableEtudiant;
    }

    public ModeleEtudiant getModeleEtudiant() {
        return modeleEtudiant;
    }

    public JButton getBoutonAjout() {
        return boutonAjout;
    }

    public JButton getBoutonModif() {
        return boutonModif;
    }

    public JButton getBoutonSuppression() {
        return boutonSuppression;
    }

    public JPanel getPanelAffichage() {
        return panelAffichage;
    }

    public JPanel getPanelGestion() {
        return panelGestion;
    }

    public PanelRechercheEtudiant getPanelRechercheEtudiant() {
        return panelRechercheEtudiant;
    }

    @Override
    public Insets getInsets() {
        return new Insets(20, 20, 20, 20);
    }
}
