package fexla.varonoiGen;

import java.util.Objects;

import static java.lang.Math.sqrt;

/**
 * @author ：fexla
 * @description：2维向量
 * @date ：2021/2/8 18:19
 */
public class Vector2D {
    private double x;
    private double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    //距离
    public static double dist(Vector2D v1, Vector2D v2) {
        double a, b;
        a = v1.x - v2.x;
        b = v1.y - v2.y;
        return sqrt(a * a + b * b);
    }

    //距离平方
    public static double distSquare(Vector2D v1, Vector2D v2) {
        double a, b;
        a = v1.x - v2.x;
        b = v1.y - v2.y;
        return a * a + b * b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2D vector2D = (Vector2D) o;
        return Double.compare(vector2D.x, x) == 0 &&
                Double.compare(vector2D.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
