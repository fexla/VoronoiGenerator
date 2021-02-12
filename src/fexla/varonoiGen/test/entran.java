package fexla.varonoiGen.test;

import fexla.varonoiGen.*;

/**
 * @author ：fexla
 * @description：TODO
 * @date ：2021/2/8 21:08
 */
public class entran {
    public static void main(String[] args) {
        Vector2Dint.type=CalculateType.CHEBYSHEV;
        Diagram p = new Diagram("fexla".hashCode());
        p.initialRoots(new GeneratorUseDataNum(1), 60, 30, 2,8);
        Vector2Dint v = new Vector2Dint(0, 0);

        for (int yi = 0; yi < 30; yi++) {
            for (int xi = 0; xi < 60; xi++) {
                v.x = xi;
                v.y = yi;
                DataOfNum num= (DataOfNum) p.getPointData(v,0);
                char c= (char) (num.getValue()+20);
                System.out.print(c+" ");
            }
            System.out.println(" ");
        }
    }
}
