package view.menu.emprunts_reservations;

import controller.EmpruntReservation;
import model.design.Couleurs;

import javax.swing.*;

public class PanelRechercheEmpRes extends JPanel {

    private final JButton boutonRecherche;
    private final JTextField texteRecherche;

    private final EmpruntReservation empResController;

    public PanelRechercheEmpRes() {

        empResController = EmpruntReservation.getInstance();

        boutonRecherche = new JButton("Recherche");
        boutonRecherche.setActionCommand("recherche");

        texteRecherche = new JTextField(20);

        add(texteRecherche);
        add(boutonRecherche);

        setBorder(BorderFactory.createTitledBorder("Recherche"));
        setBackground(Couleurs.BLEU_CLAIR.getCouleur());

        texteRecherche.addKeyListener(empResController);
    }

    public JButton getBoutonRecherche() {
        return boutonRecherche;
    }

    public JTextField getTexteRecherche() {
        return texteRecherche;
    }

    public EmpruntReservation getEmpResController() {
        return empResController;
    }
}
