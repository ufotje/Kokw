package be.kokw.utility.rowFactories;


import be.kokw.bean.books.Book;
import be.kokw.bean.books.Derated;
import be.kokw.repositories.books.DerateRepo;
import be.kokw.utility.rowFactories.details.DerateDetails;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;

/**
 * Created By Demesmaecker Daniel
 */

@SuppressWarnings("unchecked")
public interface RowFactoryBookDerated {

    static void set(TableView table, DerateRepo repo) {
        table.setRowFactory(tv -> {
            TableRow<Book> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 2) {
                    Book clickedRow = row.getItem();
                    Derated derated = repo.findByBook(clickedRow);
                    DerateDetails.set(derated.getId(), clickedRow.getTitle(),derated.getDerated(), derated.getDestination());
                }
            });
            return row;
        });
    }
}
