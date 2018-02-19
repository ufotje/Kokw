package be.kokw.utility.controller;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public interface GetHbox {
    static HBox get(String value) {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        Label label1 = new Label(value);
        hBox.getChildren().add(label1);
        HBox.setMargin(label1, new Insets(0, 20, 30, 100));
        return hBox;
    }
}
