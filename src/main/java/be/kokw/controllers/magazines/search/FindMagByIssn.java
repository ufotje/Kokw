package be.kokw.controllers.magazines.search;

import be.kokw.bean.magazines.Magazine;
import be.kokw.controllers.MenuController;
import be.kokw.repositories.magazines.MagazineRepo;
import be.kokw.utility.controller.tables.MagazineTable;
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
public class FindMagByIssn {
    @FXML
    private TextField issnField;
    @FXML
    private TableView<Magazine> table;
    @FXML
    private TableColumn<Magazine,String> id;
    @FXML
    private TableColumn<Magazine,String> issn;
    @FXML
    private TableColumn<Magazine,String> name;
    @FXML
    private TableColumn<Magazine,String> topic;
    @FXML
    private TableColumn<Magazine,String> publisher;
    @FXML
    private TableColumn<Magazine,String> nr;
    @FXML
    private TableColumn<Magazine,String> year;
    @FXML
    private TableColumn<Magazine,String> pages;
    @FXML
    private TableColumn<Magazine,String> period;
    @FXML
    private TableColumn<Magazine,String> copies;
    @FXML
    private TableColumn<Magazine,Boolean> illustrated;
    private MagazineRepo repo;

    @Autowired
    private void setRepo(@Qualifier("magRepo") MagazineRepo repo){
        this.repo = repo;
    }

    /**
     * Shows a table of a magazine with the by the user specified ISSN
     */
    @FXML
    public void search(){
        if (Validation.validate("name", issnField.getText(), "[0-9a-zA-Z \\-]+")) {
            ObservableList<Magazine> list = observableArrayList(repo.findMagazineByIssn(issnField.getText()));
            MenuController.window.close();
            if (list.isEmpty()){
                Warning.alert("No Magazines found!", "Er werden geen magazines gevonden met het issnNummer: " + issnField.getText());
                ChangeScene.init("/fxml/home.fxml", "KOKW - Het verleden draait altijd mee!");
            }else{
                ChangeScene.init("/fxml/magazines/search/views/findMagByIssnView.fxml", "Find magazines by issnNumber");
                MagazineTable.init(table, id, issn, name, topic, publisher, nr, year, pages, period, copies, illustrated, list);
            }
        }
    }
}
