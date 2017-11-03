package be.kokw.utility;

import javafx.fxml.FXML;

/**
 * Created by ufotje on 3/11/2017.
 */
public interface Alert {
    @FXML
    static void alert(String title, String content) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();

    }
}

