package be.kokw.utility;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public interface DeleteAlert {
    @FXML
    static void deleteAlert(String type, String name) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(type + " deleted successfully.");
        alert.setHeaderText(null);
        alert.setContentText("The " + type + name + " has been deleted");
        alert.showAndWait();

    }
}
