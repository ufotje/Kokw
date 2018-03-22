package be.kokw.utility.rowFactories.details;

import be.kokw.Main;
import be.kokw.controllers.books.search.derated.DerateDetailsController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public interface DerateDetails {
    static void set(int id, String title, LocalDate date, String destination){
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/books/derated/details/DerateDetails.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        DerateDetailsController controller = loader.getController();
        Scene scene = new Scene(root);
        stage.setTitle("DeclasseringDetails van het book: '" + title + "'");
        controller.setId(id);
        controller.setDestination(destination);
        controller.setDate(date);
        stage.setScene(scene);
        stage.show();
    }
}
