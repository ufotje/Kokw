package be.kokw.controllers.books.search.other;

import be.kokw.bean.books.Book;
import be.kokw.controllers.MenuController;
import be.kokw.repositories.books.BookRepo;
import be.kokw.utility.controller.tables.BookTable;
import be.kokw.utility.sceneControl.ChangeScene;
import be.kokw.utility.validation.Validation;
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

import java.util.ArrayList;
import java.util.List;

import static javafx.collections.FXCollections.observableArrayList;


/**
 * Created by Demesmaecker on 28/10/2017.
 * The search byAuthorsNameControllerClass
 */

@Component
public class SearchBookByAuthor {
    @FXML
    private TextField firstName, lastName;
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
    private void setBookRepo(@Qualifier("bookRepo") BookRepo repo) {
        bookRepo = repo;
    }

    @FXML
    public void initialize() {
        lastName.setOnAction(event -> search());
        List<String> names = bookRepo.findAuthors();
        List<String> authors = new ArrayList<>();
        for (String s : names) {
            String[] split = s.split("\n");
            for (String sr : split) {
                if (!authors.contains(sr)) {
                    authors.add(sr);
                }
            }
        }
        List<String> firstNames = new ArrayList<>();
        List<String> lastNames = new ArrayList<>();
        for (String s : authors) {
            String[] split = s.split(" ");
            firstNames.add(split[0]);
            for (int i = 1; i < split.length; i++) {
                lastNames.add(split[i]);
            }
        }
        TextFields.bindAutoCompletion(firstName, firstNames);
        TextFields.bindAutoCompletion(lastName, lastNames);
    }

    /**
     * Shows a table with all books written by a by the user specified Author
     */
    @FXML
    public void search() {
        if (Validation.validate("Achternaam Auteur", lastName.getText(), "[a-zA-Z]+") &&
                Validation.validate("Voornaam Auteur:", firstName.getText(), "[a-zA-Z]+")) {
            ObservableList<Book> bookList = observableArrayList(bookRepo.findByAuthorsContains(firstName.getText() + " " + lastName.getText()));
            if (bookList.isEmpty()) {
                Warning.alert("No Books found!", "Er werden geen boeken gevonden geschreven door " + firstName.getText() + " " + lastName.getText());
                MenuController.window.close();
            } else {

                MenuController.window.close();
                ChangeScene.init("/fxml/books/found/other/tableView.fxml", "Books by Author's name");
                BookTable.init(table, idCol, isbnCol, depotCol, titleCol, editionCol, volumeCol, topicCol, authorCol,
                        subTitleCol, publisherCol, yearCol, pagesCol, illusCol, bookList);
            }
        }
    }
}
