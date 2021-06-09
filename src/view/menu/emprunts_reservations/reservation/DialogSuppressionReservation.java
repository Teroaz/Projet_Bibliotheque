package view.menu.emprunts_reservations.reservation;

import model.Etudiant;
import model.Livre;
import model.Reservation;
import model.design.Couleurs;
import utils.swing_utils.JFrameUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DialogSuppressionReservation extends JDialog implements ActionListener {

    JButton boutonOk = new JButton("OK");
    JButton boutonAnnuler = new JButton("Annuler");

    JPanel panel = new JPanel();

    public DialogSuppressionReservation() {
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
        setTitle("Suppression d'une réservation");
        setSize(400, 150);
        setLocation(JFrameUtils.centerFrameCoords(getWidth(), getHeight()));

        panel.setLayout(new GridBagLayout());

        JLabel labelConfirmation = new JLabel("Êtes-vous sûr(e) de vouloir supprimer la réservation ?", JLabel.CENTER);
        labelConfirmation.setFont(new Font("Arial", Font.PLAIN, 12));

        boutonAnnuler.addActionListener(this);
        boutonOk.addActionListener(this);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 4, 10, 4);
        gbc.anchor = GridBagConstraints.CENTER;

        panel.add(labelConfirmation, gbc);

        gbc.gridy++;
        panel.add(boutonOk, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        panel.add(boutonAnnuler, gbc);

        add(panel);
        setContentPane(panel);

//        setBackground(Couleurs.BLEU_CLAIR.getCouleur());
//        panel.setBackground(getBackground());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == boutonAnnuler) {
            setVisible(false);
        }
        if (e.getSource() == boutonOk) {
//            Reservation.suppressionReservation();
            setVisible(false);
        }
    }
}
