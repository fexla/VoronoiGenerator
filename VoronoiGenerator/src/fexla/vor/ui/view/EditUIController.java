package fexla.vor.ui.view;

import fexla.vor.ui.Main;
import fexla.vor.ui.item.Item;
import fexla.vor.ui.item.ItemTextField;
import fexla.vor.ui.item.TextFieldChecker;
import fexla.vor.ui.model.DiagramModel;
import fexla.vor.ui.model.LayerModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
    private AnchorPane scrollPaneOut;
    @FXML
    private BorderPane imageContainer;
    @FXML
    private ImageView imageView;
    @FXML
    private VBox LayerEditBox;


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
        dm = new DiagramModel();
        items = new ArrayList<>();
        LayerButtons = new ArrayList<>();
        LayerButton.nameIndex = 1;
        layerModelMap = new HashMap<>();

        BackgroundFill backgroundFill = new BackgroundFill(Color.rgb(246, 246, 247), null, null);
        selectedBackgrand = new Background(backgroundFill);

        imageView.fitWidthProperty().bind(imageContainer.widthProperty());
        imageContainer.heightProperty().addListener((observableValue, o, n) -> imageView.setFitHeight(n.intValue() - 60));
        diagramImage = new DiagramImage(imageView);
        dm.setDiagramImage(diagramImage);
        dm.setBlockLength(5);
        dm.setColorLayer(2);

        loadLayerButton();
        loadLayerButton();
        loadLayerButton();
        loadLayerButton();
        updateLayerButtonLayout();

        dm.update();
    }

    private void loadLayerButton() {
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
        });
        ((LayerButton) loader.getController()).setController(this);
        LayerButtons.add(b);
        LayerModel lm = new LayerModel((LayerButtons.size() + 1) * 10);
        lm.setName("");
        lm.setDiagramModel(dm);
        dm.add(lm);
        layerModelMap.put(b, lm);
    }

    public void updateLayerButtonLayout() {
        for (int i = 0; i < LayerButtons.size(); i++) {
            AnchorPane anchorPane = LayerButtons.get(i);
            updataLayerButtonLocation(i);
            anchorPane.setVisible(true);
        }
        LayerOverview.setMinHeight((LayerButtons.size() - 1) * (LayerButtonHeight + LayerButtonPad));
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
}