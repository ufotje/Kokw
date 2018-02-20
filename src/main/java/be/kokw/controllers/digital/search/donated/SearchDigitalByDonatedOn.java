package be.kokw.controllers.digital.search.donated;

import be.kokw.bean.books.Gifted;
import be.kokw.bean.digital.Digital;
import be.kokw.bean.digital.DigitalDonated;
import be.kokw.controllers.MenuController;
import be.kokw.repositories.books.GiftedRepo;
import be.kokw.repositories.digital.DigitalDonateRepo;
import be.kokw.utility.controller.tables.DigitalTable;
import be.kokw.utility.rowFactories.RowFactoryDigitalDonated;
import be.kokw.utility.sceneControl.ChangeScene;
import be.kokw.utility.sceneControl.NewStage;
import be.kokw.utility.validation.Warning;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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

    @FXML
    public void search() throws Exception {
        ObservableList<DigitalDonated> digiList = observableArrayList(donateRepo.findByGiftedOn(date.getValue()));
        if (digiList.isEmpty()) {
            Warning.alert("No Books found!", "Er werden geen boeken gevonden die op " + date.getValue() + " werden gedoneerd.");
            MenuController.window.close();
        } else {
            MenuController.window.close();
            ChangeScene.init("/fxml/digital/found/donated/tableviewByGiftedOn.fxml", "Books by Donated on");
            DigitalTable.init(table, idCol, depotCol, volumeCol, titleCol, topicCol, authorCol, subTitleCol, publisherCol, yearCol, digiList);
            window = NewStage.getStage("DonateDetails", "/fxml/digital/found/donated/digitalDonateDetailsBetween.fxml");
            RowFactoryDigitalDonated.setFactory(table, donateRepo, firstName, lastName, dateValue, window);
        }
    }
}
