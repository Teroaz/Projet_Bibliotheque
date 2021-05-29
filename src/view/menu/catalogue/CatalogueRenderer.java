package view.menu.catalogue;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class CatalogueRenderer extends JLabel implements TableCellRenderer {

    public CatalogueRenderer() {
        super();
        setOpaque(true);
        setBackground(Color.WHITE);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (column == 0 || column == 3) {
            int valeur = (Integer)value;
            setText(String.valueOf(valeur));
            setHorizontalAlignment(JLabel.CENTER);
        }
        if (column == 1 || column == 2) {
            String texte = (String)value;
            setText(texte);
        }
        return this;
    }
}
