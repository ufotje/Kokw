package be.kokw.controllers.books.search.other;

import be.kokw.bean.books.Book;
import be.kokw.controllers.MenuController;
import be.kokw.repositories.books.BookRepo;
import be.kokw.utility.controller.tables.BookTable;
import be.kokw.utility.validation.Warning;
import be.kokw.utility.sceneControl.ChangeScene;
import be.kokw.utility.validation.Validation;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import static javafx.collections.FXCollections.observableArrayList;

/**
 * Created by Demesmaecker Daniel on 3/11/2017.
 * The find by titleClass
 */

@Component
public class SearchBookByTitle {
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
    private BookRepo bookRepo;

    @Autowired
    private void SetBookRepo(@Qualifier("bookRepo") BookRepo repo) {
        bookRepo = repo;
    }

    @FXML
    public void initialize() {
        title.setOnAction(event -> search());
    }

    /**
     * Shows a table with all the books with a by the user specified title
     */
    @FXML
    public void search() {
        if (Validation.emptyValidation("Titel", title.getText().isEmpty())) {
            ObservableList<Book> bookList = observableArrayList(bookRepo.findByTitle(title.getText()));
            if (!bookList.isEmpty()) {
                MenuController.window.close();
                ChangeScene.init("/fxml/books/found/other/tableViewByTitle.fxml", "Books by Title");
                BookTable.init(table, idCol, isbnCol, depotCol, titleCol, editionCol, volumeCol, topicCol, authorCol,
                        subTitleCol, publisherCol, yearCol, pagesCol, illusCol, bookList);
            } else {
                Warning.alert("Book Not Found", "The book '" + title.getText() + "' has not been found!");
                MenuController.window.close();
            }
        }
    }
}

