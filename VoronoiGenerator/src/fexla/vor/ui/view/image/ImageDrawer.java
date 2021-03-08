package fexla.vor.ui.view.image;

import fexla.vor.Diagram;
import fexla.vor.ui.fun.DataColored;
import fexla.vor.util.Vector2D;
import fexla.vor.util.Vector2Dint;
import javafx.scene.paint.Color;

import static fexla.vor.ui.utl.ImageUtl.toInt;

/**
 * @author ：fexla
 * @description：TODO
 * @date ：2021/3/8 22:35
 */
public class ImageDrawer {
    private int[] buffer;
    private Diagram diagram;
    private Vector2D startPoint;
    private Vector2Dint drawStart;
    private Vector2Dint pixelNum;//生成的像素长宽
    private double pixelLength;//单像素在diagram中的长度
    private int blockLength;//生成图形每个点占的方格边长（像素）

    public void draw() {
        int width = pixelNum.x,
                length = pixelNum.y,
                endX = drawStart.x + pixelNum.x,
                endY = drawStart.y + pixelNum.y;

        for (int y = drawStart.y; y <endY; y += blockLength) {
            for (int x = drawStart.x; x < endX; x += blockLength) {
                DataColored res = (DataColored) diagram.getPointData(new Vector2D(startPoint.x + x * pixelLength, startPoint.y + y * pixelLength), 0);

                Color color = null;
                if (res == null) color = Color.RED;
                else color = res.getColor();
                for (int i = 0; x + i < endX && i <= blockLength; i++) {
                    for (int j = 0; y + j < endY && j <= blockLength; j++) {
                        buffer[x + i + width * (y + j)] = toInt(color);
                    }
                }
            }
        }
    }

    public ImageDrawer(int[] buffer, Diagram diagram, Vector2D startPoint, Vector2Dint drawStart,
                       Vector2Dint pixelNum, double pixelLength, int blockLength) {
        this.buffer = buffer;
        this.diagram = diagram;
        this.startPoint = startPoint;
        this.drawStart = drawStart;
        this.pixelNum = pixelNum;
        this.pixelLength = pixelLength;
        this.blockLength = blockLength;
    }
}

