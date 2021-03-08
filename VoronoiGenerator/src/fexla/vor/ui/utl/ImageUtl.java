package fexla.vor.ui.utl;

import javafx.scene.paint.Color;

/**
 * @author ：fexla
 * @description：TODO
 * @date ：2021/3/8 19:55
 */
public class ImageUtl {
    public static int toInt(Color c) {
        return
                (255 << 24) |
                        ((int) (c.getRed() * 255) << 16) |
                        ((int) (c.getGreen() * 255) << 8) |
                        ((int) (c.getBlue() * 255));
    }
}
