package fexla.vor.test;

import fexla.vor.*;

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
        int num = (int) ((seed ^ x) % (1 << 16) + (seed ^ y) << 16);
        return minNum + num % (maxNum-minNum+1);
    }

    public GeneratorUseDataNum(Diagram diagram) {
        super(diagram, (pos, seed) -> new DataOfNum(GeneratorUseDataNum.getSeed(pos, seed)));
    }

    @Override
    public PointRoot gen(Vector2Dint pos, long seed, int level, int unitLength) {
        PointRoot res = super.gen(pos, seed, level, unitLength);
        if (level == getDiagram().getLayerNum()) {
            res.setData(new DataOfNum(getSeed(pos, seed)));
        }
        return res;
    }
}
