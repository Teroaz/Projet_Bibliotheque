package view.menu.emprunts_reservations.emprunt;

import model.Emprunt;
import model.Etudiant;
import model.Livre;
import model.design.Couleurs;
import utils.swing_utils.JFrameUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DialogModificationEmprunt extends JDialog implements ActionListener {

    JButton boutonOk = new JButton("OK");
    JButton boutonAnnuler = new JButton("Annuler");

    JTextField texteJours = new JTextField(15);

    JPanel panel = new JPanel();

    public DialogModificationEmprunt() {
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
        setTitle("Modification d'un emprunt");
        setSize(400, 300);
        setLocation(JFrameUtils.centerFrameCoords(getWidth(), getHeight()));

        panel.setLayout(new GridLayout(2,2,20,120));

        JLabel labelJour = new JLabel("Nombre de jour(s) supplémentaires");

        boutonAnnuler.addActionListener(this);
        boutonOk.addActionListener(this);

        panel.add(labelJour);
        panel.add(texteJours);
        panel.add(boutonOk);
        panel.add(boutonAnnuler);

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
            setVisible(false);
        }
    }
}
