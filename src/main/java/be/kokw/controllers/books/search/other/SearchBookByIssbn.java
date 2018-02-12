package be.kokw.controllers.books.search.other;

import be.kokw.bean.books.Book;
import be.kokw.controllers.MenuController;
import be.kokw.repositories.books.BookRepo;
import be.kokw.utility.sceneControl.ChangeScene;
import be.kokw.utility.validation.Warning;
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

@Component
public class SearchBookByIssbn {
    @FXML
    TextField isbn;
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

    @FXML
    public void search() throws Exception {
        ObservableList<Book> bookList = observableArrayList(bookRepo.findByIsbn(isbn.getText()));
        if (bookList.isEmpty()) {
            Warning.alert("No Books found!", "Er werden geen boeken gevonden met " + isbn.getText() + " als isbnnummer.");
            MenuController.window.close();
        } else {
            MenuController.window.close();
            ChangeScene.init("/fxml/books/found/other/tableviewByIsbn.fxml", "Books by IsbnNumber");
            table.setEditable(true);
            idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            isbnCol.setCellValueFactory(new PropertyValueFactory<>("isbn"));
            depotCol.setCellValueFactory(new PropertyValueFactory<>("depot"));
            titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
            editionCol.setCellValueFactory(new PropertyValueFactory<>("edition"));
            volumeCol.setCellValueFactory(new PropertyValueFactory<>("volume"));
            topicCol.setCellValueFactory(new PropertyValueFactory<>("topics"));
            authorCol.setCellValueFactory(new PropertyValueFactory<>("authors"));
            subTitleCol.setCellValueFactory(new PropertyValueFactory<>("subtitles"));
            publisherCol.setCellValueFactory(new PropertyValueFactory<>("publisher"));
            yearCol.setCellValueFactory(new PropertyValueFactory<>("yearPublished"));
            pagesCol.setCellValueFactory(new PropertyValueFactory<>("nrOfPages"));
            illusCol.setCellValueFactory(new PropertyValueFactory<>("illustrated"));
            copiesCol.setCellValueFactory(new PropertyValueFactory<>("copies"));
            table.setItems(bookList);
        }
    }
}

