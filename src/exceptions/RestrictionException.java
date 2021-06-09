package exceptions;

import view.FenetreBibliotheque;

import javax.swing.*;

public class RestrictionException extends Exception {
    public RestrictionException(String message) {
        super(message);
        JOptionPane.showMessageDialog(FenetreBibliotheque.getInstance(), message, "Petit soucis...", JOptionPane.WARNING_MESSAGE);
    }
}
