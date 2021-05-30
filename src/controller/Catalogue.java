package controller;

import model.Auteur;
import model.Livre;
import view.menu.catalogue.PanelCatalogue;
import view.menu.catalogue.PanelRechercheCatalogue;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Catalogue implements ActionListener, KeyListener {

    private int selectedIndex = 0;
    private static Catalogue instance;

    private final PanelCatalogue panelCatalogue;

    public Catalogue() {
        instance = this;
        panelCatalogue = new PanelCatalogue();
    }

    public static Catalogue getInstance() {
        return instance;
    }

    public PanelCatalogue getPanelCatalogue() {
        return panelCatalogue;
    }

    public void ActionListener(ActionEvent evt) {


        if (evt.getActionCommand().equals("ajout")) {

        } else if (evt.getActionCommand().equals("suppression")) {

        } else if (evt.getActionCommand().equals("emprunt")) {

        } else if (evt.getActionCommand().equals("reservation")) {

        } else if (evt.getActionCommand().equals("recherche")) {

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        PanelRechercheCatalogue panelRechercheCatalogue = panelCatalogue.getPanelRechercheCatalogue();

        if (e.getSource() == panelRechercheCatalogue.getTexteRecherche()) {
            String saisieRecherche = panelRechercheCatalogue.getTexteRecherche().getText();

            ArrayList<Livre> livres = new ArrayList<>();

            if (panelRechercheCatalogue.getChoixRecherche().getSelectedIndex() == 0) {
                livres = Livre.rechercherLivres(saisieRecherche);
            } else {
                ArrayList<Auteur> auteurs = Auteur.rechercherAuteurs(saisieRecherche);
                for (Auteur auteur : auteurs) {
                    livres.addAll(auteur.getLivres());
                }
                livres.sort((livre1, livre2) -> livre1.getId() - livre2.getId());
            }
            panelCatalogue.getModeleCatalogue().updateCatalogue(livres);
        }
    }

    public void onTableSelection(ListSelectionModel selectionModel) {

        if (selectionModel.isSelectionEmpty()) {
            selectionModel.clearSelection();
            selectedIndex = 0;
            panelCatalogue.getBoutonModif().setEnabled(false);
            return;
        }

        System.out.println(selectionModel.toString());
        int newSelectedIndex = selectionModel.getMinSelectionIndex();

        if (selectedIndex == newSelectedIndex) return;

        selectedIndex = newSelectedIndex;
        panelCatalogue.getBoutonModif().setEnabled(true);
        System.out.println(getTableSelectedLivre());
    }

    public Livre getTableSelectedLivre() {
        return panelCatalogue.getModeleCatalogue().getLivreByRow(selectedIndex);
    }
}
