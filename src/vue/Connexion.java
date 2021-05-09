package vue;

import javax.swing.*;

public class Connexion extends JFrame {
    private JPanel panel1;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton seConnecterButton;

    public Connexion() {
        add(panel1);
        add(textField1);
        add(passwordField1);
        add(seConnecterButton);
        setVisible(true);
    }
}
