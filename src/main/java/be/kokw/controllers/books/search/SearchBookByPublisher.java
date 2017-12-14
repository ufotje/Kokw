package be.kokw.controllers.books.search;

import be.kokw.bean.Book;
import be.kokw.controllers.MenuController;
import be.kokw.repositories.books.BookRepo;
import be.kokw.utility.Warning;
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
 * Created by ufotje on 3/11/2017.
 */

@Component
public class SearchBookByPublisher {
    @FXML
    private TextField publisher;
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
    private TableColumn<Book,String> lastNameCol ;
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
    private void search() throws Exception {
        if(Validation.emptyValidation("Uitgeverij",publisher.getText().isEmpty())){
            ObservableList<Book> bookList = observableArrayList(bookRepo.findByPublisher(publisher.getText()));
            if(!(bookList.isEmpty())){
                MenuController.window.close();
                ChangeScene.init("/fxml/books/found/tableviewByPublisher.fxml", "Books by Publisher");
                table.setEditable(true);
                idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
                titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
                topicCol.setCellValueFactory(new PropertyValueFactory<>("topic"));
                firstNameCol.setCellValueFactory(new PropertyValueFactory<>("authorFirstName"));
                lastNameCol.setCellValueFactory(new PropertyValueFactory<>("authorLastName"));
                publisherCol.setCellValueFactory(new PropertyValueFactory<>("publisher"));
                placeCol.setCellValueFactory(new PropertyValueFactory<>("place"));
                yearCol.setCellValueFactory(new PropertyValueFactory<>("yearPublished"));
                pagesCol.setCellValueFactory(new PropertyValueFactory<>("nrOfPages"));
                table.setItems(bookList);
            }else{
                Warning.alert("Book Not Found","Er werden geen boeken gevonden die werden uitgegeven door: '" + publisher.getText() + "'!");
            }
        }
    }
}