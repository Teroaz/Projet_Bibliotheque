package view;

import controller.Catalogue;
import model.Livre;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;

public class PanelCatalogue extends JPanel {

    JButton boutonAjout = new JButton("+");
    JButton boutonSuppression = new JButton("-");
    JButton boutonReservation = new JButton("R");
    JButton boutonEmprunt = new JButton("E");
    JButton boutonRecherche = new JButton("Recherche");

    JTextField texteRecherche = new JTextField(15);

    JPanel gestion = new JPanel();
    JPanel recherche = new JPanel();
    JPanel affichage = new JPanel();
    private final Catalogue catController;

    public PanelCatalogue(Catalogue catController) {
        setLayout(new BorderLayout());
        gestion.setLayout(new GridLayout(4, 1));
        this.catController = catController;
        String[] intitulesColonnes = {"ID", "Titre", "Auteur", "Nombre d'exemplaires"};
        Collection<Livre> livres = Livre.catalogue.values();

        ModeleLivre modele = new ModeleLivre(livres);

        JTable tableLivre = new JTable(new ModeleLivre(livres));
        JScrollPane scrollPane = new JScrollPane(tableLivre,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        tableLivre.getTableHeader().setBackground(new Color(198, 173, 137, 255));
        tableLivre.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        //tableSemaine.getTableHeader().setForeground(new Color(0,0,0));
        tableLivre.setRowHeight(30);
        //tableLivre.setDefaultRenderer(String.class, new CelluleRenderer());
        //tableLivre.setDefaultRenderer(Integer.class, new CelluleRenderer());

        scrollPane.setPreferredSize(new Dimension(500, 500));

        boutonAjout.setActionCommand("ajout");
        boutonSuppression.setActionCommand("suppression");
        boutonEmprunt.setActionCommand("emprunt");
        boutonReservation.setActionCommand("reservation");
        boutonRecherche.setActionCommand("recherche");

        affichage.add(scrollPane);

        gestion.add(boutonAjout);
        gestion.add(boutonSuppression);
        gestion.add(boutonReservation);
        gestion.add(boutonEmprunt);

        recherche.add(texteRecherche);
        recherche.add(boutonRecherche);

        add(affichage, BorderLayout.CENTER);
        add(gestion, BorderLayout.EAST);
        add(recherche, BorderLayout.NORTH);

        setBackground(new Color(240, 230, 200));
    }

    public Catalogue getCatController() {
        return catController;
    }

    public void enregistreEcouteur(Catalogue catalogue) {
        boutonAjout.addActionListener(catalogue);
        boutonSuppression.addActionListener(catalogue);
        boutonReservation.addActionListener(catalogue);
        boutonEmprunt.addActionListener(catalogue);
        boutonRecherche.addActionListener(catalogue);
    }

}
