package be.kokw.controllers.books.search.donated;

import be.kokw.bean.books.Gifted;
import be.kokw.controllers.MenuController;
import be.kokw.repositories.books.GiftedRepo;
import be.kokw.utility.ChangeScene;
import be.kokw.utility.Warning;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static javafx.collections.FXCollections.observableArrayList;

@Component
public class SearchBookByGiftedOnBetween {
    @FXML
    private DatePicker start;
    @FXML
    private DatePicker end;
    @FXML
    private TableView<Gifted> table;
    @FXML
    private TableColumn<Gifted, Integer> idCol;
    @FXML
    private TableColumn<Gifted, String> isbnCol;
    @FXML
    private TableColumn<Gifted, String> boekIdCol;
    @FXML
    private TableColumn<Gifted, String> depotCol;
    @FXML
    private TableColumn<Gifted, String> giftedByCol;
    @FXML
    private TableColumn<Gifted, LocalDate> giftedOnCol;
    @FXML
    private TableColumn<Gifted, String> titleCol;
    @FXML
    private TableColumn<Gifted, String> authorCol;
    private GiftedRepo repo;

    @Autowired
    private void setBookRepo(@Qualifier("giftedRepo") GiftedRepo repo) {
        this.repo = repo;
    }

    @FXML
    public void search() throws Exception {
        ObservableList<Gifted> bookList = observableArrayList(repo.findByGiftedOnBetween(start.getValue(), end.getValue()));
        if (bookList.isEmpty()) {
            Warning.alert("No Books found!", "Er werden geen boeken gevonden die tussen " + start.getValue() + " en " + end.getValue() + " werden gedoneerd.");
            MenuController.window.close();
        } else {
            MenuController.window.close();
            ChangeScene.init("/fxml/books/found/tableviewGiftedOnBetween.fxml", "Books by Donated Between");
            table.setEditable(true);
            idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            isbnCol.setCellValueFactory(new PropertyValueFactory<>("isbn"));
            boekIdCol.setCellValueFactory(new PropertyValueFactory<>("bookId"));
            depotCol.setCellValueFactory(new PropertyValueFactory<>("depot"));
            titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
            authorCol.setCellValueFactory(new PropertyValueFactory<>("authors"));
            giftedByCol.setCellValueFactory(new PropertyValueFactory<>("nameGifter"));
            giftedOnCol.setCellValueFactory(new PropertyValueFactory<>("giftedOn"));
            table.setItems(bookList);
        }
    }
}
