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

    public Diagram(long seed) {
        this.seed = seed;
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

    /**
     * 初始化图像
     *
     * @param gen     根点生成器
     * @param width   表示宽度（x的范围0~a）
     * @param height  表示长度（y的范围0~b）
     * @param unitLen 表示各层的单元格边长，unitLen[0]为最低层
     */
    public void initialDiagram(PointRootGenerator gen, int width, int height, int... unitLen) {
        int num = unitLen.length;
        layerNum = num;
        layers = new RootLayer[num];
        for (int i = num - 1; i >= 0; i--) {
            layers[i] = new RootLayer(unitLen[i], i, seed, gen);

        }
    }


    public long getSeed() {
        return seed;
    }

    public int getLayerNum() {
        return layerNum;
    }
}
