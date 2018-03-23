package be.kokw.controllers.books.search.derated;

import be.kokw.bean.books.Book;
import be.kokw.bean.books.Derated;
import be.kokw.controllers.MenuController;
import be.kokw.repositories.books.DerateRepo;
import be.kokw.utility.controller.tables.BookTable;
import be.kokw.utility.rowFactories.RowFactoryBookDerated;
import be.kokw.utility.sceneControl.ChangeScene;
import be.kokw.utility.validation.Warning;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

import static javafx.collections.FXCollections.observableArrayList;

/**
 * Created By Demesmaecker Daniel
 */

@Component
public class SearchBookByDeratedDate {
    @FXML
    private DatePicker date;
    @FXML
    private TableView<Book> table;
    @FXML
    private TableColumn<Book, Integer> idCol;
    @FXML
    private TableColumn<Book, String> isbnCol;
    @FXML
    private TableColumn<Book, String> depotCol;
    @FXML
    private TableColumn<Book, Integer> editionCol;
    @FXML
    private TableColumn<Book, Integer> volumeCol;
    @FXML
    private TableColumn<Book, Boolean> illusCol;
    @FXML
    private TableColumn<Book, String> titleCol;
    @FXML
    private TableColumn<Book, String> topicCol;
    @FXML
    private TableColumn<Book, String> authorCol;
    @FXML
    private TableColumn<Book, String> subTitleCol;
    @FXML
    private TableColumn<Book, String> publisherCol;
    @FXML
    private TableColumn<Book, String> yearCol;
    @FXML
    private TableColumn<Book, String> pagesCol;
    private DerateRepo repo;

    @Autowired
    private void setRepo(@Qualifier("derateRepo") DerateRepo repo) {
        this.repo = repo;
    }

    /**
     * Shows an table with all the books that have bin derated on a by the user specified date,
     * when clicked on a row the derateDetails are opened
     */
    @FXML
    public void search() {
        List<Derated> derateList = repo.findByDerated(date.getValue());
        ObservableList<Book> bookList = observableArrayList();
        MenuController.window.close();
        for(Derated d: derateList){
            bookList.add(d.getBook());
        }
        if (bookList.isEmpty()) {
            Warning.alert("No Books found!", "Er werden geen boeken gevonden die werden gedeclasseerd op ." + date.getValue());
            ChangeScene.init("/fxml/home.fxml", "KOKW - Het Verleden Draait Altijd Mee!");
        } else {
            ChangeScene.init("/fxml/books/derated/views/tableviewDeratedDate.fxml", "Alle boeken die werden gedeclasseerd op " + date.getValue());
            BookTable.init(table, idCol, isbnCol, depotCol, titleCol, editionCol, volumeCol, topicCol, authorCol,
                    subTitleCol, publisherCol, yearCol, pagesCol, illusCol, bookList);
            RowFactoryBookDerated.set(table, repo);
        }
    }
}
