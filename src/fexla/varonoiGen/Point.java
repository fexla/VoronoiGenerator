package fexla.varonoiGen;

/**
 * @author ：fexla
 * @description：表示点
 * @date ：2021/2/8 18:31
 */
public abstract class Point {
    private Vector2Dint pos;

    public Vector2Dint getPos() {
        return pos;
    }

    public void setPos(Vector2Dint pos) {
        this.pos = pos;
    }
}
