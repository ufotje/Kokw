package be.kokw.utility.rowFactories;

import be.kokw.bean.books.Book;
import be.kokw.bean.books.Gifted;
import be.kokw.repositories.books.GiftedRepo;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;


public interface RowFactoryGiftedBooks {
    static void set(TableView table, TextField firstName, TextField lastName, DatePicker date, GiftedRepo repo) {
        table.setRowFactory(tv -> {
            TableRow<Book> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 2) {
                    Book clickedRow = row.getItem();
                    Gifted gifted = repo.findByBookId(clickedRow.getId());
                    String [] name = gifted.getName().split(" ");
                    firstName.setText(name[0]);
                    StringBuilder sb = new StringBuilder();
                    for(int i = 1; i < name.length; i++){
                        sb.append(name[i]);
                        sb.append(" ");
                    }
                    lastName.setText(sb.toString());
                    date.setValue(gifted.getGiftedOn());
                    AllDetailWindow.create(firstName, lastName, date);
                }
            });
            return row;
        });
    }
}
