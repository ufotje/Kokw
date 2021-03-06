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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.textfield.TextFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

import static javafx.collections.FXCollections.observableArrayList;

/**
 * Created By Demesmaecker Daniel
 */

@Component
public class SearchBookByGiftedForOnNameAndDate {
    @FXML
    private TextField name;
    @FXML
    private DatePicker date;
    @FXML
    private TableView<GiftedFor> table;
    @FXML
    private TableColumn<GiftedFor, Integer> idCol;
    @FXML
    private TableColumn<GiftedFor, String> isbnCol;
    @FXML
    private TableColumn<GiftedFor, String> boekIdCol;
    @FXML
    private TableColumn<GiftedFor, String> depotCol;
    @FXML
    private TableColumn<GiftedFor, String> conNameCol;
    @FXML
    private TableColumn<GiftedFor, File> conCol;
    @FXML
    private TableColumn<GiftedFor, LocalDate> conDateCol;
    @FXML
    private TableColumn<GiftedFor, String> conNrCol;
    @FXML
    private TableColumn<GiftedFor, String> titleCol;
    @FXML
    private TableColumn<GiftedFor, String> authorCol;
    private GiftedForRepo repo;

    @Autowired
    private void setRepo(@Qualifier("giftedForRepo") GiftedForRepo repo) {
        this.repo = repo;
    }

    @FXML
    public void initialize() {
        date.setOnAction(event -> search());
        List<String> names = repo.getNames();
        TextFields.bindAutoCompletion(name, names);
    }

    /**
     * Shows a table with all the books traded on a by the user specified date and by a user specified name
     * When clicked on a row it opens the contract
     */
    @FXML
    public void search() {
        if (date.getValue() != null && name.getText() != null) {
            ObservableList<GiftedFor> bookList = observableArrayList(repo.findByContractDateAndName(date.getValue(), name.getText()));
            if (bookList.isEmpty()) {
                Warning.alert("No Books found!", "Er werden geen boeken gevonden die door " + name.getText() + " op " + date.getValue() + " werden gedoneerd met tegenprestatie.");
                MenuController.window.close();
            } else {
                MenuController.window.close();
                ChangeScene.init("/fxml/books/found/giftedFor/tableviewGiftedForOnNameAndDate.fxml", "Books by Donated for by on");
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
        }else {
            Warning.alert("Wrong value for inputfields", "Gelieve uw velden te controleren");
        }
    }
}
