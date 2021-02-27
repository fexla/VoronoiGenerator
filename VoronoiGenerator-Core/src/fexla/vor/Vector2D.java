package fexla.vor;

import java.util.Objects;

import static java.lang.Math.sqrt;

/**
 * @author ：fexla
 * @description：2维向量
 * @date ：2021/2/8 18:19
 */
public class Vector2D {
    public double x;
    public double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
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

    public Vector2D copy() {
        return new Vector2D(x, y);
    }

    //距离
    public static double dist(Vector2D v1, Vector2D v2, CalculateType type) {
        double a, b;
        a = v1.x - v2.x;
        b = v1.y - v2.y;
        switch (type) {
            case EUCLIDEAN:
                return Math.sqrt(a * a + b * b);
            case CHEBYSHEV:
                return a > b ? a : b;
            case MANHATTAN:
                return a + b;
        }

        return 0;
    }

    public static double dist(Vector2D v1, Vector2D v2) {
        return dist(v1, v2, CalculateType.EUCLIDEAN);
    }

    public static Vector2Dint d2i(Vector2D v) {
        return new Vector2Dint((int) v.x, (int) v.y);
    }
}
