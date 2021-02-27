package fexla.vor;


import fexla.vor.util.Vector2Dint;

import java.util.Random;

/**
 * @author ：fexla
 * @description：根点生成器。
 * @date ：2021/2/8 19:44
 */
public class PointRootGenerator {
    private Diagram diagram;
    private RootDataGenerator dataGenerator;

    public PointRootGenerator(RootDataGenerator dataGenerator) {
        this.dataGenerator = dataGenerator;
    }

    /**
     * @param pos        根点位置，此位置为该点在这一层中的位置并非整个图中的位置
     * @param seed       种子
     * @param level
     * @param unitLength
     * @return
     */
    public PointRoot gen(Vector2Dint pos, long seed, int level, int unitLength) {
        Vector2Dint posInDiagram = getPosInDiagram(pos, seed, unitLength);
        PointRoot res = new PointRoot(posInDiagram);
        if (level == diagram.getLayerNum() - 1) {
            res.setData(dataGenerator.generate(pos, seed));
        } else {
            res.setData(diagram.getPointData(posInDiagram, level + 1).nextData(pos,level));
        }
        return res;
    }

    private Vector2Dint getPosInDiagram(Vector2Dint pos, long seed, int unitLength) {
        Vector2Dint posInDiagram = pos.copy();
//        Random ran = new Random((seed ^ pos.x) % (1 << 16) + (seed ^ pos.y) << 16);
        Random ran = new Random(pos.hashCode());
        posInDiagram.x *= unitLength;
        posInDiagram.x += ran.nextInt(unitLength);
        posInDiagram.y *= unitLength;
        posInDiagram.y += ran.nextInt(unitLength);
        return posInDiagram;
    }

    public Diagram getDiagram() {
        return diagram;
    }

    public void setDiagram(Diagram diagram) {
        this.diagram = diagram;
    }
}
