package be.kokw.controllers.books.search.donatedAgainst;

import be.kokw.bean.books.GiftedFor;
import be.kokw.controllers.MenuController;
import be.kokw.repositories.books.GiftedForRepo;
import be.kokw.utility.sceneControl.ChangeScene;
import be.kokw.utility.rowFactories.RowFactoryGF;
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

import java.io.File;
import java.time.LocalDate;

import static javafx.collections.FXCollections.observableArrayList;

/**
 * Created By Demesmaecker Daniel
 */

@Component
public class SearchBookByGiftedForOnBetween {
    @FXML
    private DatePicker start;
    @FXML
    private DatePicker end;
    @FXML
    private TableView<GiftedFor> table;
    @FXML
    private TableColumn<GiftedFor, Integer> idCol;
    @FXML
    private TableColumn<GiftedFor, String> isbnCol;
    @FXML
    private TableColumn<GiftedFor, String> depotCol;
    @FXML
    private TableColumn<GiftedFor, String> conNameCol;
    @FXML
    private TableColumn<GiftedFor, File> conCol;
    @FXML
    private TableColumn<GiftedFor, String> conNrCol;
    @FXML
    private TableColumn<GiftedFor, LocalDate> conDateCol;
    @FXML
    private TableColumn<GiftedFor, String> titleCol;
    @FXML
    private TableColumn<GiftedFor, String> boekIdCol;
    @FXML
    private TableColumn<GiftedFor, String> authorCol;
    private GiftedForRepo repo;

    @Autowired
    private void setRepo(@Qualifier("giftedForRepo") GiftedForRepo repo) {
        this.repo = repo;
    }

    @FXML
    public void initialize(){
        end.setOnAction(event -> search());
    }

    /**
     * Shows a table with all the books traded between two by the user specified date
     * When clicked on a row it opens the contract
     */
    @FXML
    public void search(){
        ObservableList<GiftedFor> bookList = observableArrayList(repo.findByContractDateBetween(start.getValue(), end.getValue()));
        if (bookList.isEmpty()) {
            Warning.alert("No Books found!", "Er werden geen boeken gevonden die tussen " + start.getValue() + " en " + end.getValue() + " werden gedoneerd met tegenprestatie.");
            MenuController.window.close();
        } else {
            MenuController.window.close();
            ChangeScene.init("/fxml/books/found/giftedFor/tableviewGiftedForOnBetween.fxml", "Books Donated Between");
            table.setEditable(true);
            idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            isbnCol.setCellValueFactory(new PropertyValueFactory<>("issbn"));
            boekIdCol.setCellValueFactory(new PropertyValueFactory<>("bookId"));
            depotCol.setCellValueFactory(new PropertyValueFactory<>("depot"));
            titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
            authorCol.setCellValueFactory(new PropertyValueFactory<>("authors"));
            conNrCol.setCellValueFactory(new PropertyValueFactory<>("contractNr"));
            conDateCol.setCellValueFactory(new PropertyValueFactory<>("contractDate"));
            conNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            conCol.setCellValueFactory(new PropertyValueFactory<>("contract"));
            table.setItems(bookList);
            RowFactoryGF.set(table);
        }
    }
}
