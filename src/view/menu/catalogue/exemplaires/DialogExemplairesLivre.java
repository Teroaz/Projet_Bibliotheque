package view.menu.catalogue.exemplaires;

import controller.Catalogue;
import controller.PanelSwitcher;
import model.Etat;
import model.Exemplaire;
import model.Livre;
import model.design.Couleurs;
import utils.swing_utils.JFrameUtils;
import view.FenetreBibliotheque;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogExemplairesLivre extends JDialog implements ActionListener {

    private final int width = 500;
    private final int height = 400;
    JLabel labelTitre;
    JButton boutonNeuf = new JButton("Neuf");
    JButton boutonBon = new JButton("Bon");
    JButton boutonAbime = new JButton("Abime");
    JButton boutonAnnuler = new JButton("Annuler");

    Livre livre;

    public DialogExemplairesLivre() {
        super(FenetreBibliotheque.getInstance(), "Bibliothèque | Catalogue - Gestion des exemplaires", ModalityType.APPLICATION_MODAL);
        setModal(true);

        setSize(width, height);
        setLocation(JFrameUtils.centerFrameCoords(width, height));
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);
        setAutoRequestFocus(true);

        livre = Catalogue.getInstance().getTableSelectedLivre();
        JLabel labelInfo = new JLabel("Ajouter ou supprimer des exemplaires");
        labelTitre = new JLabel(livre.getTitre());

        boutonNeuf.addActionListener(this);
        boutonBon.addActionListener(this);
        boutonAbime.addActionListener(this);
        boutonAnnuler.addActionListener(this);

        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(labelInfo, gbc);

        gbc.gridy++;
        add(labelTitre, gbc);

        gbc.gridy++;
        add(boutonNeuf, gbc);

        gbc.gridx++;
        add(boutonBon, gbc);

        gbc.gridx++;
        add(boutonAbime, gbc);

        gbc.gridy++;
        gbc.gridx--;
        add(boutonAnnuler, gbc);

        setBackground(Couleurs.BLEU_CLAIR.getCouleur());
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == boutonNeuf) {
            Exemplaire.ajoutExemplaire(livre.getId(), Etat.NEUF);
            PanelSwitcher.getMenu().getCatalogue().getPanelCatalogue().getModeleCatalogue().updateCatalogue(Livre.catalogue.values());
            setVisible(false);
        }
        else if (e.getSource() == boutonBon) {
            Exemplaire.ajoutExemplaire(livre.getId(), Etat.BON);
            PanelSwitcher.getMenu().getCatalogue().getPanelCatalogue().getModeleCatalogue().updateCatalogue(Livre.catalogue.values());
            setVisible(false);
        }
        else if (e.getSource() == boutonAbime) {
            Exemplaire.ajoutExemplaire(livre.getId(), Etat.ABIME);
            PanelSwitcher.getMenu().getCatalogue().getPanelCatalogue().getModeleCatalogue().updateCatalogue(Livre.catalogue.values());
            setVisible(false);
        }
        else if (e.getSource() == boutonAnnuler) {
            setVisible(false);
        }
    }
}
