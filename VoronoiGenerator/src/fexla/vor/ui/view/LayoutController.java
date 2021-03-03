package fexla.vor.ui.view;

import fexla.vor.ui.Main;
import fexla.vor.ui.item.ItemChoiceBox;
import fexla.vor.ui.item.ItemTextField;
import fexla.vor.ui.item.TextFieldChecker;
import fexla.vor.ui.model.DiagramModel;
import fexla.vor.ui.model.ExportModel;
import fexla.vor.ui.model.LayerModel;
import fexla.vor.util.Vector2Dint;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author ：fexla
 * @description：TODO
 * @date ：2021/2/19 22:15
 */
public class LayoutController {
    @FXML
    private void initialize() {
        FXMLLoader loader = new FXMLLoader();
        URL url = LayoutController.class.getResource("EditUI.fxml");
        loader.setLocation(url);
        AnchorPane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void exportUI() {
        Stage exportStage = new Stage();
        StackPane pane = new StackPane();
        Insets insets1 = new Insets(10, 10, 10, 10);
        pane.setPadding(insets1);

        VBox box = new VBox();
        box.setSpacing(8);
        box.setStyle("-fx-border-style: solid;" +
                "-fx-border-color: #668B8B;" +
                " -fx-border-width: 1;" +
                " -fx-border-radius: 5");
        Insets insets2 = new Insets(15, 20, 10, 20);
        box.setPadding(insets2);


        pane.getChildren().add(box);
        pane.setAlignment(Pos.CENTER);
        Scene scene = new Scene(pane);
        HBox hBox = new HBox();
        ItemChoiceBox choiceBox = new ItemChoiceBox(hBox, "格式 :   ", "jpg", "png");
        box.getChildren().add(hBox);

        Label label = new Label();
        label.setText("");
        label.setTextFill(Color.rgb(255, 0, 0));
        TextFieldChecker IntergerChecker = string -> {
            try {
                Integer.parseInt(string);
                label.setText("");
                return true;
            } catch (NumberFormatException e) {
                label.setText("数字输入错误");
                return false;
            }
        };

        hBox = new HBox();
        ItemTextField widthField = new ItemTextField(hBox, "宽度 :   ", IntergerChecker, "1200", null);
        box.getChildren().add(hBox);

        hBox = new HBox();
        ItemTextField heightField = new ItemTextField(hBox, "高度 :   ", IntergerChecker, "800", null);
        box.getChildren().add(hBox);

        hBox = new HBox();
        ItemTextField blockLengthField = new ItemTextField(hBox, "块长 :   ", IntergerChecker, "1", null);
        box.getChildren().add(hBox);

        hBox = new HBox();
        ItemTextField fileNameField = new ItemTextField(hBox, "文件名 : ", string -> {
            boolean res = true;
            if (string == null || string.length() > 255) res = false;
            if (string.contains("/") || string.contains("?") || string.contains("<") || string.contains(">") || string.contains("\"") || string.contains("|"))
                res = false;
            if (string.charAt(string.length() - 1) == '.')
                res = false;
            if (res) return true;
            label.setText("文件名不合法");
            return false;
        }, "paint", null);
        box.getChildren().add(hBox);

        box.getChildren().add(label);

        hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_RIGHT);
        hBox.setSpacing(2);
        Insets insets3 = new Insets(10, 0, 10, 0);
        hBox.setPadding(insets3);

        Button button = new Button();
        button.setText("取消");
        button.setFont(new Font(15));
        button.setOnAction(actionEvent -> exportStage.close());
        hBox.getChildren().add(button);

        button = new Button();
        button.setText("导出");
        button.setFont(new Font(15));
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ExportModel em = new ExportModel();
                em.setFileName(fileNameField.textField.getText());
                em.setFormat(choiceBox.getValue());
                em.setBlockLength(Integer.parseInt(blockLengthField.textField.getText()));
                em.setPixelNum(new Vector2Dint(Integer.parseInt(widthField.textField.getText()), Integer.parseInt(heightField.textField.getText())));
                em.setDiagramImage(EditUIController.instance.getDiagramImage());
                em.export();
                exportStage.close();
            }
        });
        hBox.getChildren().add(button);
        box.getChildren().add(hBox);


        exportStage.setScene(scene);
        exportStage.showAndWait();
    }
}
