package utils.swing_utils;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class JTableUtils {

    public static void beautifyJTable(JTable table) {

    }

    public static void beautifyHeader(JTableHeader tableHeader, Color fgColor, Color bgColor, int fontSize) {
        tableHeader.setForeground(fgColor);
        tableHeader.setBackground(bgColor);
        tableHeader.setFont(new Font(Font.SANS_SERIF, Font.BOLD, fontSize));
    }
}
