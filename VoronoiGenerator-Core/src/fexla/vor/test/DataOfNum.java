package fexla.vor.test;

import fexla.vor.Data;

/**
 * @author ：fexla
 * @description：测试用数字数据
 * @date ：2021/2/8 20:21
 */
public class DataOfNum extends Data {
    private int value;

    @Override
    public Data nextData() {
        return new DataOfNum(value);
    }

    public DataOfNum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
