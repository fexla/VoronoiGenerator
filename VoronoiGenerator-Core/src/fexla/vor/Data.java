package fexla.vor;

import fexla.vor.util.Vector2Dint;

/**
 * @author ：fexla
 * @description：数据
 * @date ：2021/2/8 18:29
 */
public abstract class Data {
    //上一层传递给下一层的数据
    public abstract Data nextData(Vector2Dint pos, int level);
}
