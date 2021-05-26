package view;

import model.Livre;

import javax.swing.table.DefaultTableModel;
import java.util.Collection;

public class ModeleLivre extends DefaultTableModel {

    private final String[] intitulesColonnes = {"ID", "Titre", "Auteur", "Nombre d'exemplaires"};

    public ModeleLivre(Collection<Livre> parLivres) {

        setColumnCount(intitulesColonnes.length);
        setRowCount(100);

        int ligne = 0;

        Collection<Livre> livres = parLivres;
        for (Livre livre : livres) {
            setValueAt(livre.getIdLivre(), ligne, 0);
            setValueAt(livre.getTitre(), ligne, 1);
            setValueAt(livre.getAuteur(), ligne, 2);
            setValueAt(livre.getExemplaires().size(), ligne, 3);
            ligne++;
        }
    }

    public String[] getIntitulesColonnes() {
        return intitulesColonnes;
    }
}
