package view.menu.emprunts_reservations.emprunt;

import controller.EmpruntReservation;
import model.design.Couleurs;
import view.menu.etudiant.JTableEtudiant;
import view.menu.etudiant.ModeleEtudiant;

import javax.swing.*;
import java.awt.*;

public class PanelEmprunt extends JPanel {

    private final JButton boutonAjout = new JButton("Ajouter");
//    private final JButton boutonModif = new JButton("Modifier");
//    private final JButton boutonSupp = new JButton("Supprimer");

    private final JTableEmprunt tableEmprunt;

    private final JPanel panelAffiche = new JPanel();
    private final JPanel panelGestion = new JPanel();


    public PanelEmprunt() {
        setLayout(new BorderLayout());

//        panelGestion.setLayout(new GridLayout(1, 3, 50, 0));

        tableEmprunt = new JTableEmprunt(new ModeleEmprunt());

        boutonAjout.setActionCommand("ajoutEmp");
//        boutonModif.setActionCommand("modificationEmp");
//        boutonModif.setEnabled(false);
//        boutonSupp.setActionCommand("suppressionEmp");
//        boutonSupp.setEnabled(false);
        enregistreEcouteur();

        panelAffiche.setBackground(Couleurs.BLEU_CLAIR.getCouleur());
        panelGestion.setBackground(Couleurs.BLEU_CLAIR.getCouleur());

        panelAffiche.add(tableEmprunt.getScrollPane());
        panelGestion.add(boutonAjout);
//        panelGestion.add(boutonModif);
//        panelGestion.add(boutonSupp);

        add(panelAffiche, BorderLayout.CENTER);
        add(panelGestion, BorderLayout.SOUTH);

        setBorder(BorderFactory.createTitledBorder("Emprunts"));
        setBackground(Couleurs.BLEU_CLAIR.getCouleur());
    }

    public void enregistreEcouteur () {
        boutonAjout.addActionListener(EmpruntReservation.getInstance());
//        boutonModif.addActionListener(EmpruntReservation.getInstance());
//        boutonSupp.addActionListener(EmpruntReservation.getInstance());
    }

    public JTableEmprunt getTableEmprunt() {
        return tableEmprunt;
    }

//    public ModeleEmprunt getModeleEmprunt() { return model}

    public JButton getBoutonAjout() {
        return boutonAjout;
    }

//    public JButton getBoutonModif() {
//        return boutonModif;
//    }
//
//    public JButton getBoutonSupp() {
//        return boutonSupp;
//    }

    public JPanel getPanelGestion() {
        return panelGestion;
    }

    public JPanel getPanelAffiche() {
        return panelAffiche;
    }
}
