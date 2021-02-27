package fexla.vor.ui.view;

import fexla.vor.Diagram;
import fexla.vor.PointRootGenerator;
import fexla.vor.util.*;
import fexla.vor.test.DataOfNum;
import fexla.vor.test.GeneratorUseDataNum;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author ：fexla
 * @description：TODO
 * @date ：2021/2/27 17:12
 */
public class DiagramImage {
    private Diagram diagram;
    private Vector2D startPoint;
    private Vector2Dint pixelNum;//生成的像素长宽
    private double pixelLength;//单像素在diagram中的长度

    private WritableImage image;

    public DiagramImage(int width, int length) {
        diagram = new Diagram("fexla".hashCode(), new GeneratorUseDataNum(), 4, 20,40, 64,128);
        startPoint = new Vector2D(0, 0);
        pixelNum = new Vector2Dint(width, length);
        pixelLength = 1;
    }

    public Image getImage() {
        int width = pixelNum.x;
        int length = pixelNum.y;
        image = new WritableImage(width, length);
        PixelWriter pw = image.getPixelWriter();
        for (int y = 0; y < length; y++) {
            for (int x = 0; x < width; x++) {
                int color = ((DataOfNum) diagram.getPointData(new Vector2D(startPoint.x + x * pixelLength, startPoint.y + y * pixelLength), 0)).getValue();
                pw.setColor(x, y, new Color(color / 255.0, color / 255.0, color / 255.0, 1));
            }
        }
        return image;
    }

    public void setPixelNum(int width, int length) {
        this.pixelNum = new Vector2Dint(width, length);

    }
}
