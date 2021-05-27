package view;

import model.Livre;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Collection;

public class ModeleLivre extends DefaultTableModel {

    private final String[] intitulesColonnes = {"ID", "Titre", "Auteur", "Nombre d'exemplaires"};
    int nbLivres;
    int ligne;

    public ModeleLivre(Collection<Livre> parLivres) {
        nbLivres = parLivres.size();
        setColumnCount(intitulesColonnes.length);
        setRowCount(nbLivres);
        setColumnIdentifiers(intitulesColonnes);

        ligne = 0;

        Collection<Livre> livres = parLivres;
        for (Livre livre : livres) {
            setValueAt(livre.getIdLivre(), ligne, 0);
            setValueAt(livre.getTitre(), ligne, 1);
            setValueAt(livre.getAuteur().auteurNP(), ligne, 2);
            setValueAt(livre.getExemplaires().size(), ligne, 3);
            ligne++;
        }

        recupererDonneesLivre();

    }


    public String[] getIntitulesColonnes() {
        return intitulesColonnes;
    }

    public void addLivre(Livre parLivre) {
        nbLivres++;
        ligne++;
        setRowCount(nbLivres);
        setValueAt(parLivre.getIdLivre(), ligne, 0);
        setValueAt(parLivre.getTitre(), ligne, 1);
        setValueAt(parLivre.getAuteur(), ligne, 2);
        setValueAt(parLivre.getExemplaires().size(), ligne, 3);
    }

    public void recupererDonneesLivre() {

        ArrayList<Object[]> donnees = new ArrayList<>();

        for (Livre livre : Livre.catalogue.values()) {
            ArrayList<Object> donnesLivres = new ArrayList<>();
            donnesLivres.add(livre.getIdLivre());
            donnesLivres.add(livre.getTitre());
            donnesLivres.add(livre.getAuteur().auteurNP());
            donnees.add(donnesLivres.toArray());
        }


        // return donnees.toArray();
    }

}
