package be.kokw.controllers.books.search;

import be.kokw.bean.Book;
import be.kokw.bean.Gifted;
import be.kokw.controllers.MenuController;
import be.kokw.repositories.books.BookRepo;
import be.kokw.utility.ChangeScene;
import be.kokw.utility.Validation;
import be.kokw.utility.Warning;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static javafx.collections.FXCollections.observableArrayList;

@Component
public class SearchBookByTopic {
    @FXML
    private TextField topic;
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
    private TableColumn<Book, LocalDate> boughtCol;
    @FXML
    private TableColumn<Book, Integer> copiesCol;
    @FXML
    private TableColumn<Book, Boolean> deratedCol;
    @FXML
    private TableColumn<Book, String> destinationCol;
    @FXML
    private TableView<Gifted> gifted;
    @FXML
    private TableColumn<Gifted, String> giftedByCol;
    @FXML
    private TableColumn<Gifted, LocalDate> giftedOnCol;
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
    public void search() throws Exception {
        if (Validation.emptyValidation("Topic", topic.getText().isEmpty())) {
            ObservableList<Book> bookList = observableArrayList(bookRepo.findBookByTopicsContains(topic.getText()));
            if (bookList.get(0) != null) {
                ObservableList<Gifted> giftedList = observableArrayList();
                MenuController.window.close();
                ChangeScene.init("/fxml/books/found/tableViewTopic.fxml", "Books by Topic");
                table.setEditable(true);
                gifted.setEditable(true);
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
                boughtCol.setCellValueFactory(new PropertyValueFactory<>("boughtOn"));
                giftedByCol.setCellValueFactory(new PropertyValueFactory<>("name"));
                giftedOnCol.setCellValueFactory(new PropertyValueFactory<>("giftedOn"));
                deratedCol.setCellValueFactory(new PropertyValueFactory<>("derated"));
                destinationCol.setCellValueFactory(new PropertyValueFactory<>("destination"));
                table.setItems(bookList);
                gifted.setItems(giftedList);
            } else {
                Warning.alert("No Books Found", "Er werden geen boeken met '" + topic.getText() + "' als onderwerp gevonden!");
            }
        }
    }
}
