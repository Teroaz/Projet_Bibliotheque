package view.menu.emprunts_reservations.reservation;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.Date;

public class ReservationRenderer extends JLabel implements TableCellRenderer {

    public ReservationRenderer() {
        super();
        setOpaque(true);
//        setHorizontalAlignment(JLabel.WEST);
        setBackground(Color.WHITE);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (column == 0 || column == 2 || column == 3) {
            String texte = (String)value;
            setText(texte);
        }
        if (column == 1) {
            int valeur = (Integer)value;
            setText(String.valueOf(valeur));
            setHorizontalAlignment(JLabel.CENTER);
        }
        if (column == 2 || column == 3)
            setHorizontalAlignment(JLabel.CENTER);
        if (isSelected)
            setBackground(new Color(176, 229, 243));
        else
            setBackground(Color.WHITE);
        return this;
    }
}
