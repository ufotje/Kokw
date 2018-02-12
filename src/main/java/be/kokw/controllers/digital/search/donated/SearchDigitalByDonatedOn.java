package be.kokw.controllers.digital.search.donated;

import be.kokw.bean.books.Gifted;
import be.kokw.controllers.MenuController;
import be.kokw.repositories.books.GiftedRepo;
import be.kokw.utility.sceneControl.ChangeScene;
import be.kokw.utility.validation.Warning;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import static javafx.collections.FXCollections.observableArrayList;

@Component
public class SearchDigitalByDonatedOn {
    @FXML
    private DatePicker date;
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
        ObservableList<Gifted> bookList = observableArrayList(repo.findByGiftedOn(date.getValue()));
        if (bookList.isEmpty()) {
            Warning.alert("No Books found!", "Er werden geen boeken gevonden die op " + date.getValue() + " werden gedoneerd.");
            MenuController.window.close();
        } else {
            MenuController.window.close();
            ChangeScene.init("/fxml/digital/found/donated/tableviewByGiftedOn.fxml", "Books by Donated on");
            table.setEditable(true);
            idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            isbnCol.setCellValueFactory(new PropertyValueFactory<>("isbn"));
            boekIdCol.setCellValueFactory(new PropertyValueFactory<>("bookId"));
            depotCol.setCellValueFactory(new PropertyValueFactory<>("depot"));
            titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
            authorCol.setCellValueFactory(new PropertyValueFactory<>("authors"));
            table.setItems(bookList);
        }
    }
}
