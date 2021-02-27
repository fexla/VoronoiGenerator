package fexla.vor;

/**
 * @author ：fexla
 * @description：Voronoi图
 * @date ：2021/2/8 18:18
 */
public class Diagram {
    private long seed;
    private RootLayer layers[];
    private int layerNum = -1;//层级数量，未生成时为-1

    public Diagram(long seed, PointRootGenerator rootGenerator, int... unitLen) {
        this.seed = seed;
        rootGenerator.setDiagram(this);
        int num = unitLen.length;
        layerNum = num;
        layers = new RootLayer[num];
        for (int i = num - 1; i >= 0; i--) {
            layers[i] = new RootLayer(unitLen[i], i, seed, rootGenerator);

        }
    }

    //返回该点最近的根点数据
    public Data getPointData(Vector2Dint pos, int level) {

        return getClosestRoot(pos, level).getData();
    }

    public PointRoot getClosestRoot(Vector2Dint pos, int level) {
        RootLayer layer = layers[level];
        PointRoot[] roots = layer.getRootSq(pos);
        double min = Double.MAX_VALUE;
        PointRoot closest = null;
        for (int i = 0; i < 9; i++) {
            Vector2Dint pos2 = roots[i].getPos();
            double dis = Vector2Dint.dist(pos, pos2, layer.getType());
            if (dis < min) {
                min = dis;
                closest = roots[i];
            }
        }
        return closest;

    }


    public long getSeed() {
        return seed;
    }

    public int getLayerNum() {
        return layerNum;
    }
}
