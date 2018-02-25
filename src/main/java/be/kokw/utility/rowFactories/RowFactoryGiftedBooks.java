package be.kokw.utility.rowFactories;

import be.kokw.Main;
import be.kokw.bean.books.Book;
import be.kokw.bean.books.Gifted;
import be.kokw.controllers.books.search.donated.Details;
import be.kokw.repositories.books.GiftedRepo;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

import java.io.IOException;

@SuppressWarnings("unchecked")
public interface RowFactoryGiftedBooks {
    static void set(TableView table, GiftedRepo repo) {
        table.setRowFactory(tv -> {
            TableRow<Book> row = new TableRow<>();

            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 2) {
                    Book clickedRow = row.getItem();
                    Stage stage = new Stage();
                    FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/books/found/gifted/giftedAllDetails.fxml"));
                    Parent root = null;
                    try {
                        root = loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Details controller = loader.getController();
                    Scene scene = new Scene(root);
                    stage.setTitle("DonatieDetails van het boek '" + clickedRow.getTitle() + "'");
                    stage.setScene(scene);
                    Gifted gifted = repo.findByBookId(clickedRow.getId());
                    String [] name = gifted.getName().split(" ");
                    controller.setFirstName(name[0]);
                    StringBuilder sb = new StringBuilder();
                    for(int i = 1; i < name.length; i++){
                        sb.append(name[i]);
                        sb.append(" ");
                    }
                    controller.setLastName(sb.toString());
                    controller.setDateValue(gifted.getGiftedOn());
                    stage.show();
                }

            });
            return row;
        });
    }
}
