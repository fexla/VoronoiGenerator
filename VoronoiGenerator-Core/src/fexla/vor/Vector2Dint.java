package fexla.vor;

import java.util.Objects;

/**
 * @author ：fexla
 * @description：使用int记录数值的2维向量
 * @date ：2021/2/8 18:32
 */
public class Vector2Dint {
    public int x;
    public int y;

    public Vector2Dint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //距离
    public static double dist(Vector2Dint v1, Vector2Dint v2, CalculateType type) {
        int a, b;
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

    public static double dist(Vector2Dint v1, Vector2Dint v2) {
        return dist(v1, v2, CalculateType.EUCLIDEAN);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2Dint v = (Vector2Dint) o;
        return v.x == x && v.y == y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public Vector2Dint copy() {
        return new Vector2Dint(x, y);
    }
}

