package be.kokw.controllers.digital.search.donated;

import be.kokw.bean.digital.Digital;
import be.kokw.bean.digital.DigitalDonated;
import be.kokw.controllers.MenuController;
import be.kokw.repositories.digital.DigitalDonateRepo;
import be.kokw.utility.ChangeScene;
import be.kokw.utility.NewStage;
import be.kokw.utility.RowFactoryDigitalDonated;
import be.kokw.utility.Warning;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import static javafx.collections.FXCollections.observableArrayList;

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
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private DatePicker date;
    @FXML
    private DatePicker start;
    @FXML
    private DatePicker end;
    private DigitalDonateRepo donateRepo;
    private Stage window;

    @Autowired
    private void setDonateRepo(@Qualifier("digiDonateRepo") DigitalDonateRepo repo) {
        donateRepo = repo;
    }

    @FXML
    public void search() throws Exception {
        ObservableList<DigitalDonated> donateList = observableArrayList(donateRepo.findByGiftedOnBetween(start.getValue(), end.getValue()));
        ObservableList<Digital> digiList = observableArrayList();
        for (DigitalDonated donated : donateList) {
            digiList.add(donated.getDigital());
        }
            if (donateList.isEmpty()) {
                Warning.alert("No Digital Carriers found!", "Er werden geen digitale dragers gevonden met die werden gedoneerd");
                MenuController.window.close();
            } else {
                MenuController.window.close();
                ChangeScene.init("/fxml/digital/found/donated/tableviewByGiftedOnBetween.fxml", "Donated digital carriers between " + start.getValue() + " and " + end.getValue());
                table.setEditable(true);
                idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
                depotCol.setCellValueFactory(new PropertyValueFactory<>("depot"));
                titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
                volumeCol.setCellValueFactory(new PropertyValueFactory<>("volume"));
                topicCol.setCellValueFactory(new PropertyValueFactory<>("topics"));
                authorCol.setCellValueFactory(new PropertyValueFactory<>("authors"));
                subTitleCol.setCellValueFactory(new PropertyValueFactory<>("subtitles"));
                publisherCol.setCellValueFactory(new PropertyValueFactory<>("publisher"));
                yearCol.setCellValueFactory(new PropertyValueFactory<>("yearPublished"));
                table.setItems(digiList);

                RowFactoryDigitalDonated.setFactory(table, donateRepo, firstName, lastName, date, NewStage.getStage("DonateDetails", "/fxml/digital/create/gifted/donateDetailsBetween.fxml"));
            }
        }

        @FXML
        private void closeDetails () {
            window.close();
        }
    }
