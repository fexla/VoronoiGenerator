package fexla.vor.ui.view;

import fexla.vor.ui.model.LayerModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**
 * @author ：fexla
 * @description：TODO
 * @date ：2021/2/22 19:33
 */
public class LayerButton {
    private EditUIController controller;
    @FXML
    private Label label;
    @FXML
    private Label nameLabel;
    @FXML
    private TextField nameField;
    @FXML
    private HBox hooker;
    private String name;
    private boolean dragging;


    private double y = 0;

    @FXML
    private AnchorPane pane;

    private LayerModel model;

    public void initialize() {
        label.setLayoutX(20);
        label.setLayoutY(pane.getPrefHeight() / 2 - label.getPrefHeight() / 2 - 7);
        nameLabel.setLayoutX(50);
        nameLabel.setLayoutY(pane.getPrefHeight() / 2 - nameLabel.getPrefHeight() / 2 - 7);
        nameField.setLayoutX(50);
        nameField.prefHeightProperty().bind(nameLabel.heightProperty());
        nameField.setLayoutY(pane.getPrefHeight() / 2 - nameField.getPrefHeight() / 2 - 10);

        nameField.focusedProperty().addListener((observableValue, b1, b2) -> {
            if (b1 && (!b2)) {
                nameLabel.setText(nameField.getText());
                model.setName(nameField.getText());
                nameField.setVisible(false);
                nameLabel.setVisible(true);
            }
        });
        hooker.setLayoutY(pane.getPrefHeight() / 2 - hooker.getPrefHeight() / 2 + 2);
        hooker.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            hooker.setCursor(Cursor.CLOSED_HAND);
            y = event.getY();
        });
        hooker.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> {
            ((HBox) event.getSource()).setCursor(Cursor.DEFAULT);
            pane.toFront();
            pane.setOpacity(1);
            dragging = false;
            controller.updateLayerButtonLayout();
        });
        hooker.addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> {
            double distanceY = event.getY() - y;
            double y2 = pane.getLayoutY() + distanceY;
            pane.setLayoutY(y2);
            pane.toFront();
            pane.setOpacity(0.5);
            dragging = true;
            controller.updateLayerButtonSwap(pane);
            controller.setJustDragged(true);
        });
    }

    public EditUIController getController() {
        return controller;
    }

    public void setController(EditUIController controller) {
        this.controller = controller;
    }

    public boolean isDragging() {
        return dragging;
    }

    public void setDragging(boolean dragging) {
        this.dragging = dragging;
    }

    public LayerModel getModel() {
        return model;
    }

    public void setModel(LayerModel model) {
        this.model = model;
        name = model.getName();
        nameLabel.setText(name);
        nameField.setText(name);
    }

    @FXML
    private void nameLabelClicked() {
        nameLabel.setVisible(false);
        nameField.setVisible(true);
        nameField.requestFocus();
    }
}
