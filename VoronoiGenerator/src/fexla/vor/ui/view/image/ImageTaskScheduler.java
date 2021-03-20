package fexla.vor.ui.view.image;

import fexla.vor.Diagram;
import fexla.vor.ui.view.EditUIController;
import fexla.vor.util.Vector2D;
import fexla.vor.util.Vector2Dint;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ：fexla
 * @description：TODO
 * @date ：2021/3/14 14:36
 */
public class ImageTaskScheduler implements Runnable {
    public volatile static ImageTaskScheduler instance;

    private volatile boolean cancel;
    private ImageGenTask lastTask;
    private ImageGenTask currentTask;
    private volatile ImageGenTask nextTask;

    private volatile OnTaskFinished onTaskFinished;

    private List<BlockWorker> runnings;

    private int emptyLoopCount;

    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    public void setOnTaskFinished(OnTaskFinished onTaskFinished) {
        this.onTaskFinished = onTaskFinished;
    }

    public boolean isCancel() {
        return cancel;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }

    public ImageGenTask getLastTask() {
        return lastTask;
    }


    public ImageGenTask getCurrentTask() {
        return currentTask;
    }


    public ImageGenTask getNextTask() {
        return nextTask;
    }

    public void setNextTask(ImageGenTask nextTask) {
        this.nextTask = nextTask;
    }

    @Override
    public void run() {
        instance = this;
        emptyLoopCount = 0;
        while (!cancel) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Thread.yield();
            if (currentTask == null) {
                if (nextTask == null) {
                    if (++emptyLoopCount == 10) {
                        EditUIController.instance.getDm().setLastDiagramNull();
                        System.gc();
                        System.out.println("gc");
                        emptyLoopCount = 11;
                    }
                    continue;
                }
                currentTask = nextTask;
                nextTask = null;
            }
            emptyLoopCount = 0;
            runnings = new ArrayList<>();
            ImageGenTask task = currentTask;
            Vector2Dint pixelNum = task.getPixelNum();
            ImageBuffer lastBuffer = lastTask == null ? null : lastTask.getBuffer();
            ImageBuffer currBuffer = task.getBuffer();
            for (int i = 0; i < pixelNum.y; i += task.getSideLength()) {
                for (int j = 0; j < pixelNum.x; j += task.getSideLength()) {
                    int width, length;
                    width = pixelNum.x - j < task.getSideLength() ? pixelNum.x - j : task.getSideLength();
                    length = pixelNum.y - i < task.getSideLength() ? pixelNum.y - i : task.getSideLength();
                    ImageBlockBuffer blockBuffer = new ImageBlockBuffer(new Vector2Dint(width, length));
                    BlockWorker worker = null;

                    try {
                        Vector2D startPoint = (Vector2D) task.getStartPoint().clone();
                        startPoint.x += j * task.getPixelLength();
                        startPoint.y += i * task.getPixelLength();
                        Diagram diagram = task.getDiagram();
                        synchronized (diagram) {
                            if (lastTask != null && lastTask.getDiagram() == diagram)
                                worker = new BlockWorker(blockBuffer, lastBuffer, task, diagram, startPoint, j, i);
                            else
                                worker = new BlockWorker(blockBuffer, null, task, diagram, startPoint, j, i);
                        }
                        runnings.add(worker);
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
                }
            }
            runnings.forEach(worker -> cachedThreadPool.execute(worker));
            while (true) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Thread.yield();

                if (cancel) return;
                for (int i = runnings.size() - 1; i >= 0; i--) {
                    BlockWorker worker = runnings.get(i);
                    if (!worker.isRunning()) {
                        currBuffer.mergeBlock(worker.getBuffer(), worker.getX(), worker.getY());
                        runnings.remove(i);
                    }
                }
                if (runnings.size() == 0) break;
            }
            if (onTaskFinished != null)
                onTaskFinished.done(currBuffer.getBuffer());

            lastTask = currentTask;
            currentTask = nextTask;
            nextTask = null;
        }
        cachedThreadPool.shutdown();
    }

}

