package fexla.vor.ui.view;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * @author ：fexla
 * @description：TODO
 * @date ：2021/2/22 19:33
 */
public class LayerButton {
    private EditUIController controller;
    private boolean dragging;


    private double y = 0;

    @FXML
    private AnchorPane pane;

    public void initialize() {
        pane.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            pane.setCursor(Cursor.CLOSED_HAND);
            y = event.getY();
        });
        pane.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> {
            ((AnchorPane) event.getSource()).setCursor(Cursor.DEFAULT);
            dragging = false;
            controller.updateLayerButtonLayout();
        });
        pane.addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> {
            double distanceY = event.getY() - y;
            double y2 = pane.getLayoutY() + distanceY;
            pane.setLayoutY(y2);
            dragging = true;
            controller.updateLayerButtonSwap(pane);
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
}
