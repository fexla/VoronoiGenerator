package fexla.vor.ui.view.image;

import fexla.vor.util.Vector2D;
import fexla.vor.util.Vector2Dint;

/**
 * @author ：fexla
 * @description：TODO
 * @date ：2021/3/14 10:50
 */
public class ImageBlockBuffer {
    private int[] buffer;
    private Vector2Dint pixelNum;//保存该块的长宽

    public ImageBlockBuffer(Vector2Dint pixelNum) {
        this.pixelNum = pixelNum;
        buffer = new int[pixelNum.x * pixelNum.y];
    }

    public int[] getBuffer() {
        return buffer;
    }

    public void setBuffer(int[] buffer) {
        this.buffer = buffer;
    }

    public Vector2Dint getPixelNum() {
        return pixelNum;
    }

    public void setPixelNum(Vector2Dint pixelNum) {
        this.pixelNum = pixelNum;
    }

    public void set(int x, int y, int value) {
        buffer[x + y * pixelNum.x] = value;
    }

}
