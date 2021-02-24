package fexla.vor.ui.view;

import fexla.vor.ui.Main;
import fexla.vor.ui.model.DiagramModel;
import fexla.vor.ui.model.LayerModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    private List<AnchorPane> LayerButtons;

    private static int LayerButtonHeight;
    private static int LayerButtonPad = 10;

    private DiagramModel dm;

    public void initialize() {
        dm = new DiagramModel();
        LayerButtons = new ArrayList<>();
        LayerButton.nameIndex = 1;
        loadLayerButton();
        loadLayerButton();
        loadLayerButton();
        loadLayerButton();
        updateLayerButtonLayout();
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
        ((LayerButton) loader.getController()).setController(this);
        LayerButtonHeight = (int) b.getPrefHeight();
        LayerButtons.add(b);
        LayerModel lm = new LayerModel();
        lm.setName("");
        lm.setUnitLength(10);
        dm.add(lm);
    }

    public void updateLayerButtonLayout() {
        for (int i = 0; i < LayerButtons.size(); i++) {
            AnchorPane anchorPane = LayerButtons.get(i);
            updataLayerButtonLocation(i);
            anchorPane.setVisible(true);
        }
        LayerOverview.setMinHeight((LayerButtons.size() - 1) * (LayerButtonHeight + LayerButtonPad));
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

}