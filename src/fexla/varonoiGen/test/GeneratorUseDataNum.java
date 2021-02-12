package fexla.varonoiGen.test;

import fexla.varonoiGen.PointRoot;
import fexla.varonoiGen.PointRootGenerator;
import fexla.varonoiGen.Vector2Dint;

import java.util.Random;

/**
 * @author ：fexla
 * @description：TODO
 * @date ：2021/2/8 20:34
 */
public class GeneratorUseDataNum extends PointRootGenerator {
    int maxLevel;
    static int num =10;

    @Override
    public PointRoot gen(Vector2Dint pos, long seed, int level, int unitLength) {
        PointRoot res = super.gen(pos, seed, level, unitLength);
        if (level == maxLevel) {
            res.setData(new DataOfNum(num++));
        }
        return res;
    }

    public GeneratorUseDataNum(int maxLevel) {
        this.maxLevel = maxLevel;
    }
}
