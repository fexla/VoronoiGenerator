package fexla.vor.ui.view.image;

import fexla.vor.util.Vector2Dint;
import javafx.application.Platform;
import javafx.scene.image.*;

import java.nio.IntBuffer;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author ：fexla
 * @description：TODO
 * @date ：2021/3/8 22:14
 */
public class ImageUpdator implements Runnable {
    private volatile boolean canceled = false;
    private List<ImageBlockThread> threads;
    private Integer[] buffer;
    private ImageView view;
    private Vector2Dint pixelNum;

    private volatile boolean allOver = false;

    private static volatile int gcCount = 0;

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    @Override
    public void run() {
        while (!isCanceled()) {
            Platform.runLater(() -> {
                if (allOver) return;
                synchronized (buffer) {
                    int width = pixelNum.x;
                    int length = pixelNum.y;
                    WritableImage image = new WritableImage(width, length);
                    PixelWriter pw = image.getPixelWriter();
                    WritablePixelFormat<IntBuffer> pixelFormat = PixelFormat.getIntArgbPreInstance();
                    int[] array = new int[buffer.length];
                    for (int i = 0; i < array.length; i++) {
                        array[i] = buffer[i];
                    }
                    pw.setPixels(0, 0, width, length, pixelFormat, array, 0, width);
                    view.setImage(image);
                    boolean over = true;
                    for (ImageBlockThread thread : threads)
                        over &= thread.isFinished();
                    allOver = over;
                }

            });
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (allOver) {
                threads = null;
                gcCount++;
                if (gcCount % 4 == 0) {
                    System.gc();
                    gcCount = 0;
                }
                return;
            }
        }
        for (ImageBlockThread thread : threads) {
            thread.setCancel(true);
        }
    }

    public ImageUpdator(Integer[] buffer, ImageView view, Vector2Dint pixelNum) {
        this.buffer = buffer;
        this.view = view;
        this.pixelNum = pixelNum;
    }

    public List<ImageBlockThread> getThreads() {
        return threads;
    }

    public void setThreads(List<ImageBlockThread> threads) {
        this.threads = threads;
        threads.forEach(imageBlockThread -> new Thread(imageBlockThread).start());
    }
}
