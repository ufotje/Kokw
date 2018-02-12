package be.kokw.utility.validation;


import javafx.scene.control.Alert;

/**
 * Created by ufotje on 3/11/2017.
 */
public interface Warning {
    static void alert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();

    }
}

