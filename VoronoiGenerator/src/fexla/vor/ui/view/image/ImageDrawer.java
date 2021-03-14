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
 * @date ：2021/3/14 11:25
 */
public class ImageDrawer {
    static int a=0;
    public static void draw(ImageBlockBuffer buffer, ImageBuffer lastBuffer, Diagram diagram,
                            Vector2D startPoint, double pixelLength, int bufferX, int bufferY,
                            int blockLength) {
        double diagramX = startPoint.x + bufferX * pixelLength,
                diagramY = startPoint.y + bufferY * pixelLength;
        Vector2Dint pixelNum = buffer.getPixelNum();
        int colorInt = 1;
        if (lastBuffer != null) {
            colorInt = lastBuffer.getColor(diagramX, diagramY);
        }
        if (colorInt == 1) {
            DataColored res = null;
            if (diagram.getLayerNum() > 0)
                res = (DataColored) diagram.getPointData(new Vector2D(diagramX, diagramY), 0);
            Color color = null;
            if (res == null) color = Color.RED;
            else color = res.getColor();
            colorInt = toInt(color);
        }
        int iterX = pixelNum.x - bufferX < blockLength ? pixelNum.x - bufferX : blockLength,
                iterY = pixelNum.y - bufferY < blockLength ? pixelNum.y - bufferY : blockLength;
        for (int j = 0; j < iterY; j++) {
            for (int i = 0; i < iterX; i++) {
                buffer.getBuffer()[bufferX + i + pixelNum.x * (bufferY + j)] = colorInt;
            }
        }
    }
}
