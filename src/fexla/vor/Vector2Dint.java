package fexla.vor;

import java.util.Objects;

/**
 * @author ：fexla
 * @description：TODO
 * @date ：2021/2/8 18:32
 */
public class Vector2Dint {
    public static CalculateType type = CalculateType.EUCLIDEAN;
    public int x;
    public int y;

    public Vector2Dint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //距离
    public static double dist(Vector2Dint v1, Vector2Dint v2) {
        int a, b;
        a = v1.x - v2.x;
        b = v1.y - v2.y;
        switch (type) {
            case EUCLIDEAN:
                return a * a + b * b;
            case CHEBYSHEV:
                return a > b ? a : b;
            case MANHATTAN:
                return a + b;
        }

        return 0;
    }
//
//    //距离平方
//    public static int distSquare(Vector2Dint v1, Vector2Dint v2) {
//        int a, b;
//        a = v1.x - v2.x;
//        b = v1.y - v2.y;
//
//        return a * a + b * b;
//    }

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

