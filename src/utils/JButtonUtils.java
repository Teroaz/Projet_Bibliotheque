package utils;

import javax.swing.*;
import java.awt.*;

public class JButtonUtils {

    public static void beautifyButton(JButton button, Color fgColor, Color bgColor, int borderThickness) {
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(fgColor, borderThickness),
                BorderFactory.createEmptyBorder(button.getBorder().getBorderInsets(button).top, button.getBorder().getBorderInsets(button).left, button.getBorder().getBorderInsets(button).bottom, button.getBorder().getBorderInsets(button).right)));
    }
}
