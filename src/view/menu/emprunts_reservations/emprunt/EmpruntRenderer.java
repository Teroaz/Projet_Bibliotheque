package view.menu.emprunts_reservations.emprunt;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.Date;

public class EmpruntRenderer extends JLabel implements TableCellRenderer {

    public EmpruntRenderer() {
        super();
        setOpaque(true);
//        setHorizontalAlignment(JLabel.WEST);
        setBackground(Color.WHITE);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (column == 0 || column == 3 || column == 4) {
            String texte = (String) value;
            setText(texte);
        }
        if (column == 1 || column == 2) {
            int valeur = (Integer) value;
            setText(String.valueOf(valeur));
            setHorizontalAlignment(JLabel.CENTER);
        }
        if (column == 3 || column == 4)
            setHorizontalAlignment(JLabel.CENTER);
        if (isSelected)
            setBackground(new Color(176, 229, 243));
        else
            setBackground(Color.WHITE);
        return this;
    }
}
