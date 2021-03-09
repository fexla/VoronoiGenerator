package fexla.vor.ui.view.image;

import fexla.vor.Diagram;
import fexla.vor.ui.fun.DataColored;
import fexla.vor.util.Vector2D;
import fexla.vor.util.Vector2Dint;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

import static fexla.vor.ui.utl.ImageUtl.toInt;

/**
 * @author ：fexla
 * @description：TODO
 * @date ：2021/3/8 19:23
 */
public class ImageBlockThread implements Runnable {
    private ImageDrawer imageDrawer;
    private volatile boolean finished = false;

    @Override
    public void run() {
        imageDrawer.draw();
        finished = true;
    }

    public ImageBlockThread(ImageDrawer imageDrawer) {
        this.imageDrawer = imageDrawer;
    }


    public void setCancel(boolean cancel) {
        this.imageDrawer.setCancel(cancel);
    }

    public boolean isFinished() {
        return finished;
    }
}
