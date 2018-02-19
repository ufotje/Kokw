package be.kokw.utility.controller;

import be.kokw.bean.digital.Digital;
import javafx.geometry.Insets;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public interface UpdateDate {

    static VBox update(Digital digital, VBox vBox){
        HBox hb9 = GetHbox.get("Datum Aankoop:");
        DatePicker date2 = new DatePicker();
        date2.setValue(digital.getBoughtOn());
        date2.valueProperty().addListener((observable, oldValue, newValue) -> digital.setBoughtOn(newValue));
        hb9.getChildren().add(date2);
        HBox.setMargin(date2, new Insets(0, 0, 30, 0));
        vBox.getChildren().add(hb9);
        return vBox;
    }
}
