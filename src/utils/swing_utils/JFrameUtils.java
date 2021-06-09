package utils.swing_utils;

import java.awt.*;

public class JFrameUtils {

    public static Point centerFrameCoords(int width, int height) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = screenSize.width / 2 - width / 2;
        int y = screenSize.height / 2 - height / 2;
        return new Point(x, y);
    }
}
