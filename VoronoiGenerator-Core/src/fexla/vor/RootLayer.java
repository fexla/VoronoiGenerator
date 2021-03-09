package fexla.vor;

import fexla.vor.util.Vector2D;
import fexla.vor.util.Vector2Dint;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：fexla
 * @description：同一层的根点的集合
 * @date ：2021/2/8 18:47
 */
public class RootLayer implements Cloneable {
    private int unitLenth;
    private int level;
    private long seed;
    private Map<Vector2Dint, PointRoot> roots;
    private CalculateType type = CalculateType.EUCLIDEAN;
    PointRootGenerator generator;

    //输入图的坐标返回该坐标对应的在这一组中的根点对象
    public PointRoot getRoot(Vector2D pos) {
        Vector2Dint xpos = Vector2D.d2i(pos);
        xpos.x /= unitLenth;
        xpos.y /= unitLenth;
        if (roots.containsKey(xpos)) {
            PointRoot res = roots.get(xpos);
            if (res != null)
                return res;
            res= generator.gen(xpos, seed, level, unitLenth);
            roots.replace(xpos,res);
            return res;
        }
        PointRoot root = generator.gen(xpos, seed, level, unitLenth);
        roots.put(xpos, root);
        return root;
    }

    //得到对应的root周围一共9个root
    public PointRoot[] getRootSq(Vector2D pos) {
        Vector2D xpos = null;
        try {
            xpos = (Vector2D) pos.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        xpos.x /= unitLenth;
        if (xpos.x > 0) {
            xpos.x -= 1;
        } else {
            xpos.x -= 2;
        }
        xpos.y /= unitLenth;
        if (xpos.y > 0) {
            xpos.y -= 1;
        } else {
            xpos.y -= 2;
        }
        PointRoot[] res = new PointRoot[9];
        for (int i = 0; i < 9; i++) {
            res[i] = getRoot(new Vector2D(unitLenth * (xpos.x + i % 3), unitLenth * (xpos.y + i / 3)));
        }
        return res;
    }

    public RootLayer(int unitLenth, int level, long seed, PointRootGenerator generator) {
        this.unitLenth = unitLenth;
        this.level = level;
        this.seed = seed;
        this.generator = generator;
        roots = new HashMap<>();
    }

    public static <K, V> Map.Entry<K, V> entry(K k, V v) {
        return Map.entry(k, v);
    }

    public Map<Vector2Dint, PointRoot> getRoots() {
        return roots;
    }

    public CalculateType getType() {
        return type;
    }

    public void setType(CalculateType type) {
        this.type = type;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        RootLayer obj = (RootLayer) super.clone();
        obj.roots = new HashMap<>();
        return obj;
    }
}
