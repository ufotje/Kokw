package be.kokw.utility.controller;

import be.kokw.utility.validation.Warning;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public interface SetTextField {
    static void set(HBox hBox, String value) {
        TextField textField = new TextField();
        textField.setAlignment(Pos.CENTER);
        textField.setText(value);
        textField.setOnMouseClicked(event -> Warning.alert("Error!", "Dit veld kan niet aangepast worden!"));
        HBox.setMargin(textField, new Insets(0, 0, 30, 0));
        hBox.getChildren().add(textField);
    }
}
