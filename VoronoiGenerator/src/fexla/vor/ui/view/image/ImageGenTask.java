package fexla.vor.ui.view.image;

import fexla.vor.Diagram;
import fexla.vor.util.Vector2D;
import fexla.vor.util.Vector2Dint;

/**
 * @author ：fexla
 * @description：TODO
 * @date ：2021/3/14 11:18
 */
public class ImageGenTask {
    private volatile ImageBuffer buffer;
    private Vector2Dint pixelNum;
    private Vector2D startPoint;
    private Diagram diagram;
    private double pixelLength;
    private volatile int blockLength;

    private int sideLength;

    public ImageGenTask(Vector2Dint pixelNum, Vector2D startPoint, Diagram diagram, double pixelLength, int blockLength, int sideLength) {
        buffer = new ImageBuffer(pixelNum, startPoint, pixelLength);
        this.pixelNum = pixelNum;
        this.startPoint = startPoint;
        this.diagram = diagram;
        this.pixelLength = pixelLength;
        this.blockLength = blockLength;
        this.sideLength = sideLength;
    }

    public ImageBuffer getBuffer() {
        return buffer;
    }

    public void setBuffer(ImageBuffer buffer) {
        this.buffer = buffer;
    }

    public Vector2Dint getPixelNum() {
        return pixelNum;
    }

    public void setPixelNum(Vector2Dint pixelNum) {
        this.pixelNum = pixelNum;
    }

    public Vector2D getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Vector2D startPoint) {
        this.startPoint = startPoint;
    }

    public Diagram getDiagram() {
        return diagram;
    }

    public void setDiagram(Diagram diagram) {
        this.diagram = diagram;
    }

    public double getPixelLength() {
        return pixelLength;
    }

    public void setPixelLength(double pixelLength) {
        this.pixelLength = pixelLength;
    }

    public int getBlockLength() {
        return blockLength;
    }

    public void setBlockLength(int blockLength) {
        this.blockLength = blockLength;
    }

    public int getSideLength() {
        return sideLength;
    }

    public void setSideLength(int sideLength) {
        this.sideLength = sideLength;
    }
}
