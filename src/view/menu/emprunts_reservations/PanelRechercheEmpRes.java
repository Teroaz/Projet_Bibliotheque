package view.menu.emprunts_reservations;

import controller.EmpruntReservation;
import model.design.Couleurs;

import javax.swing.*;

public class PanelRechercheEmpRes extends JPanel {

    JLabel labelRecherche = new JLabel("Je recherche un ");
    JLabel labelFait = new JLabel(" du livre ");
    private JComboBox choixEmpruntReservation;
    private final JTextField texteRecherche;

    private final EmpruntReservation empResController;

    public PanelRechercheEmpRes() {

        empResController = EmpruntReservation.getInstance();

        texteRecherche = new JTextField(20);

        String [] choix = {"emprunt", "réservation"};
        choixEmpruntReservation = new JComboBox(choix);

        add(labelRecherche);
        add(choixEmpruntReservation);
        add(labelFait);
        add(texteRecherche);

        setBorder(BorderFactory.createTitledBorder("Recherche"));
        setBackground(Couleurs.BLEU_CLAIR.getCouleur());

        texteRecherche.addKeyListener(empResController);
        choixEmpruntReservation.addMouseListener(empResController);
    }

    public JComboBox getChoixEmpruntReservation() {
        return choixEmpruntReservation;
    }

    public JLabel getLabelRecherche() {
        return labelRecherche;
    }

    public JLabel getLabelFait() {
        return labelFait;
    }

    public JTextField getTexteRecherche() {
        return texteRecherche;
    }

    public EmpruntReservation getEmpResController() {
        return empResController;
    }
}
