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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.controlsfx.control.textfield.TextFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

import static javafx.collections.FXCollections.observableArrayList;

/**
 * Created by Demesmaecker Daniel
 */

@Component
public class SearchBookByDeratedTitle {
    @FXML
    private TextField title;
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

    @FXML
    public void initialize(){
        List<String> titles = repo.getTitle();
        TextFields.bindAutoCompletion(title, titles);
    }

    /**
     * Shows an table with all the books with a by the user specified title that have bin derated,
     * when clicked on a row the derateDetails are opened
     */
    @FXML
    public void search() {
        List<Derated> derateList = repo.findAll();
        ObservableList<Book> bookList = observableArrayList();
        for (Derated d : derateList) {
            bookList.add(d.getBook());
        }
        MenuController.window.close();
        if (bookList.isEmpty()) {
            Warning.alert("No Books found!", "Er werden geen gedeclasseerde boeken gevonden met " + title.getText() + " als titel.");
            ChangeScene.init("/fxml/home.fxml", "KOKW - Het Verleden Draait Altijd Mee!");
        } else {
            ChangeScene.init("fxml/books/derated/views/tableviewDeratedTitle.fxml", "Alle gedeclasseerde boeken met " + title.getText() + " als titel.");
            BookTable.init(table, idCol, isbnCol, depotCol, titleCol, editionCol, volumeCol, topicCol, authorCol,
                    subTitleCol, publisherCol, yearCol, pagesCol, illusCol, bookList);
            RowFactoryBookDerated.set(table, repo);
        }
    }
}
