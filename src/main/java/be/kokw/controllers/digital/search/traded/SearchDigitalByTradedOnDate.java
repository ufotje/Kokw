package be.kokw.controllers.digital.search.traded;


import be.kokw.bean.digital.DigitalTraded;
import be.kokw.controllers.MenuController;
import be.kokw.repositories.digital.DigitalTradeRepo;
import be.kokw.utility.rowFactories.RowFactoryDigitalDonated;
import be.kokw.utility.rowFactories.RowFactoryDigitalTraded;
import be.kokw.utility.sceneControl.ChangeScene;
import be.kokw.utility.rowFactories.RowFactoryGF;
import be.kokw.utility.validation.Warning;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static javafx.collections.FXCollections.observableArrayList;

@Component
public class SearchDigitalByTradedOnDate {
    @FXML
    private DatePicker date;
    @FXML
    private TableView<DigitalTraded> table;
    @FXML
    private TableColumn<DigitalTraded, Integer> idCol;
    @FXML
    private TableColumn<DigitalTraded, String> isbnCol;
    @FXML
    private TableColumn<DigitalTraded, String> bookIdCol;
    @FXML
    private TableColumn<DigitalTraded, String> depotCol;
    @FXML
    private TableColumn<DigitalTraded, String> conNameCol;
    @FXML
    private TableColumn<DigitalTraded, Hyperlink> conCol;
    @FXML
    private TableColumn<DigitalTraded,LocalDate>conDateCol;
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

    @FXML
    public void search() throws Exception {
        ObservableList<DigitalTraded> list = observableArrayList(repo.findByContractDate(date.getValue()));
        if (list.isEmpty()) {
            Warning.alert("No Books found!", "Er werden geen digitale dragers gevonden die op " + date.getValue() + " werden geruild.");
            MenuController.window.close();
        } else {
            MenuController.window.close();
            ChangeScene.init("/fxml/digital/found/giftedFor/tableviewGiftedForOn.fxml", "Digital Carriers by Traded on");
            table.setEditable(true);
            idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            isbnCol.setCellValueFactory(new PropertyValueFactory<>("issbn"));
            bookIdCol.setCellValueFactory(new PropertyValueFactory<>("bookId"));
            depotCol.setCellValueFactory(new PropertyValueFactory<>("depot"));
            titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
            authorCol.setCellValueFactory(new PropertyValueFactory<>("authors"));
            conNrCol.setCellValueFactory(new PropertyValueFactory<>("contractNr"));
            conDateCol.setCellValueFactory(new PropertyValueFactory<>("contractDate"));
            conNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            conCol.setCellValueFactory(new PropertyValueFactory<>("contract"));
            table.setItems(list);
            RowFactoryDigitalTraded.set(table);
        }
    }
}
