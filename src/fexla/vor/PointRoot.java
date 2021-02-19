package fexla.vor;

/**
 * @author ：fexla
 * @description：TODO
 * @date ：2021/2/8 18:31
 */
public class PointRoot extends Point {
    private PointRoot father;
    private Data data;

    public PointRoot(int x, int y) {
        setPos(new Vector2Dint(x, y));
    }

    public PointRoot(Vector2Dint pos) {
        setPos(pos);
    }

    public PointRoot(Vector2Dint pos, PointRoot father) {
        setPos(pos);
        this.father = father;
    }

    public PointRoot getFather() {
        return father;
    }

    public void setFather(PointRoot father) {
        this.father = father;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "(x=" + getPos().x + ",y=" + getPos().y +")";
    }
}
