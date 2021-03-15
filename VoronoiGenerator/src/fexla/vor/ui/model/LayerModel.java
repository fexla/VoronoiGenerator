package fexla.vor.ui.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：fexla
 * @description：TODO
 * @date ：2021/2/24 9:15
 */
public class LayerModel {
    private int level;
    private String name;
    private int unitLength;
    private List<PointModel> specialPoints;
    private transient DiagramModel diagramModel;

    public LayerModel(int unitLength) {
        this.unitLength = unitLength;
        specialPoints = new ArrayList<>();
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
        changed();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DiagramModel getDiagramModel() {
        return diagramModel;
    }

    public void setDiagramModel(DiagramModel diagramModel) {
        this.diagramModel = diagramModel;
    }

    public int getUnitLength() {
        return unitLength;
    }

    public void setUnitLength(int unitLength) {
        this.unitLength = unitLength;
        changed();
        diagramModel.update();
    }

    private void changed() {
        diagramModel.changed();
    }

    public List<PointModel> getSpecialPoints() {
        return specialPoints;
    }
}
