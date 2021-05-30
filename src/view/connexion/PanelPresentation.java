package view.connexion;

import model.design.Couleurs;
import utils.DateUtils;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class PanelPresentation extends JPanel {

    private final JLabel labelDate;
    private final JLabel labelHeure;

    public PanelPresentation() {
        setLayout(new GridLayout(3, 1, 15, 15));
        setBackground(Couleurs.VIOLET.getCouleur());

        JLabel labelTitre = new JLabel("Bibliothèque", JLabel.CENTER);
        labelTitre.setFont(new Font("Georgia", Font.ITALIC | Font.BOLD, 60));
        add(labelTitre);

        labelDate = new JLabel(DateUtils.toStringDate(new Date()), JLabel.CENTER);
        labelDate.setFont(new Font("Arial", Font.PLAIN, 25));
        add(labelDate);

        labelHeure = new JLabel(DateUtils.toStringHeure(new Date()), JLabel.CENTER);
        labelHeure.setFont(new Font("Arial", Font.PLAIN, 25));
        add(labelHeure);

        refreshDateHeure();

    }

    private void refreshDateHeure() {
        java.util.Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Date currDate = new Date();
                labelDate.setText(DateUtils.toStringDate(currDate));
                labelHeure.setText(DateUtils.toStringHeure(currDate));
            }
        }, 0, 1000);
    }
}
