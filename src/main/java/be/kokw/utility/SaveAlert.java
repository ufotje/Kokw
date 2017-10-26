package be.kokw.utility;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;

/**
 * Created by ufotje on 26/10/2017.
 */
public interface SaveAlert {
    @FXML
    static void saveAlert(String type, String name) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(type + " saved successfully.");
        alert.setHeaderText(null);
        alert.setContentText("The " + type + name + " has been created");
        alert.showAndWait();

    }
}
