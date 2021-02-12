package fexla.varonoiGen;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：fexla
 * @description：同一层的根点的集合
 * @date ：2021/2/8 18:47
 */
public class RootGroup {
    private int unitLenth;
    private int level;
    private long seed;
    private Map<Vector2Dint, PointRoot> roots;
    PointRootGenerator generator;

    //输入图的坐标返回该坐标对应的在这一组中的根点对象
    public PointRoot getRoot(Vector2Dint pos) {
        Vector2Dint xpos = pos.copy();
        xpos.x /= unitLenth;
        xpos.y /= unitLenth;
        if (roots.containsKey(xpos)) return roots.get(xpos);
        PointRoot root = generator.gen(xpos, seed, level, unitLenth);
        roots.put(xpos, root);
        return root;
    }

    //得到对应的root周围一共9个root
    public PointRoot[] getRootSq(Vector2Dint pos) {
        Vector2Dint xpos = pos.copy();
        xpos.x /= unitLenth;
        xpos.y /= unitLenth;
        xpos.x -= 1;
        xpos.y -= 1;
        PointRoot[] res = new PointRoot[9];
        for (int i = 0; i < 9; i++) {
            res[i] = getRoot(new Vector2Dint(unitLenth * (xpos.x + i % 3), unitLenth * (xpos.y + i / 3)));
        }
        return res;
    }

    public RootGroup(int unitLenth, int level, long seed, PointRootGenerator generator) {
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
}
