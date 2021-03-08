package fexla.vor.ui.model;

import fexla.vor.ui.view.image.DiagramImage;
import fexla.vor.util.Vector2Dint;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * @author ：fexla
 * @description：TODO
 * @date ：2021/3/3 15:24
 */
public class ExportModel {
    private String fileName;
    private String format;
    private DiagramImage diagramImage;
    private Vector2Dint pixelNum;
    private int blockLength;

    public ExportModel() {
        fileName = "图";
        format = "png";
        pixelNum = new Vector2Dint(1000, 1000);
        blockLength = 1;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Vector2Dint getPixelNum() {
        return pixelNum;
    }

    public void setPixelNum(Vector2Dint pixelNum) {
        this.pixelNum = pixelNum;
    }

    public int getBlockLength() {
        return blockLength;
    }

    public void setBlockLength(int blockLength) {
        this.blockLength = blockLength;
    }

    public DiagramImage getDiagramImage() {
        return diagramImage;
    }

    public void setDiagramImage(DiagramImage diagramImage) {
        this.diagramImage = diagramImage;
    }

    public void export() {
        Image res = diagramImage.generateImage(pixelNum, 1, blockLength);
        File file=new File("./"+fileName+"."+format);
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(res, null), "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("成功");
    }
}
