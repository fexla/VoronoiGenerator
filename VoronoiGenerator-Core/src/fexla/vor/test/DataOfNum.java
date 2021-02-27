package fexla.vor.test;

import fexla.vor.Data;
import fexla.vor.util.Vector2Dint;

/**
 * @author ：fexla
 * @description：测试用数字数据
 * @date ：2021/2/8 20:21
 */
public class DataOfNum extends Data {
    private int value;


    public DataOfNum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public Data nextData(Vector2Dint pos, int level) {
        return new DataOfNum(value);
    }
}
