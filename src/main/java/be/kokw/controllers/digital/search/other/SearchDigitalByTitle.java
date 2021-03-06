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
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import static javafx.collections.FXCollections.observableArrayList;

/**
 * Created by Demesmaecker Daniel on 3/11/2017.
 * The find by titleClass
 */

@Component
public class SearchDigitalByTitle {
    @FXML
    private TextField title;
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
    private void SetRepo(@Qualifier("digitalRepo") DigitalRepo repo) {
        this.repo = repo;
    }

    /**
     * Returns all the carriers with a by the user specified title
     */
    @FXML
    public void search(){
        if(Validation.emptyValidation("Titel",title.getText().isEmpty())){
            ObservableList<Digital> digiList = observableArrayList(repo.findByTitle(title.getText()));
            if(digiList.get(0) != null){
                MenuController.window.close();
                ChangeScene.init("/fxml/books/found/other/tableViewByTitle.fxml", "Books by Title");
                DigitalTable.init(table, idCol, depotCol, volumeCol, titleCol, topicCol, authorCol, subTitleCol, publisherCol, yearCol, digiList);
            }else{
                Warning.alert("Book Not Found","The book '" + title.getText() + "' has not been found!");
                MenuController.window.close();
            }
        }
    }
}

