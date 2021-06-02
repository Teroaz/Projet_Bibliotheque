package view.menu.emprunts_reservations.reservation;

import controller.EmpruntReservation;
import model.design.Couleurs;

import javax.swing.*;
import java.awt.*;

public class PanelReservation extends JPanel {

    private final JButton boutonAjout = new JButton("Ajouter");
    private final JButton boutonSupp = new JButton("Supprimer");

    private final JTableReservation tableReservation;

    private final JPanel panelAffiche = new JPanel();
    private final JPanel panelGestion = new JPanel();

    public PanelReservation() {
        setLayout(new BorderLayout());

        panelGestion.setLayout(new GridLayout(1, 3, 50, 0));

        tableReservation = new JTableReservation(new ModeleReservation());

        boutonAjout.setActionCommand("ajoutRes");
        boutonSupp.setActionCommand("suppressionRes");
//        boutonSupp.setEnabled(false);
        enregistreEcouteur();

        panelAffiche.setBackground(Couleurs.BLEU_CLAIR.getCouleur());
        panelGestion.setBackground(Couleurs.BLEU_CLAIR.getCouleur());

        panelAffiche.add(tableReservation.getScrollPane());
        panelGestion.add(boutonAjout);
        panelGestion.add(boutonSupp);

        add(panelAffiche, BorderLayout.CENTER);
        add(panelGestion, BorderLayout.SOUTH);

        setBorder(BorderFactory.createTitledBorder("Réservations"));
        setBackground(Couleurs.BLEU_CLAIR.getCouleur());
    }

    public void enregistreEcouteur () {
        boutonAjout.addActionListener(EmpruntReservation.getInstance());
        boutonSupp.addActionListener(EmpruntReservation.getInstance());
    }

    public JTableReservation getTableReservation() {
        return tableReservation;
    }

    public JButton getBoutonAjout() {
        return boutonAjout;
    }

    public JButton getBoutonSupp() {
        return boutonSupp;
    }

    public JPanel getPanelGestion() {
        return panelGestion;
    }

    public JPanel getPanelAffiche() {
        return panelAffiche;
    }
}
