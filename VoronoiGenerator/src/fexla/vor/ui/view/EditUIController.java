package fexla.vor.ui.view;

import fexla.vor.ui.Main;
import fexla.vor.ui.item.Item;
import fexla.vor.ui.item.ItemTextField;
import fexla.vor.ui.item.TextFieldChecker;
import fexla.vor.ui.model.DiagramModel;
import fexla.vor.ui.model.LayerModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：fexla
 * @description：编辑界面
 * @date ：2021/2/19 22:17
 */
public class EditUIController {
    @FXML
    private Pane LayerOverview;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private BorderPane scrollPaneOut;
    @FXML
    private BorderPane imageContainer;
    @FXML
    private ImageView imageView;
    @FXML
    private VBox LayerEditBox;
    @FXML
    private Button removeLayerButton;

    private Background unSelectedBackgrand;
    private Background selectedBackgrand;

    private List<AnchorPane> LayerButtons;

    private AnchorPane selectedLayerButton;

    private static int LayerButtonHeight = 40;
    private static int LayerButtonPad = 5;

    private boolean justDragged = false;
    private Map<AnchorPane, LayerModel> layerModelMap;
    private DiagramModel dm;

    private List<Item> items;

    private DiagramImage diagramImage;

    public void initialize() {
        items = new ArrayList<>();
        LayerButtons = new ArrayList<>();
        layerModelMap = new HashMap<>();

        BackgroundFill backgroundFill = new BackgroundFill(Color.rgb(246, 246, 247), null, null);
        selectedBackgrand = new Background(backgroundFill);
        scrollPane.prefWidthProperty().bind(scrollPaneOut.widthProperty());
        imageView.fitWidthProperty().bind(imageContainer.widthProperty());
        imageContainer.heightProperty().addListener((observableValue, o, n) -> imageView.setFitHeight(n.intValue() - 60));
        diagramImage = new DiagramImage(imageView);
        setDm(getBlankDiagramModel());
    }

    public DiagramModel getBlankDiagramModel() {
        DiagramModel dm = new DiagramModel();
        dm.setBlockLength(5);
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

    public DiagramModel getDm() {
        return dm;
    }

    public void setDm(DiagramModel dm) {
        this.dm = dm;
        dm.setDiagramImage(diagramImage);
        List<LayerModel> lms = dm.getLayerModels();
        for (LayerModel lm : lms) {
            loadLayerButton(lm);
        }
        dm.setDiagramImage(diagramImage);
        updateLayerButtonLayout();
    }

    public AnchorPane loadLayerButton(LayerModel lm) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/LayerButton.fxml"));
        AnchorPane b = null;
        try {
            b = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LayerOverview.getChildren().add(b);
        b.prefWidthProperty().bind(LayerOverview.widthProperty());
        b.setVisible(false);

        LayerButtonHeight = (int) b.getPrefHeight();
        unSelectedBackgrand = b.getBackground();
        b.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            AnchorPane p = (AnchorPane) event.getSource();
            if (justDragged) {
                justDragged = false;
                return;
            }
            if (p == selectedLayerButton) return;
            if (selectedLayerButton != null) selectedLayerButton.setBackground(unSelectedBackgrand);
            p.setBackground(selectedBackgrand);
            selectedLayerButton = p;
            showLayerModel(layerModelMap.get(p));
            removeLayerButton.setDisable(false);
        });
        LayerButton lb = ((LayerButton) loader.getController());
        lb.setController(this);
        lb.setModel(lm);
        LayerButtons.add(b);
        layerModelMap.put(b, lm);
        return b;
    }

    public void updateLayerButtonLayout() {
        for (int i = 0; i < LayerButtons.size(); i++) {
            AnchorPane anchorPane = LayerButtons.get(i);
            updataLayerButtonLocation(i);
            anchorPane.setVisible(true);
        }
        LayerOverview.setMinHeight((LayerButtons.size() ) * (LayerButtonHeight + LayerButtonPad));
        List<LayerModel> layerModels = new ArrayList<>();

        for (int i = 0; i < LayerButtons.size(); i++) {
            LayerModel lmi = layerModelMap.get(LayerButtons.get(i));
            lmi.setLevel(i);
            layerModels.add(lmi);
        }
        dm.setLayerModels(layerModels);
        dm.update();
    }

    private void updataLayerButtonLocation(int i) {
        AnchorPane anchorPane = LayerButtons.get(i);
        anchorPane.setLayoutX(0);
        anchorPane.setLayoutY(i * (LayerButtonHeight + LayerButtonPad));
    }

    public void updateLayerButtonSwap(AnchorPane pane) {
        int i;
        for (i = 0; i < LayerButtons.size(); i++) {
            AnchorPane anchorPane = LayerButtons.get(i);
            if (pane == anchorPane)
                break;
        }

        if (i != 0 && LayerButtons.get(i - 1).getLayoutY() - pane.getLayoutY() > 0) {
            AnchorPane m = LayerButtons.get(i - 1);
            LayerButtons.set(i - 1, pane);
            LayerButtons.set(i, m);
            updataLayerButtonLocation(i);
        } else if (i < (LayerButtons.size() - 1) && LayerButtons.get(i + 1).getLayoutY() - pane.getLayoutY() < 0) {
            AnchorPane m = LayerButtons.get(i + 1);
            LayerButtons.set(i + 1, pane);
            LayerButtons.set(i, m);
            updataLayerButtonLocation(i);
        }
    }

    public boolean isJustDragged() {
        return justDragged;
    }

    public void setJustDragged(boolean justDragged) {
        this.justDragged = justDragged;
    }

    public void clearEditBox() {
        items = new ArrayList<>();
        LayerEditBox.getChildren().clear();
    }

    TextFieldChecker IntergerChecker = string -> {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    };

    public void showLayerModel(LayerModel model) {
        clearEditBox();
        HBox box = new HBox();
        ItemTextField layerUnitLengthField = new ItemTextField(box, "层单位格宽度 : ", IntergerChecker,
                model.getUnitLength() + "", string -> model.setUnitLength(Integer.parseInt(string)));
        LayerEditBox.getChildren().add(box);
    }

    @FXML
    private void removeSelectedLayer() {
        AnchorPane removeItem = selectedLayerButton;
        LayerButtons.remove(removeItem);
        LayerOverview.getChildren().remove(removeItem);
        LayerModel lm = layerModelMap.remove(removeItem);
        dm.remove(lm);
        updateLayerButtonLayout();
        clearEditBox();
        if (LayerButtons.size() == 0) removeLayerButton.setDisable(true);
    }

    @FXML
    void newLayer() {
        LayerModel lm = null;
        int location = LayerButtons.size() == 0 ? 0 : LayerButtons.indexOf(selectedLayerButton) + 1;
        if (location == 0) {
            lm = new LayerModel(10);
            removeLayerButton.setDisable(false);
        } else {
            lm = new LayerModel(layerModelMap.get(selectedLayerButton).getUnitLength() + 1);
        }
        lm.setName(location + 1 + "");
        lm.setDiagramModel(dm);
        AnchorPane b = loadLayerButton(lm);
        LayerButtons.remove(b);
        LayerButtons.add(location, b);
        updateLayerButtonLayout();
    }
}