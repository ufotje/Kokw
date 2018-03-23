package be.kokw.controllers.members.search;

import be.kokw.bean.Member;
import be.kokw.controllers.MenuController;
import be.kokw.repositories.MemberRepo;
import be.kokw.utility.autocomplete.TextFieldsMembers;
import be.kokw.utility.controller.tables.MemberTable;
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

import java.time.LocalDate;

import static javafx.collections.FXCollections.observableArrayList;

/**
 * Created By Demesmaecker Daniel
 */

@Component
public class MembersByCity {
    @FXML
    private TextField city;
    @FXML
    private TableView<Member> table;
    @FXML
    private TableColumn<Member, Integer> idCol;
    @FXML
    private TableColumn<Member, String> firstNameCol;
    @FXML
    private TableColumn<Member, String> lastNameCol;
    @FXML
    private TableColumn<Member, String> streetCol;
    @FXML
    private TableColumn<Member, Integer> nrCol;
    @FXML
    private TableColumn<Member, Integer> zipCol;
    @FXML
    private TableColumn<Member, String> cityCol;
    @FXML
    private TableColumn<Member, LocalDate> bDayCol;
    @FXML
    private TableColumn<Member, String> mailCol;
    @FXML
    private TableColumn<Member, Boolean> payedCol;
    @FXML
    private TableColumn<Member, Boolean> analCol;
    private MemberRepo repo;

    @Autowired
    private void setRepo(@Qualifier("memberRepo") MemberRepo repo) {
        this.repo = repo;
    }

    public void initialize() {
        TextFieldsMembers.autoCompletCities(repo.findAll(), city);
    }

    /**
     * Shows a table with the members who live in a by the user specified city
     */
    @FXML
    public void search(){
        if (Validation.validate("Stad", city.getText(), "[a-zA-Z -]+")) {
            ObservableList<Member> memberList = observableArrayList(repo.findByCity(city.getText()));
            if (memberList.isEmpty()) {
                Warning.alert("No Members found!", "Er werden geen leden in " + city.getText() + " gevonden!");
                MenuController.window.close();
                ChangeScene.init("/fxml/home.fxml", "KOKW - Het verleden draait altijd mee!");
            } else {
                ChangeScene.init("/fxml/members/search/tableviewByCity.fxml", "Zoeken op Stad");
                MenuController.window.close();
                MemberTable.init(memberList, table, idCol, firstNameCol, lastNameCol, streetCol, nrCol, zipCol, cityCol, mailCol, bDayCol, payedCol, analCol);

            }
        }
    }
}

