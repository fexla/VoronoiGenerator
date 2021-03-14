package fexla.vor.ui.view.image;

import fexla.vor.util.Vector2D;
import fexla.vor.util.Vector2Dint;

/**
 * @author ：fexla
 * @description：TODO
 * @date ：2021/3/14 10:45
 */
public class ImageBuffer {
    private volatile int[] buffer;
    private int width;
    private Vector2D startPoint;
    private double pixelLength;

    public ImageBuffer(Vector2Dint pixelNum, Vector2D startPoint, double pixelLength) {
        this.width = pixelNum.x;
        this.startPoint = startPoint;
        this.pixelLength = pixelLength;
        buffer = new int[width * pixelNum.y];
    }

    public int[] getBuffer() {
        return buffer;
    }

    public void setBuffer(int[] buffer) {
        this.buffer = buffer;
    }

    public Vector2D getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Vector2D startPoint) {
        this.startPoint = startPoint;
    }

    public double getPixelLength() {
        return pixelLength;
    }

    public void setPixelLength(double pixelLength) {
        this.pixelLength = pixelLength;
    }

    public void mergeBlock(ImageBlockBuffer blockBuffer, int x, int y) {
        int[] block = blockBuffer.getBuffer();
        synchronized (block) {
            for (int j = 0; j < blockBuffer.getPixelNum().y; j++) {
                for (int i = 0; i < blockBuffer.getPixelNum().x; i++) {
                    buffer[x + i + (y + j) * width] =
                            block[i + j * blockBuffer.getPixelNum().x];
                }
            }
        }
    }

    /**
     * @param diagramX
     * @param diagramY
     * @return 返回颜色的int形式的值，未生成则返回1
     */
    public int getColor(double diagramX, double diagramY) {
        double x = (diagramX - startPoint.x) / pixelLength;
        double y = (diagramY - startPoint.y) / pixelLength;
        if (Math.abs(x - (int) x) < 0.125 && Math.abs(y - (int) y) < 0.125) {
            int x2 = (int) x, y2 = (int) y;
            if (x2 >= 0 && x < width && y2 >= 0 && y < buffer.length / width) return buffer[x2 + width * y2];

        }
        return 1;
    }
}
