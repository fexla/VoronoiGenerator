package fexla.vor.ui.item;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * @author ：fexla
 * @description：条目
 * @date ：2021/2/24 17:10
 */
public abstract class Item {
    public HBox box;
    public Label txt;


    public Item(HBox box, String string) {
        this.box = box;
        txt = new Label(string);
        box.getChildren().add(txt);
    }
}
