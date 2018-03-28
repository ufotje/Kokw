package be.kokw.controllers.books.search.donatedAgainst;

import be.kokw.bean.books.GiftedFor;
import be.kokw.controllers.MenuController;
import be.kokw.repositories.books.GiftedForRepo;
import be.kokw.utility.sceneControl.ChangeScene;
import be.kokw.utility.rowFactories.RowFactoryGF;
import be.kokw.utility.validation.Warning;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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
public class SearchBookByGiftedForOnName {
    @FXML
    private TextField name;
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
    private TableColumn<GiftedFor,LocalDate> conDateCol;
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
    public void initialize(){
        name.setOnAction(event -> search());
        List<String> names = repo.getNames();
        TextFields.bindAutoCompletion(name, names);
    }

    /**
     * Shows a table with all the books traded with a by the user specified name
     * When clicked on a row it opens the contract
     */
    @FXML
    public void search() {
        ObservableList<GiftedFor> bookList = observableArrayList(repo.findByName(name.getText()));
        if (bookList.isEmpty()) {
            Warning.alert("No Books found!", "Er werden geen boeken gevonden die door " + name.getText() + " werden geruild.");
            MenuController.window.close();
        } else {
            MenuController.window.close();
            ChangeScene.init("/fxml/books/found/giftedFor/tableviewGiftedForOnName.fxml", "Books Traded By " + name.getText());
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
