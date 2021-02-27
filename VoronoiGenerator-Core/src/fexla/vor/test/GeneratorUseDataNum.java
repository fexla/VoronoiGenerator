package fexla.vor.test;

import fexla.vor.*;
import fexla.vor.util.Hash;
import fexla.vor.util.Vector2Dint;

/**
 * @author ：fexla
 * @description：TODO
 * @date ：2021/2/8 20:34
 */
public class GeneratorUseDataNum extends PointRootGenerator {
    static final int minNum = 21;
    static final int maxNum = 126;

    private static int getSeed(Vector2Dint pos, long seed) {
        int x = pos.x, y = pos.y;
//        int num = Math.abs((int) ((seed ^ x) % (1 << 16) + (seed ^ y) << 16));
        int num = (int) Hash.hash2d(seed, pos.hashCode());
        return minNum + num % (maxNum - minNum + 1);
    }

    public GeneratorUseDataNum() {
        super((pos, seed) -> new DataOfNum(getSeed(pos, seed)));
    }
}
