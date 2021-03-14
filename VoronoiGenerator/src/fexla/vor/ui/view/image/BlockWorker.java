package fexla.vor.ui.view.image;

import fexla.vor.Diagram;
import fexla.vor.util.Vector2D;

import static fexla.vor.ui.view.image.ImageDrawer.draw;

/**
 * @author ：fexla
 * @description：TODO
 * @date ：2021/3/14 11:25
 */
public class BlockWorker implements Runnable {
    private ImageBlockBuffer buffer;
    private ImageBuffer lastImageBuffer;
    private ImageGenTask task;
    private volatile Diagram diagram;
    private Vector2D startPoint;
    private int x, y;

    private volatile boolean running = true;

    @Override
    public void run() {
        running = true;
        int blockLength = task.getBlockLength();
        for (int y = 0; y < buffer.getPixelNum().y; y += blockLength) {
            for (int x = 0; x < buffer.getPixelNum().x; x += blockLength) {
                draw(buffer, lastImageBuffer, diagram, startPoint, task.getPixelLength(), x, y, blockLength);
            }
        }
        running = false;
    }

    public BlockWorker(ImageBlockBuffer buffer, ImageBuffer lastImageBuffer, ImageGenTask task, Diagram diagram,
                       Vector2D startPoint, int x, int y) {
        this.buffer = buffer;
        this.lastImageBuffer = lastImageBuffer;
        this.task = task;
        this.diagram = diagram;
        this.startPoint = startPoint;
        this.x = x;
        this.y = y;
    }

    public ImageBlockBuffer getBuffer() {
        return buffer;
    }

    public boolean isRunning() {
        return running;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
