package be.kokw.controllers.books.search.other;

import be.kokw.bean.books.Book;
import be.kokw.controllers.MenuController;
import be.kokw.repositories.books.BookRepo;
import be.kokw.utility.controller.tables.BookTable;
import be.kokw.utility.sceneControl.ChangeScene;
import be.kokw.utility.validation.Warning;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


import static javafx.collections.FXCollections.observableArrayList;

@Component
public class SearchBookByIssbn {
    @FXML
    private TextField isbn;
    @FXML
    private Button button;
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
    private TableColumn<Book, Integer> copiesCol;
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
    private void setBookRepo(@Qualifier("bookRepo") BookRepo repo) {
        bookRepo = repo;
    }

    public void initialize() {
        isbn.setOnAction(event -> search());
        button.setOnAction(event -> search());
    }

    @FXML
    public void search() {
        ObservableList<Book> bookList = observableArrayList(bookRepo.findByIsbn(isbn.getText()));
        if (bookList.get(0) == null) {
            Warning.alert("No Books found!", "Er werden geen boeken gevonden met " + isbn.getText() + " als isbnnummer.");
            MenuController.window.close();
        } else {
            MenuController.window.close();
            ChangeScene.init("/fxml/books/found/other/tableviewByIsbn.fxml", "Books by IsbnNumber");
            BookTable.init(table, idCol, isbnCol, depotCol, titleCol, editionCol, volumeCol, topicCol, authorCol,
                    subTitleCol, publisherCol, yearCol, pagesCol, illusCol, copiesCol, bookList);
        }
    }
}

