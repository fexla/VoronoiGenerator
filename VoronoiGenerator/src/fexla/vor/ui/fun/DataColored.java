package fexla.vor.ui.fun;

import fexla.vor.Data;
import fexla.vor.util.Vector2Dint;
import javafx.scene.paint.Color;

/**
 * @author ：fexla
 * @description：TODO
 * @date ：2021/2/27 20:58
 */
public class DataColored extends Data {
    private double r, g, b;

    public DataColored(double r, double g, double b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public Color getColor() {
        return new Color(r, g, b, 1);
    }

    @Override
    public Data nextData(Vector2Dint vector2Dint, int i) {
        return new DataColored(r, g, b);
    }
}
