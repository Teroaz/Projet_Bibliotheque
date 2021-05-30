package view.menu.emprunts_reservations.reservation;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.Date;

public class ReservationRenderer extends JLabel implements TableCellRenderer {

    public ReservationRenderer() {
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
        if (column == 1) {
            int valeur = (Integer)value;
            setText(String.valueOf(valeur));
            setHorizontalAlignment(JLabel.CENTER);
        }
        if (column == 2 || column == 3) {
            Date date = (Date)value;
            setText(date.toString());
            setHorizontalAlignment(JLabel.CENTER);
        }
        return this;
    }
}
