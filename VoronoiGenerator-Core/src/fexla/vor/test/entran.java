package fexla.vor.test;

import fexla.vor.*;

/**
 * @author ：fexla
 * @description：TODO
 * @date ：2021/2/8 21:08
 */
public class entran {
    public static void main(String[] args) {
        Diagram p = new Diagram("fexla".hashCode());
        int width=90,height=30;
        p.initialDiagram(new GeneratorUseDataNum(p), width, height, 2,8,16);
        Vector2Dint v = new Vector2Dint(0, 0);

        for (int yi = 0; yi < height; yi++) {
            for (int xi = 0; xi < width; xi++) {
                v.x = xi;
                v.y = yi;
                DataOfNum num= (DataOfNum) p.getPointData(v,0);
                char c= (char) (num.getValue());
                System.out.print(c+" ");
            }
            System.out.println(" ");
        }
    }
}
