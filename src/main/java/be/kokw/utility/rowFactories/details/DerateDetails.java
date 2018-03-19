package be.kokw.utility.rowFactories.details;

import be.kokw.Main;
import be.kokw.bean.books.Derated;
import be.kokw.controllers.books.search.donated.Details;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public interface DerateDetails {
    static void set(int id, int bookId, String isbn, String title, String Authors, LocalDate date, String destination){
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/books/found/gifted/giftedAllDetails.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Derated controller = loader.getController();
        Scene scene = new Scene(root);
        stage.setTitle("DeclasseringDetails van het book: '" + title + "'");
        stage.setScene(scene);
        stage.show();
    }
}
