package fexla.vor.ui.view;

import fexla.vor.Diagram;
import fexla.vor.PointRootGenerator;
import fexla.vor.ui.fun.DataColored;
import fexla.vor.ui.fun.DataOfColor;
import fexla.vor.util.*;
import javafx.beans.value.ChangeListener;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;


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
    private int colorLayer;//开始上色的层编号

    private WritableImage image;

    private ImageView view;

    public DiagramImage(ImageView view) {
        setView(view);
        colorLayer =3;
        diagram = new Diagram("fexla".hashCode(),
                new PointRootGenerator((vector2Dint, seed) -> {
                    DataOfColor data = new DataOfColor(colorLayer, seed);
                    if (colorLayer == diagram.getLayerNum() - 1) return data.nextData(vector2Dint, colorLayer);
                    return data;
                }), 32,64,96, 128);
        startPoint = new Vector2D(0, 0);
        pixelLength = 1;
    }

    public Image getImage() {
        int width = pixelNum.x;
        int length = pixelNum.y;
        image = new WritableImage(width, length);
        PixelWriter pw = image.getPixelWriter();
        int sq = 1;
        for (int y = 0; y <= length - sq; y += sq) {
            for (int x = 0; x <= width - sq; x += sq) {
                DataColored res = (DataColored) diagram.getPointData(new Vector2D(startPoint.x + x * pixelLength, startPoint.y + y * pixelLength), 0);

                Color color = null;
                if (res == null) color = Color.RED;
                else color = res.getColor();
                for (int i = 0; i < sq; i++) {
                    for (int j = 0; j < sq; j++) {
                        pw.setColor(x + i, y + j, color);

                    }
                }
            }
        }
        return image;
    }

    public void setPixelNum(int width, int length) {
        this.pixelNum = new Vector2Dint(width, length);

    }

    public Diagram getDiagram() {
        return diagram;
    }

    public void setDiagram(Diagram diagram) {
        this.diagram = diagram;
    }

    public Vector2D getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Vector2D startPoint) {
        this.startPoint = startPoint;
    }

    public Vector2Dint getPixelNum() {
        return pixelNum;
    }

    public void setPixelNum(Vector2Dint pixelNum) {
        this.pixelNum = pixelNum;
    }

    public double getPixelLength() {
        return pixelLength;
    }

    public void setPixelLength(double pixelLength) {
        this.pixelLength = pixelLength;
    }

    public ImageView getView() {
        return view;
    }

    private double MouseX, MouseY;

    public void setView(ImageView view) {
        this.view = view;
        int width = (int) view.getFitWidth(), length = (int) view.getFitHeight();
        pixelNum = new Vector2Dint(width, length);
        ChangeListener<Number> imageViewListener = (observableValue, number, t1) -> {
            setPixelNum((int) view.getFitWidth(), (int) view.getFitHeight());
            view.setImage(getImage());
        };
        view.fitWidthProperty().addListener(imageViewListener);
        view.fitHeightProperty().addListener(imageViewListener);

        view.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            MouseX = event.getX();
            MouseY = event.getY();
        });
        view.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            view.setCursor(Cursor.CLOSED_HAND);
            MouseX = event.getX();
            MouseY = event.getY();
        });
        view.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> {
            view.setCursor(Cursor.DEFAULT);
        });
        view.addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> {
            double distanceX = event.getX() - MouseX;
            double distanceY = event.getY() - MouseY;
            startPoint.x -= distanceX;
            startPoint.y -= distanceY;
            MouseX = event.getX();
            MouseY = event.getY();
            view.setImage(getImage());
        });
    }
}
