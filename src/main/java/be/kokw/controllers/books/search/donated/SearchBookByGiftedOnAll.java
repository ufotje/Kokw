package be.kokw.controllers.books.search.donated;

import be.kokw.bean.Gifted;
import be.kokw.repositories.books.GiftedRepo;
import be.kokw.utility.Warning;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static javafx.collections.FXCollections.observableArrayList;

@Component
public class SearchBookByGiftedOnAll {
    @FXML
    private TableView<Gifted> table;
    @FXML
    private TableColumn<Gifted, Integer> idGCol;
    @FXML
    private TableColumn<Gifted, Integer> bookIdCol;
    @FXML
    private TableColumn<Gifted, String> isbnGCol;
    @FXML
    private TableColumn<Gifted, String> depotGCol;
    @FXML
    private TableColumn<Gifted, String> giftedByCol;
    @FXML
    private TableColumn<Gifted, LocalDate> giftedOnCol;
    @FXML
    private TableColumn<Gifted, String> titleGCol;
    @FXML
    private TableColumn<Gifted, String> authorGCol;
    private GiftedRepo repo;

    @Autowired
    private void setBookRepo(@Qualifier("giftedRepo") GiftedRepo repo) {
        this.repo = repo;
    }

    @FXML
    public void initialize() {
        ObservableList<Gifted> bookList = observableArrayList(repo.findAll());
        if (bookList.isEmpty()) {
            Warning.alert("No Books found!", "Er werden geen boeken gevonden die werden gedoneerd.");
        } else {
            table.setEditable(true);
            idGCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            isbnGCol.setCellValueFactory(new PropertyValueFactory<>("isbn"));
            bookIdCol.setCellValueFactory(new PropertyValueFactory<>("bookId"));
            depotGCol.setCellValueFactory(new PropertyValueFactory<>("depot"));
            titleGCol.setCellValueFactory(new PropertyValueFactory<>("title"));
            authorGCol.setCellValueFactory(new PropertyValueFactory<>("authors"));
            giftedByCol.setCellValueFactory(new PropertyValueFactory<>("nameGifter"));
            giftedOnCol.setCellValueFactory(new PropertyValueFactory<>("giftedOn"));
            table.setItems(bookList);
        }
    }
}
