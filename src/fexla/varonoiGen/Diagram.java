package fexla.varonoiGen;

/**
 * @author ：fexla
 * @description：Voronoi图
 * @date ：2021/2/8 18:18
 */
public class Diagram {
    private long seed;
    private RootLayer layers[];
    private int layerNum = -1;//层级数量，未生成时为-1

    public Diagram(long seed) {
        this.seed = seed;
    }

    //返回该点最近的根点数据
    public Data getPointData(Vector2Dint pos, int level) {

        return getClosestRoot(pos, level).getData();
    }

    public PointRoot getClosestRoot(Vector2Dint pos, int level) {
        PointRoot[] roots = layers[level].getRootSq(pos);
        double min = Double.MAX_VALUE;
        PointRoot closest = null;
        for (int i = 0; i < 9; i++) {
            Vector2Dint pos2 = roots[i].getPos();
            double dis = Vector2Dint.dist(pos, pos2);
            if (dis < min) {
                min = dis;
                closest = roots[i];
            }
        }
        return closest;

    }

    //a表示长度（x的范围0~a），b表示宽度（y的范围0~b）
    public void initialDiagram(PointRootGenerator gen, int a, int b, int... lenth) {
        int num = lenth.length;
        layerNum = num;
        layers = new RootLayer[num];
        for (int i = num - 1; i >= 0; i--) {
            layers[i] = new RootLayer(lenth[i], i, seed, gen);

        }
    }


    public long getSeed() {
        return seed;
    }

    public int getLayerNum() {
        return layerNum;
    }
}
