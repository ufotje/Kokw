package be.kokw.controllers.digital.search.donated;

import be.kokw.bean.digital.Digital;
import be.kokw.bean.digital.DigitalDonated;
import be.kokw.controllers.MenuController;
import be.kokw.repositories.digital.DigitalDonateRepo;
import be.kokw.utility.controller.tables.DigitalTable;
import be.kokw.utility.sceneControl.ChangeScene;
import be.kokw.utility.rowFactories.RowFactoryDigitalDonated;
import be.kokw.utility.validation.Warning;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import static javafx.collections.FXCollections.observableArrayList;

/**
 * Created By Demesmaecker Daniel
 */

@Component
public class SearchDigitalByDonatedBetween {
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
    private DatePicker start;
    @FXML
    private DatePicker end;
    private DigitalDonateRepo donateRepo;

    @Autowired
    private void setDonateRepo(@Qualifier("digiDonateRepo") DigitalDonateRepo repo) {
        donateRepo = repo;
    }

    /**
     * Shows a table with all digital carriers donated between two by the user specified dates
     */
    @FXML
    public void search() {
        ObservableList<DigitalDonated> donateList = observableArrayList(donateRepo.findByGiftedOnBetween(start.getValue(), end.getValue()));
        ObservableList<Digital> digiList = observableArrayList();
        for (DigitalDonated donated : donateList) {
            digiList.add(donated.getDigital());
        }
        if (digiList.isEmpty()) {
            Warning.alert("No Digital Carriers found!", "Er werden geen digitale dragers gevonden met die werden gedoneerd");
            MenuController.window.close();
        } else {
            MenuController.window.close();
            ChangeScene.init("/fxml/digital/found/donated/tableviewGiftedOnBetween.fxml", "Donated digital carriers between " + start.getValue() + " and " + end.getValue());
            DigitalTable.init(table, idCol, depotCol, volumeCol, titleCol, topicCol, authorCol, subTitleCol, publisherCol, yearCol, digiList);
            RowFactoryDigitalDonated.setFactory(table, donateRepo);
        }
    }
}
