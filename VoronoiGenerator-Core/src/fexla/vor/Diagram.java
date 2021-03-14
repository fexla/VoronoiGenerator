package fexla.vor;

import fexla.vor.util.Vector2D;
import fexla.vor.util.Vector2Dint;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author ：fexla
 * @description：Voronoi图
 * @date ：2021/2/8 18:18
 */
public class Diagram implements Cloneable {
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
    public Data getPointData(Vector2D pos, int level) {

        PointRoot root = getClosestRoot(pos, level);
        if (root == null) return null;
        return root.getData();
    }

    //返回该点最近的根点数据
    public Data getPointData(Vector2Dint pos, int level) {

        return getPointData(Vector2Dint.i2d(pos), level);
    }

    public PointRoot getClosestRoot(Vector2D pos, int level) {
        RootLayer layer = layers[level];
        PointRoot[] roots = layer.getRootSq(pos);
        double min = Double.MAX_VALUE;
        PointRoot closest = null;
        for (int i = 0; i < 9; i++) {
            Vector2Dint pos2 = roots[i].getPos();
            Vector2D temp = Vector2Dint.i2d(pos2);
            double dis = Vector2D.dist(pos, temp, layer.getType());
            if (dis < min) {
                min = dis;
                closest = roots[i];
            }
        }
//        if (min < 3&&level==0) return null;
//        closest = roots[0];
        return closest;

    }

    public PointRoot getClosestRoot(Vector2Dint pos, int level) {
        return getClosestRoot(Vector2Dint.i2d(pos), level);
    }


    public long getSeed() {
        return seed;
    }

    public int getLayerNum() {
        return layerNum;
    }

    public RootLayer getLayer(int i) {
        return layers[i];
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Diagram obj = (Diagram) super.clone();
        obj.layers = new RootLayer[layers.length];
        for (int i = 0; i < layers.length; i++) {
            obj.layers[i] = (RootLayer) layers[i].clone();
        }
        return obj;
    }
}
