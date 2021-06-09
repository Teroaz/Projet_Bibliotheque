package exceptions;

import view.FenetreBibliotheque;

import javax.swing.*;

public class BasicException extends Exception {
    public BasicException(String message) {
        super(message);
        JOptionPane.showMessageDialog(FenetreBibliotheque.getInstance(), message, "Erreur ..", JOptionPane.ERROR_MESSAGE);
    }
}
