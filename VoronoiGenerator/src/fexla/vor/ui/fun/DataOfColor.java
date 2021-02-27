package fexla.vor.ui.fun;

import fexla.vor.Data;
import fexla.vor.util.Hash;
import fexla.vor.util.Vector2Dint;
import javafx.scene.paint.Color;

import java.util.Random;

/**
 * @author ：fexla
 * @description：TODO
 * @date ：2021/2/27 20:32
 */
public class DataOfColor extends Data {
    private int colorLevel;
    private long seed;

    public DataOfColor(int colorLevel, long seed) {
        this.colorLevel = colorLevel;
        this.seed = seed;
    }

    @Override
    public Data nextData(Vector2Dint vector2Dint, int level) {
        if (level == colorLevel) {
            Random ran = new Random(Hash.hash2d(vector2Dint.hashCode(),level));
            DataColored data = new DataColored(ran.nextDouble(), ran.nextDouble(), ran.nextDouble());
            return data;
        }
        return new DataOfColor(colorLevel, seed);
    }
}

