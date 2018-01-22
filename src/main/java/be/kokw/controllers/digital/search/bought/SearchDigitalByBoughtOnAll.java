package be.kokw.controllers.digital.search.bought;

import be.kokw.bean.digital.Digital;
import be.kokw.repositories.digital.DigitalRepo;
import be.kokw.utility.ChangeScene;
import be.kokw.utility.Warning;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static javafx.collections.FXCollections.observableArrayList;

@Component
public class SearchDigitalByBoughtOnAll {
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
    private TableColumn<Digital,LocalDate> boughtCol;
    private DigitalRepo digitalRepo;

    @Autowired
    private void setDigitalRepo(@Qualifier("digitalRepo") DigitalRepo repo) {
        digitalRepo = repo;
    }

    @FXML
    public void initialize(){
        ObservableList<Digital> bookList = observableArrayList(digitalRepo.findByBoughtOnIsNotNull());
        if (bookList.isEmpty()) {
            Warning.alert("No Digital Carriers found!", "Er werden geen digitale dragers gevonden die werden aangekocht.");
            ChangeScene.init("/fxml/home.fxml", "KOKW - Het verleden draait altijd mee!");
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
            boughtCol.setCellValueFactory(new PropertyValueFactory<>("boughtOn"));
            table.setItems(bookList);
        }
    }
}
