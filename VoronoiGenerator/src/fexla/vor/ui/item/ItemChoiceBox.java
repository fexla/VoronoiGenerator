package fexla.vor.ui.item;

import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.HBox;

/**
 * @author ：fexla
 * @description：TODO
 * @date ：2021/3/2 22:18
 */
public class ItemChoiceBox extends Item {
    public ChoiceBox choiceBox;

    public String getValue() {
        return (String) choiceBox.getValue();
    }

    public void addAll(String... items) {
        choiceBox.getItems().addAll(items);
    }

    public ItemChoiceBox(HBox box, String string, String... items) {
        super(box, string);
        choiceBox = new ChoiceBox();
        addAll(items);
        choiceBox.getSelectionModel().select(0);
        box.getChildren().add(choiceBox);
    }
}
