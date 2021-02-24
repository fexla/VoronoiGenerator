package fexla.vor.ui.view;

import fexla.vor.ui.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author ：fexla
 * @description：TODO
 * @date ：2021/2/19 22:15
 */
public class LayoutController {
    @FXML
    private void initialize() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(LayoutController.class.getResource("EditUI.fxml"));
        AnchorPane pane=null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
