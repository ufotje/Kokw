package be.kokw.controllers.books.search;

import be.kokw.bean.Book;
import be.kokw.repositories.BookRepo;
import be.kokw.utility.Alert;
import be.kokw.utility.ChangeScene;
import be.kokw.utility.Validation;
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
 * Created by ufotje on 28/10/2017.
 * The search byAuthorsNameControllerClass
 */

@Component
public class SearchBookByName {
    @FXML
    private TextField firstName, lastName;
    @FXML
    private TableView<Book> table;
    @FXML
    private TableColumn<Book,Integer> idCol;
    @FXML
    private TableColumn<Book,String> titleCol;
    @FXML
    private TableColumn<Book,String> topicCol;
    @FXML
    private TableColumn<Book,String> firstNameCol;
    @FXML
    private TableColumn<Book,String> lastNameCol;
    @FXML
    private TableColumn<Book,String> publisherCol;
    @FXML
    private TableColumn<Book,String> placeCol;
    @FXML
    private TableColumn<Book,String>yearCol;
    @FXML
    private TableColumn<Book,String>pagesCol;
    private BookRepo bookRepo;

    @Autowired
    private void SetBookRepo(@Qualifier("bookRepo") BookRepo repo) {
        bookRepo = repo;
    }

    @FXML
    void search() throws Exception {
        if (Validation.validate("Achternaam Auteur", lastName.getText(), "[a-zA-Z]+") &&
                Validation.validate("Voornaam Auteur:", firstName.getText(), "[a-zA-Z]+")) {
            ObservableList<Book> bookList = observableArrayList(bookRepo.findByAuthorFirstNameAndAuthorLastName(firstName.getText(), lastName.getText()));
            if(!(bookList.isEmpty())) {
                ChangeScene.init("/fxml/books/found/tableView.fxml", "Books by Author's name");
                idCol.setCellValueFactory(new PropertyValueFactory("id"));
                titleCol.setCellValueFactory(new PropertyValueFactory("title"));
                topicCol.setCellValueFactory(new PropertyValueFactory("topic"));
                firstNameCol.setCellValueFactory(new PropertyValueFactory("authorFirstName"));
                lastNameCol.setCellValueFactory(new PropertyValueFactory("authorLastName"));
                publisherCol.setCellValueFactory(new PropertyValueFactory("publisher"));
                placeCol.setCellValueFactory(new PropertyValueFactory("place"));
                yearCol.setCellValueFactory(new PropertyValueFactory("yearPublished"));
                pagesCol.setCellValueFactory(new PropertyValueFactory("nrOfPages"));
                table.setItems(bookList);
            }else {
                Alert.alert("No Books found!","Er werden geen boeken gevonden geschreven door " + firstName + " " + lastName);
            }
        }
    }
}
