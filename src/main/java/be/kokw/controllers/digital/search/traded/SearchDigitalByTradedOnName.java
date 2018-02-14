package be.kokw.controllers.digital.search.traded;

import be.kokw.bean.digital.DigitalTraded;
import be.kokw.controllers.MenuController;
import be.kokw.repositories.digital.DigitalTradeRepo;
import be.kokw.utility.autocomplete.NameTextField;
import be.kokw.utility.sceneControl.ChangeScene;
import be.kokw.utility.rowFactories.RowFactoryGF;
import be.kokw.utility.validation.Warning;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.LocalDate;

import static javafx.collections.FXCollections.observableArrayList;

@Component
public class SearchDigitalByTradedOnName {
    @FXML
    private TextField name;
    @FXML
    private TableView<DigitalTraded> table;
    @FXML
    private TableColumn<DigitalTraded, Integer> idCol;
    @FXML
    private TableColumn<DigitalTraded, String> isbnCol;
    @FXML
    private TableColumn<DigitalTraded, String> depotCol;
    @FXML
    private TableColumn<DigitalTraded, String> conNameCol;
    @FXML
    private TableColumn<DigitalTraded, File> conCol;
    @FXML
    private TableColumn<DigitalTraded, LocalDate> conDateCol;
    @FXML
    private TableColumn<DigitalTraded, String> conNrCol;
    @FXML
    private TableColumn<DigitalTraded, String> titleCol;
    @FXML
    private TableColumn<DigitalTraded, String> authorCol;
    private DigitalTradeRepo repo;

    @Autowired
    private void setRepo(@Qualifier("digiTradeRepo") DigitalTradeRepo repo) {
        this.repo = repo;
    }

    public void initialize() {
        NameTextField.bind(repo.findAll(), name);
    }

    @FXML
    public void search() throws Exception {
        ObservableList<DigitalTraded> list = observableArrayList(repo.findByName(name.getText()));
        if (list.isEmpty()) {
            Warning.alert("No Digitale Carriers Found!", "Er werden geen digitale dragers gevonden die door " + name.getText() + " werden geruild.");
            MenuController.window.close();
        } else {
            MenuController.window.close();
            ChangeScene.init("/fxml/digital/found/tableviewByGiftedForOnName.fxml", "Books by Donated for by");
            table.setEditable(true);
            idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            isbnCol.setCellValueFactory(new PropertyValueFactory<>("issbn"));
            depotCol.setCellValueFactory(new PropertyValueFactory<>("depot"));
            titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
            authorCol.setCellValueFactory(new PropertyValueFactory<>("authors"));
            conNrCol.setCellValueFactory(new PropertyValueFactory<>("contractNr"));
            conDateCol.setCellValueFactory(new PropertyValueFactory<>("contractDate"));
            conNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            conCol.setCellValueFactory(new PropertyValueFactory<>("contract"));
            table.setItems(list);
            RowFactoryGF.set(table);
        }
    }
}
