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
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;


        JLabel labelTitre = new JLabel("Bibliothèque", JLabel.CENTER);
        labelTitre.setFont(new Font("Georgia", Font.ITALIC | Font.BOLD, 60));
        add(labelTitre, gbc);

        gbc.gridy = 1;
        add(Box.createRigidArea(new Dimension(0, 100)), gbc);

        gbc.gridy = 2;
        labelDate = new JLabel(DateUtils.toStringDate(new Date()), JLabel.CENTER);
        labelDate.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 25));
        add(labelDate, gbc);

        gbc.gridy = 3;
        labelHeure = new JLabel(DateUtils.toStringHeure(new Date()), JLabel.CENTER);
        labelHeure.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 25));
        add(labelHeure, gbc);

        refreshDateHeure();

        setBackground(Couleurs.VIOLET.getCouleur());
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
