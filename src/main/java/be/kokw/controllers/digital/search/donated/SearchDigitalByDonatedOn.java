package be.kokw.controllers.digital.search.donated;

import be.kokw.bean.digital.Digital;
import be.kokw.bean.digital.DigitalDonated;
import be.kokw.controllers.MenuController;
import be.kokw.repositories.digital.DigitalDonateRepo;
import be.kokw.utility.controller.tables.DigitalTable;
import be.kokw.utility.rowFactories.RowFactoryDigitalDonated;
import be.kokw.utility.sceneControl.ChangeScene;
import be.kokw.utility.validation.Warning;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import static javafx.collections.FXCollections.observableArrayList;

@Component
public class SearchDigitalByDonatedOn {
    @FXML
    private DatePicker date;
    @FXML
    private TableView<Digital> table;
    @FXML
    private TableColumn<Digital, Integer> idCol;
    @FXML
    private TableColumn<Digital, String> depotCol;
    @FXML
    private TableColumn<Digital, Integer> volumeCol;
    @FXML
    private TableColumn<Digital, String> titleCol;
    @FXML
    private TableColumn<Digital, String> topicCol;
    @FXML
    private TableColumn<Digital, String> authorCol;
    @FXML
    private TableColumn<Digital, String> subTitleCol;
    @FXML
    private TableColumn<Digital, String> publisherCol;
    @FXML
    private TableColumn<Digital, String> yearCol;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private DatePicker dateValue;
    private DigitalDonateRepo donateRepo;
    private Stage window;

    @Autowired
    private void setDonateRepo(@Qualifier("digiDonateRepo") DigitalDonateRepo repo) {
        donateRepo = repo;
    }

    /**
     * Shows a table with all digital carriers donated on a by the user specified date
     */
    @FXML
    public void search(){
        ObservableList<DigitalDonated> digiList = observableArrayList(donateRepo.findByGiftedOn(date.getValue()));
        if (digiList.isEmpty()) {
            Warning.alert("No Carriers found!", "Er werden geen dragers gevonden die op " + date.getValue() + " werden gedoneerd.");
            MenuController.window.close();
        } else {
            MenuController.window.close();
            ChangeScene.init("/fxml/digital/found/donated/tableviewByGiftedOn.fxml", "Books by Donated on");
            DigitalTable.init(table, idCol, depotCol, volumeCol, titleCol, topicCol, authorCol, subTitleCol, publisherCol, yearCol, digiList);
            RowFactoryDigitalDonated.setFactory(table, donateRepo);
        }
    }
}
