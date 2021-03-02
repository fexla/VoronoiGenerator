package fexla.vor.ui.item;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

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
        txt.setFont(new Font(14));
        box.getChildren().add(txt);
    }
}
