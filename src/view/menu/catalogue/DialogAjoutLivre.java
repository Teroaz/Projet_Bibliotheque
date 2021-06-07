package view.menu.catalogue;

import model.Auteur;
import model.Livre;
import utils.swing_utils.JFrameUtils;
import view.FenetreBibliotheque;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogAjoutLivre extends JDialog implements ActionListener {

    private final int width = 500;
    private final int height = 400;

    private final JTextField idTextField;
    private final JButton addButton;
    private final JTextField nouvAuteurTextfield;


    public DialogAjoutLivre() {
        super(FenetreBibliotheque.getInstance(), "Bibliothèque | Catalogue - Ajout d'un livre", ModalityType.APPLICATION_MODAL);
        setModal(true);

        setSize(width, height);
        setLocation(JFrameUtils.centerFrameCoords(width, height));
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);
        setAutoRequestFocus(true);

        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 20, 5, 20);
        gbc.gridx = 0;
        gbc.gridy = 0;
//        JLabel title = new JLabel("Livre #" + (Livre.catalogue.size() + 1));
//        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 28));
//        title.setHorizontalAlignment(SwingConstants.CENTER);
//        add(title, BorderLayout.NORTH);
        JLabel idLabel = new JLabel("ID : ");
        add(idLabel, gbc);
        gbc.gridx++;

        idTextField = new JTextField(4);
        idTextField.setText((Livre.catalogue.size() + 1) + "");
        idTextField.setEnabled(false);
        idTextField.setHorizontalAlignment(SwingConstants.CENTER);
        add(idTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;

        JLabel titreLabel = new JLabel("Titre : ");
        add(titreLabel, gbc);
        gbc.gridx++;

        JTextField titreTextField = new JTextField(10);
        add(titreTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;

        JLabel auteurLabel = new JLabel("Auteur : ");
        add(auteurLabel, gbc);
        gbc.gridx++;

        JComboBox auteurComboBox = new JComboBox();
        auteurComboBox.addItem("Nouvel auteur");
        for (Object nom_prenom : Auteur.getAuteurs().stream().map(Auteur::auteurNP).toArray()) {
            auteurComboBox.addItem(nom_prenom);
        }

        auteurComboBox.addActionListener(this);

        add(auteurComboBox, gbc);

        gbc.gridy = 3;

        gbc.gridx = 0;
        JLabel nouvAuteurLabel = new JLabel("Prénom & Nom");
        add(nouvAuteurLabel, gbc);

        gbc.gridy++;
        nouvAuteurTextfield = new JTextField(10);
        nouvAuteurTextfield.setToolTipText("Nom puis prénom de l'auteur séparés par une \",\".");
        add(nouvAuteurTextfield, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;

        addButton = new JButton("Ajouter");
        addButton.addActionListener(this);
        add(addButton, gbc);

        setVisible(true);
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JTextField getNouvAuteurTextfield() {
        return nouvAuteurTextfield;
    }

    public JTextField getIdTextField() {
        return idTextField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String target = (String) ((JComboBox) e.getSource()).getSelectedItem();
        if (target == null) return;

        nouvAuteurTextfield.setVisible(target.equals("Nouvel auteur"));

        revalidate();
        repaint();
    }
}
