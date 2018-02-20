package be.kokw.utility.rowFactories;

import be.kokw.utility.controller.GetHbox;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public interface AllDetailWindow {

    static void create(TextField firstName, TextField lastName, DatePicker date){
        Stage window =  new Stage();
        Pane pane = new Pane();
        VBox vBox = new VBox();
        HBox hb = GetHbox.get("Voornaam:");
        hb.getChildren().add(firstName);
        HBox hb2 = GetHbox.get("Achternaam:");
        hb2.getChildren().add(lastName);
        HBox hb3 = GetHbox.get("DonatieDatum:");
        hb.getChildren().add(date);
        vBox.setAlignment(Pos.CENTER);
        Button button = new Button("Sluiten");
        button.setOnAction(e -> window.close());
        vBox.getChildren().addAll(hb, hb2, hb3, button);
        pane.getChildren().addAll(vBox);
        Scene scene = new Scene(pane);
        window.setTitle("Gedoneerde digitale drager Details");
        window.setScene(scene);
        window.show();
    }
}
