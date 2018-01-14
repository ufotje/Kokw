package be.kokw.controllers.members.search;

import be.kokw.bean.Member;
import be.kokw.controllers.MenuController;
import be.kokw.repositories.MemberRepo;
import be.kokw.utility.ChangeScene;
import be.kokw.utility.Validation;
import be.kokw.utility.Warning;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static javafx.collections.FXCollections.observableArrayList;

/**
 * Created by ufotje on 5/11/2017.
 * View for Searchquery ByFullName
 */

@Component
public class MemberByFullName {
    @FXML
    private TextField firstName, lastName;
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

    @FXML
    public void search() throws Exception {
        if (Validation.validate("Achternaam", lastName.getText(), "[a-zA-Z ]+") &&
                Validation.validate("Voornaam", firstName.getText(), "[a-zA-Z]+")) {
            ObservableList<Member> memberList = observableArrayList(repo.findByFirstNameAndLastName(firstName.getText(), lastName.getText()));
            if (memberList.get(0) == null) {
                Warning.alert("No Members found!", "Het lid " + firstName.getText() + " " + lastName.getText() + " werd niet gevonden!");
                ChangeScene.init("/fxml/home.fxml", "KOKW - Het verleden draait altijd mee!");
                MenuController.window.close();
            } else {
                ChangeScene.init("/fxml/members/search/tableviewByFullName.fxml", "Zoeken op volledige naam");
                MenuController.window.close();
                table.setEditable(true);
                idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
                firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
                lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
                streetCol.setCellValueFactory(new PropertyValueFactory<>("street"));
                nrCol.setCellValueFactory(new PropertyValueFactory<>("houseNr"));
                zipCol.setCellValueFactory(new PropertyValueFactory<>("zip"));
                cityCol.setCellValueFactory(new PropertyValueFactory<>("city"));
                mailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
                bDayCol.setCellValueFactory(new PropertyValueFactory<>("birthday"));
                payedCol.setCellValueFactory(new PropertyValueFactory<>("payed"));
                analCol.setCellValueFactory(new PropertyValueFactory<>("anal"));
                table.setItems(memberList);
            }
        }
    }
}
