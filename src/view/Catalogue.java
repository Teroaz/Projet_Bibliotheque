package view;

import model.Livre;

import javax.swing.*;

public class Catalogue extends JPanel {

    public Catalogue() {

        Livre.catalogue.values().forEach(livre -> add(new LivreCatalogue(livre)));

    }
}
