package fexla.vor.ui.item;

import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

/**
 * @author ：fexla
 * @description：TODO
 * @date ：2021/2/24 17:17
 */
public class ItemTextField extends Item {


    public TextField textField;

    public TextFieldChecker checker;
    public TextFieldPropertyUpdator updator;

    public String oldValue;


    public ItemTextField(HBox box, String string, TextFieldChecker checker, String startValue, TextFieldPropertyUpdator updator) {
        super(box, string);
        textField = new TextField();
        textField.setText(startValue);
        oldValue = startValue;
        textField.setPrefHeight(25);
        textField.setFont(new Font(12));
        this.checker = checker;
        this.updator = updator;
        textField.focusedProperty().addListener((observable, b1, b2) -> {
            if (!b2)
                if (!checker.check(textField.getText())) textField.setText(oldValue);
                else {
                    oldValue = textField.getText();
                    updator.update(oldValue);
                }
        });
        box.getChildren().add(textField);
    }
}

