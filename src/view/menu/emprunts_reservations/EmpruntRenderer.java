package view.menu.emprunts_reservations;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.Date;

public class EmpruntRenderer extends JLabel implements TableCellRenderer {

    public EmpruntRenderer() {
        super();
        setOpaque(true);
        setBackground(Color.WHITE);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (column == 0) {
            String texte = (String)value;
            setText(texte);
        }
        if (column == 1 || column == 2) {
            int valeur = (Integer)value;
            setText(String.valueOf(valeur));
            setHorizontalAlignment(JLabel.CENTER);
        }
        if (column == 3 || column == 4) {
            Date date = (Date)value;
            setText(date.toString());
            setHorizontalAlignment(JLabel.CENTER);
        }
        return this;
    }
}
