package fexla.vor.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


import java.io.IOException;

/**
 * @author ：fexla
 * @description：TODO
 * @date ：2021/2/19 21:40
 */
public class Main extends Application {

    public static Stage stage;
    private static BorderPane layout;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        loadLayout();
        loadEditUI();
        stage.show();
    }

    private void loadLayout() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/Layout.fxml"));
        try {
            layout = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(layout);
        stage.setScene(scene);
        scene.getStylesheets().add(Main.class.getResource("view/s.css").toExternalForm());
        stage.setMinWidth(layout.getMinWidth());
        stage.setMinHeight(layout.getMinHeight() + 50);
    }

    private void loadEditUI() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/EditUI.fxml"));
        AnchorPane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        layout.setBottom(pane);
    }
}
