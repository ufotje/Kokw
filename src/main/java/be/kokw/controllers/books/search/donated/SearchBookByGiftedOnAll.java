package be.kokw.controllers.books.search.donated;

import be.kokw.bean.books.Book;
import be.kokw.bean.books.Gifted;
import be.kokw.repositories.books.BookRepo;
import be.kokw.repositories.books.GiftedRepo;
import be.kokw.utility.controller.tables.BookTable;
import be.kokw.utility.rowFactories.RowFactoryGiftedBooks;
import be.kokw.utility.sceneControl.ChangeScene;
import be.kokw.utility.sceneControl.NewStage;
import be.kokw.utility.validation.Warning;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

import static javafx.collections.FXCollections.observableArrayList;

@Component
public class SearchBookByGiftedOnAll {
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private DatePicker dateValue;
    @FXML
    TableView<Book> table;
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
    private GiftedRepo repo;
    private BookRepo bookRepo;
    private Stage window;

    @Autowired
    private void setRepo(@Qualifier("giftedRepo") GiftedRepo repo) {
        this.repo = repo;
    }

    @Autowired
    private void setBookRepo(@Qualifier("bookRepo") BookRepo repo) {
        bookRepo = repo;
    }

    @FXML
    public void initialize() {
        ObservableList<Book> bookList = observableArrayList();
        List<Gifted> list = repo.findAll();
        for(Gifted g : list){
            bookList.add(g.getBook());
        }
        if (bookList.isEmpty()) {
            Warning.alert("No Books found!", "Er werden geen boeken gevonden die werden gedoneerd.");
        } else {
            ChangeScene.init("/fxml/books/found/gifted/tableviewByGiftedOnAll.fxml", "All Donated Books");
            BookTable.init(table, idCol, isbnCol, depotCol, titleCol, editionCol, volumeCol, topicCol, authorCol,
                    subTitleCol, publisherCol, yearCol, pagesCol, illusCol, copiesCol, bookList);
            window = NewStage.getStage("DonatieDetails", "/fxml/books/found/gifted/giftedAllDetails.fxml");
            RowFactoryGiftedBooks.set(table, firstName, lastName, dateValue, repo, window);
        }
    }

    @FXML
    private void close(){
        window.close();
    }
}
