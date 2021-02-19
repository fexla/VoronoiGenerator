package fexla.vor;

/**
 * @author ：fexla
 * @description：根点数据生成器
 * @date ：2021/2/19 10:55
 */
public interface RootDataGenerator {
    Data generate(Vector2Dint pos, long seed);
}
