import view.FenetreBibliotheque;

import javax.swing.*;

public class Bibliotheque {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        new FenetreBibliotheque();
    }
}
