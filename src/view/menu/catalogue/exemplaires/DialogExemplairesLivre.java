package view.menu.catalogue.exemplaires;

import utils.swing_utils.JFrameUtils;
import view.FenetreBibliotheque;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogExemplairesLivre extends JDialog implements ActionListener {

    private final int width = 500;
    private final int height = 400;

    public DialogExemplairesLivre() {
        super(FenetreBibliotheque.getInstance(), "Bibliothèque | Catalogue - Gestion des exemplaires", ModalityType.APPLICATION_MODAL);
        setModal(true);

        setSize(width, height);
        setLocation(JFrameUtils.centerFrameCoords(width, height));
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);
        setAutoRequestFocus(true);
        setVisible(true);

        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
