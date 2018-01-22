package be.kokw.controllers.digital.search.donated;

import be.kokw.bean.digital.Digital;
import be.kokw.repositories.digital.DigitalDonateRepo;
import be.kokw.repositories.digital.DigitalRepo;
import be.kokw.utility.NewStage;
import be.kokw.utility.RowFactoryDigitalDonated;
import be.kokw.utility.Warning;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


import static javafx.collections.FXCollections.observableArrayList;

@Component
public class SearchDigitalByDonatedAll {
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
    private DigitalRepo digitalRepo;
    private DigitalDonateRepo donateRepo;

    @Autowired
    private void setDigitalRepo(@Qualifier("digitalRepo") DigitalRepo repo) {
        digitalRepo = repo;
    }

    @Autowired
    private void setDonateRepo(@Qualifier("digiDonateRepo") DigitalDonateRepo repo) {
        donateRepo = repo;
    }

    @FXML
    public void initialize(){
        ObservableList<Digital> bookList = observableArrayList(digitalRepo.findByDonatedIsTrue());
        if (bookList.isEmpty()) {
            Warning.alert("No Digital Carriers found!", "Er werden geen digitale dragers gevonden met die werden gedoneerd");

        } else {
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
            table.setItems(bookList);
//            RowFactoryDigitalDonated.setFactory(table, donateRepo, firstName, lastName, date,NewStage.getStage("DonateDetails","/fxml/digital/found/donated/digitalDonateDetails.fxml"));
        }
    }

    @FXML
    private void closeDetails() {

    }
}
