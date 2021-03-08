package fexla.vor.util;

import java.util.Objects;

/**
 * @author ：fexla
 * @description：使用int记录数值的2维向量
 * @date ：2021/2/8 18:32
 */
public class Vector2Dint implements Cloneable{
    public int x;
    public int y;

    public Vector2Dint(int x, int y) {
        this.x = x;
        this.y = y;
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
        return (int) Hash.hash2d(x, y);
    }

    public static Vector2D i2d(Vector2Dint v) {
        return new Vector2D(v.x, v.y);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

