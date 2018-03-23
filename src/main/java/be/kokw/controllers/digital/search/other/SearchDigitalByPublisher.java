package be.kokw.controllers.digital.search.other;

import be.kokw.bean.digital.Digital;
import be.kokw.controllers.MenuController;
import be.kokw.repositories.digital.DigitalRepo;
import be.kokw.utility.controller.tables.DigitalTable;
import be.kokw.utility.sceneControl.ChangeScene;
import be.kokw.utility.validation.Validation;
import be.kokw.utility.validation.Warning;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import static javafx.collections.FXCollections.observableArrayList;

/**
 * Created by Demesmaecker Daniel on 3/11/2017.
 */

@Component
public class SearchDigitalByPublisher {
    @FXML
    private TextField publisher;
    @FXML
    private TableView<Digital> table;
    @FXML
    private TableColumn<Digital,Integer> idCol;
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
    private DigitalRepo repo;

    @Autowired
    private void setRepo(@Qualifier("digitalRepo") DigitalRepo repo) {
        this.repo = repo;
    }

    /**
     * Shows a table with all the carriers published by a user specified publisher
     */
    @FXML
    public void search(){
        if(Validation.emptyValidation("Uitgeverij",publisher.getText().isEmpty())){
            ObservableList<Digital> digiList = observableArrayList(repo.findByPublisher(publisher.getText()));
            if(!(digiList.isEmpty())){
                MenuController.window.close();
                ChangeScene.init("/fxml/digital/found/other/tableviewByPublisher.fxml", "Books by Publisher");
                DigitalTable.init(table, idCol, depotCol, volumeCol, titleCol, topicCol, authorCol, subTitleCol, publisherCol, yearCol, digiList);
            }else{
                Warning.alert("Digital Carrier Not Found","Er werden geen digitale dragers gevonden die werden uitgegeven door: '" + publisher.getText() + "'!");
                MenuController.window.close();
            }
        }
    }
}