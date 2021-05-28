package view.menu.catalogue;

import model.Livre;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Collection;

public class ModeleCatalogue extends DefaultTableModel {

    private final String[] intitulesColonnes = {"ID", "Titre", "Auteur", "Nombre d'exemplaires"};

    int nbLivres;

    public ModeleCatalogue() {

        Collection<Livre> livres = Livre.catalogue.values();
        nbLivres = livres.size();

        setColumnCount(intitulesColonnes.length);
        setColumnIdentifiers(intitulesColonnes);

        setRowCount(nbLivres);

        updateCatalogue(livres);
    }

    public String[] getIntitulesColonnes() {
        return intitulesColonnes;
    }

    public boolean isCellEditable(int ligne, int col) {
        return false;
    }


    public void updateCatalogue(Collection<Livre> livres) {
        ArrayList<Livre> livresArr = new ArrayList<>(livres);
        for (int i = 0; i < livresArr.size(); i++) {
            Livre livre = livresArr.get(i);

            setValueAt(livre.getIdLivre(), i, 0);
            setValueAt(livre.getTitre(), i, 1);
            setValueAt(livre.getAuteur().auteurNP(), i, 2);
            setValueAt(livre.getExemplaires().size(), i, 3);
        }
    }

    public Class getColumnClass(int i) {
        if (i == 0 || i == 3)
            return Integer.class;
        return String.class;
    }

    /*
    public void addLivre(Livre parLivre) {
        nbLivres++;
        setRowCount(nbLivres);
        setValueAt(parLivre.getIdLivre(), ligne, 0);
        setValueAt(parLivre.getTitre(), ligne, 1);
        setValueAt(parLivre.getAuteur(), ligne, 2);
        setValueAt(parLivre.getExemplaires().size(), ligne, 3);
    }*/
}
