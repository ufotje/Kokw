package be.kokw.controllers.digital.search.bought;

import be.kokw.bean.digital.Digital;
import be.kokw.controllers.MenuController;
import be.kokw.repositories.digital.DigitalRepo;
import be.kokw.utility.controller.tables.DigitalTable;
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

import java.time.LocalDate;

import static javafx.collections.FXCollections.observableArrayList;

/**
 * Created By Demesmaecker Daniel
 */

@Component
public class SearchDigitalByBoughtOnBetween {
    @FXML
    private DatePicker start;
    @FXML
    private DatePicker end;
    @FXML
    private TableView<Digital> table;
    @FXML
    private TableColumn<Digital, Integer> idCol;
    @FXML
    private TableColumn<Digital, String> depotCol;
    @FXML
    private TableColumn<Digital, Integer> volumeCol;
    @FXML
    private TableColumn<Digital, LocalDate> boughtCol;
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
    private DigitalRepo digitalRepo;

    @Autowired
    private void setDigitalRepo(@Qualifier("digitalRepo") DigitalRepo repo) {
        digitalRepo = repo;
    }

    /**
     * Shows a table with all digital carriers bought between two by the user specified dates
     */
    @FXML
    public void search() throws Exception {
        ObservableList<Digital> digiList = observableArrayList(digitalRepo.findByBoughtOnBetween(start.getValue(), end.getValue()));
        if (digiList.isEmpty()) {
            Warning.alert("No Digital Carriers found!", "Er werden geen digitale gevonden die tussen " + start.getValue() + " en " + end.getValue() + " werden aangekocht.");
            MenuController.window.close();
            ChangeScene.init("/fxml/home.fxml", "KOKW - Het verleden draait altijd mee!");
        } else {
            MenuController.window.close();
            ChangeScene.init("/fxml/digital/found/bought/tableviewByBoughtOnBetween.fxml", "Digitals by Bought Between");
            boughtCol.setCellValueFactory(new PropertyValueFactory<>("boughtOn"));
            DigitalTable.init(table, idCol, depotCol, volumeCol, titleCol, topicCol, authorCol, subTitleCol, publisherCol, yearCol, digiList);
        }
    }
}
