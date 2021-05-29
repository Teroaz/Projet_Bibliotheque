package controller;

import model.Auteur;
import model.Livre;
import view.menu.catalogue.PanelCatalogue;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Catalogue implements MouseListener, ActionListener, KeyListener {

    private int selectedIndex = 0;
    private final PanelCatalogue panelCatalogue;

    public Catalogue() {
        panelCatalogue = new PanelCatalogue(this);
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
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == panelCatalogue.getTexteRecherche()) {
            String saisieRecherche = panelCatalogue.getTexteRecherche().getText();

            ArrayList<Livre> livres = new ArrayList<>();

            if (panelCatalogue.getChoixRecherche().getSelectedIndex() == 0) {
                livres = Livre.rechercherLivres(saisieRecherche);
            } else {
                ArrayList<Auteur> auteurs = Auteur.rechercherAuteurs(saisieRecherche);
                for (Auteur auteur : auteurs) {
                    livres.addAll(auteur.getLivres());
                }
            }
            panelCatalogue.getModeleCatalogue().updateCatalogue(livres);
        }
    }

    public void onTableSelection(ListSelectionModel selectionModel) {
        int newSelectedIndex = selectionModel.getLeadSelectionIndex();
        if (selectedIndex == newSelectedIndex) return;

        System.out.println(Livre.catalogue.get(selectionModel.getLeadSelectionIndex() + 1));
        selectedIndex = newSelectedIndex;
    }

    public Livre getTableSelectedLivre() {
        return Livre.catalogue.get(selectedIndex);
    }
}
