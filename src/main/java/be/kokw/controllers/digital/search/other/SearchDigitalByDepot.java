package be.kokw.controllers.digital.search.other;

import be.kokw.bean.digital.Digital;
import be.kokw.controllers.MenuController;
import be.kokw.repositories.digital.DigitalRepo;
import be.kokw.utility.controller.tables.DigitalTable;
import be.kokw.utility.sceneControl.ChangeScene;
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
public class SearchDigitalByDepot {
    @FXML
    TextField depot;
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
    private void setRepo(@Qualifier("digitalRepo") DigitalRepo repo){
        this.repo = repo;
    }

    /**
     * Shows an table with the digital carrier containing the by the user defined depotnumber
     */
    @FXML
    public void search(){
        ObservableList<Digital> digiList = observableArrayList(repo.findByDepot(depot.getText()));
        if (digiList.isEmpty()) {
            Warning.alert("No Digital Carriers found!", "Er werden geen digitale dragers gevonden met " + depot.getText() + " als depotnummer.");
            MenuController.window.close();
        } else {
            MenuController.window.close();
            ChangeScene.init("/fxml/digital/found/other/tableviewByDepot.fxml", "Books by DepotNumber");
            table.setEditable(true);
            DigitalTable.init(table, idCol, depotCol, volumeCol, titleCol, topicCol, authorCol, subTitleCol, publisherCol, yearCol, digiList);
        }
    }
}
