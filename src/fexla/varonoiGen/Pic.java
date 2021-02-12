package fexla.varonoiGen;

import java.awt.*;

/**
 * @author ：fexla
 * @description：图
 * @date ：2021/2/8 18:18
 */
public class Pic {
    private long seed;
    private RootGroup groups[];

    //返回该点最近的根点数据
    public Data getPointData(Vector2Dint pos, int level) {

        return getClosestRoot(pos, level).getData();
    }

    public PointRoot getClosestRoot(Vector2Dint pos, int level) {
        PointRoot[] roots = groups[level].getRootSq(pos);
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
    public void initialRoots(PointRootGenerator gen, int a, int b, int... lenth) {
        int num = lenth.length;
        groups = new RootGroup[num];
        for (int i = num - 1; i >= 0; i--) {
            groups[i] = new RootGroup(lenth[i], i, seed, gen);

        }
        for (int i = num - 2; i >= 0; i--) {
            RootGroup lowerg;
            lowerg = groups[i];
            int ll = lenth[i];
            for (int yi = -ll; yi <= b + ll; yi += ll) {
                for (int xi = -ll; xi <= a + ll; xi += ll) {
//                    System.out.println(1);
                    Vector2Dint pos = new Vector2Dint(xi, yi);
                    PointRoot root = lowerg.getRoot(pos);
                    PointRoot father = getClosestRoot(root.getPos(), i + 1);
                    root.setFather(father);
                    root.setData(father.
                            getData().
                            nextData());
                }
            }
        }
    }

    public Pic(long seed) {
        this.seed = seed;
    }

    public long getSeed() {
        return seed;
    }

    public RootGroup[] getGroups() {
        return groups;
    }
}
