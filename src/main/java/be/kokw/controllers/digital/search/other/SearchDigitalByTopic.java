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
 * Created By Demesmaecker Daniel
 */

@Component
public class SearchDigitalByTopic {
    @FXML
    private TextField topic;
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
    private DigitalRepo repo;

    @Autowired
    private void SetRepo(@Qualifier("digitalRepo") DigitalRepo repo) {
        this.repo = repo;
    }

    /**
     * Shows a table with all digital carriers related to a by the user specified topic
     */
    @FXML
    public void search(){
        if (Validation.emptyValidation("Topic", topic.getText().isEmpty())) {
            ObservableList<Digital> digiList = observableArrayList(repo.findByTopicsContains(topic.getText()));
            if (digiList.get(0) != null) {
                MenuController.window.close();
                ChangeScene.init("/fxml/books/found/other/tableViewTopic.fxml", "Books by Topic");
                DigitalTable.init(table, idCol, depotCol, volumeCol, titleCol, topicCol, authorCol, subTitleCol, publisherCol, yearCol, digiList);
            } else {
                Warning.alert("No Digital Carriers Found", "Er werden geen digitale dragers met '" + topic.getText() + "' als onderwerp gevonden!");
                MenuController.window.close();
            }
        }
    }
}
