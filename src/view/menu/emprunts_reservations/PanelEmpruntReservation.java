package view.menu.emprunts_reservations;

import controller.EmpruntReservation;
import controller.PanelSwitcher;
import model.design.Couleurs;
import view.menu.PanelNavigation;
import view.menu.catalogue.JTableCatalogue;
import view.menu.catalogue.ModeleCatalogue;

import javax.swing.*;
import java.awt.*;

public class PanelEmpruntReservation extends JPanel {
//    public PanelEmprunt(Array)

    JButton boutonModificationEmp = new JButton("Modifier");
    JButton boutonAjoutEmp = new JButton("Ajouter");
    JButton boutonSuppressionEmp = new JButton("Supprimer");
    JButton boutonModificationRes = new JButton("Modifier");
    JButton boutonAjoutRes = new JButton("Ajouter");
    JButton boutonSuppressionRes = new JButton("Supprimer");
    JButton boutonRecherche = new JButton("Recherche");

    JTextField texteRecherche = new JTextField(20);

    JPanel afficheEmprunt = new JPanel();
    JPanel gestionEmprunt = new JPanel();
    JPanel panelEmprunt = new JPanel();
    JPanel afficheReservation = new JPanel();
    JPanel gestionReservation = new JPanel();
    JPanel panelReservation = new JPanel();
    JPanel recherche = new JPanel();
    JPanel panelEmpruntReservation = new JPanel();

    private final EmpruntReservation empControleur;

    public PanelEmpruntReservation(EmpruntReservation empControleur) {
        this.empControleur = empControleur;

        gestionEmprunt.setLayout(new GridLayout(1,3,50,0));
        gestionReservation.setLayout(new GridLayout(1,3,50,0));
        panelEmpruntReservation.setLayout(new GridLayout(1,2,10,0));
        panelEmprunt.setLayout(new BorderLayout());
        panelReservation.setLayout(new BorderLayout());
        setLayout(new BorderLayout());

        PanelNavigation panelNavigation = PanelSwitcher.getMenu().getPanelNavigation();

        JTableEmprunt tableEmprunt = new JTableEmprunt(new ModeleEmprunt(), empControleur);
        JTableReservation tableReservation = new JTableReservation(new ModeleReservation(), empControleur);

        boutonModificationEmp.setActionCommand("modificationEmp");
        boutonAjoutEmp.setActionCommand("ajoutEmp");
        boutonSuppressionEmp.setActionCommand("suppressionEmp");
        boutonModificationRes.setActionCommand("modificationRes");
        boutonAjoutRes.setActionCommand("ajoutRes");
        boutonSuppressionRes.setActionCommand("suppressionRes");
        boutonRecherche.setActionCommand("recherche");

        afficheEmprunt.add(tableEmprunt.getScrollPane());
        gestionEmprunt.add(boutonModificationEmp);
        gestionEmprunt.add(boutonAjoutEmp);
        gestionEmprunt.add(boutonSuppressionEmp);

        panelEmprunt.add(afficheEmprunt, BorderLayout.CENTER);
        panelEmprunt.add(gestionEmprunt, BorderLayout.SOUTH);
        panelEmprunt.setBorder(BorderFactory.createTitledBorder("Emprunts"));

        afficheReservation.add(tableReservation.getScrollPane());
        gestionReservation.add(boutonModificationRes);
        gestionReservation.add(boutonAjoutRes);
        gestionReservation.add(boutonSuppressionRes);

        panelReservation.add(afficheReservation, BorderLayout.CENTER);
        panelReservation.add(gestionReservation, BorderLayout.SOUTH);
        panelReservation.setBorder(BorderFactory.createTitledBorder("Réservations"));

        recherche.add(texteRecherche);
        recherche.add(boutonRecherche);
        recherche.setBorder(BorderFactory.createTitledBorder("Recherche"));

        panelEmpruntReservation.add(panelEmprunt);
        panelEmpruntReservation.add(panelReservation);

        add(recherche, BorderLayout.CENTER);
        add(panelNavigation, BorderLayout.NORTH);
        add(panelEmpruntReservation, BorderLayout.SOUTH);

        setBackground(Couleurs.BLEU_CLAIR.getCouleur());
        afficheEmprunt.setBackground(getBackground());
        gestionEmprunt.setBackground(getBackground());
        panelEmprunt.setBackground(getBackground());
        afficheReservation.setBackground(getBackground());
        gestionReservation.setBackground(getBackground());
        panelReservation.setBackground(getBackground());
        recherche.setBackground(getBackground());
        panelEmpruntReservation.setBackground(getBackground());
    }

    public EmpruntReservation getEmpControleur() {
        return empControleur;
    }

}
