package fexla.vor.util;

/**
 * @author ：fexla
 * @description：TODO
 * @date ：2021/2/27 19:58
 */
public class Hash {
    final static int p1 = 479, p2 = 487;

    public static long hash2d(long a, long b) {
        return (p1 + b) * p2 + a;
    }
}
