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

import static javafx.collections.FXCollections.observableArrayList;

/**
 * Created by ufotje on 3/11/2017.
 */
public class ByTitle {
    @FXML
    private TextField title;
    @FXML
    private TableView<Book> table;
    @FXML
    private TableColumn<Book,Integer> idCol = new TableColumn();
    @FXML
    private TableColumn<Book,String> titleCol = new TableColumn();
    @FXML
    private TableColumn<Book,String> topicCol = new TableColumn();
    @FXML
    private TableColumn<Book,String> firstNameCol = new TableColumn();
    @FXML
    private TableColumn<Book,String> lastNameCol = new TableColumn();
    @FXML
    private TableColumn<Book,String> publisherCol = new TableColumn();
    @FXML
    private TableColumn<Book,String> placeCol = new TableColumn();
    @FXML
    private TableColumn<Book,String>yearCol = new TableColumn();
    @FXML
    private TableColumn<Book,String>pagesCol = new TableColumn();
    private BookRepo bookRepo;

    @Autowired
    private void SetBookRepo(@Qualifier("bookRepo") BookRepo repo) {
        bookRepo = repo;
    }

    @FXML
    private void search() throws Exception {
        if(Validation.emptyValidation("Titel",title.getText().isEmpty())){
            ObservableList<Book> bookList = observableArrayList(bookRepo.findByTitle(title.getText()));
            if(!(bookList.isEmpty())){
                ChangeScene.init("/fxml/books/found/tableView.fxml", "Books by Title");
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
            }else{
                Alert.alert("Book Not Found","The book '" + title.getText() + "' has not been found!");
            }
        }
    }
}

