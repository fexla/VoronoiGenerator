package fexla.vor.ui.model;

import fexla.vor.Diagram;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：fexla
 * @description：TODO
 * @date ：2021/2/24 9:16
 */
public class DiagramModel {
    private long seed = "fexla".hashCode();
    private List<LayerModel> layerModels;

    public DiagramModel() {
        layerModels = new ArrayList<>();
    }

    public long getSeed() {
        return seed;
    }

    public void setSeed(long seed) {
        this.seed = seed;
    }


    public List<LayerModel> getLayerModels() {
        return layerModels;
    }

    public void setLayerModels(List<LayerModel> layerModels) {
        this.layerModels = layerModels;
    }

    public int size() {
        return layerModels.size();
    }

    public boolean add(LayerModel layerModel) {
        return layerModels.add(layerModel);
    }

    public boolean remove(Object o) {
        return layerModels.remove(o);
    }

    public Diagram build() {
        return null;
    }
}
