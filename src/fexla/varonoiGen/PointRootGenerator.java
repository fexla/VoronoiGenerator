package fexla.varonoiGen;

import java.util.Random;

/**
 * @author ：fexla
 * @description：TODO
 * @date ：2021/2/8 19:44
 */
public class PointRootGenerator {
    public PointRoot gen(Vector2Dint pos, long seed, int level, int unitLength) {
        Vector2Dint xpos = pos.copy();
        Random ran = new Random(seed^xpos.x^(xpos.y<<5));
        xpos.x=xpos.x*unitLength;
        xpos.x=xpos.x+ran.nextInt(unitLength);
        xpos.y*=unitLength;
        xpos.y+=ran.nextInt(unitLength);
        return new PointRoot(xpos);
    }
}
