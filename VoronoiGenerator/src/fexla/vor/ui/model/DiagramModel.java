package fexla.vor.ui.model;

import fexla.vor.Diagram;
import fexla.vor.PointRootGenerator;
import fexla.vor.ui.fun.DataOfColor;
import fexla.vor.ui.view.image.DiagramImage;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：fexla
 * @description：TODO
 * @date ：2021/2/24 9:16
 */
public class DiagramModel {
    private long seed;
    private int colorLayer;
    private int blockLength;
    private List<LayerModel> layerModels;
    private transient DiagramImage diagramImage;

    private boolean changed = true;
    private transient Diagram lastDiagram;

    public DiagramModel() {
        seed = "fexla".hashCode();
        layerModels = new ArrayList<>();
    }

    public long getSeed() {
        return seed;
    }

    public void setSeed(long seed) {
        this.seed = seed;
        changed();
    }

    public int getColorLayer() {
        return colorLayer;
    }

    public void setColorLayer(int colorLayer) {
        this.colorLayer = colorLayer;
        changed();
    }

    public int getBlockLength() {
        return blockLength;
    }

    public void setBlockLength(int blockLength) {
        this.blockLength = blockLength;
        changed();
    }

    public DiagramImage getDiagramImage() {
        return diagramImage;
    }

    public void setDiagramImage(DiagramImage diagramImage) {
        this.diagramImage = diagramImage;
    }

    public List<LayerModel> getLayerModels() {
        return layerModels;
    }

    public void setLayerModels(List<LayerModel> layerModels) {
        this.layerModels = layerModels;
        changed();
    }

    public void setLastDiagramNull() {
        lastDiagram = null;
    }

    protected void changed() {
        changed = true;
    }

    public int size() {
        return layerModels.size();
    }

    public boolean add(LayerModel layerModel) {
        changed();
        return layerModels.add(layerModel);
    }

    public boolean remove(Object o) {
        changed();
        return layerModels.remove(o);
    }

    public void update() {
        colorLayer = layerModels.size() - 1;
        int unitLen[] = new int[layerModels.size()];
        for (int i = 0; i < layerModels.size(); i++) {
            unitLen[i] = layerModels.get(i).getUnitLength();
        }
        PointRootGenerator generator = null;
        if (colorLayer == unitLen.length - 1) {
            generator = new PointRootGenerator((vector2Dint, seed) -> {
                DataOfColor data = new DataOfColor(colorLayer, seed);
                return data.nextData(vector2Dint, colorLayer);
            });
        } else {
            generator = new PointRootGenerator((vector2Dint, seed) -> new DataOfColor(colorLayer, seed));
        }
        Diagram diagram = null;
        if (changed || lastDiagram == null)
            diagram = new Diagram(seed, generator, unitLen);
        else
            diagram = lastDiagram;
        changed = false;
        lastDiagram = diagram;
        diagramImage.setDiagram(diagram);
        diagramImage.setColorLayer(colorLayer);
        diagramImage.setBlockLength(blockLength);
        diagramImage.updateImage();
    }

    public static DiagramModel getBlankDiagramModel() {
        DiagramModel dm = new DiagramModel();
        dm.setBlockLength(3);
        for (int i = 0; i < 3; i++) {
            LayerModel lm = new LayerModel((i + 1) * (i + 1) * 10);
            lm.setDiagramModel(dm);
            lm.setName((i + 1) + "");
            lm.setLevel(i);
            dm.add(lm);
        }
        dm.setColorLayer(2);
        return dm;
    }

}
