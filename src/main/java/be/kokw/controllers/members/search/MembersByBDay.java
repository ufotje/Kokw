package be.kokw.controllers.members.search;

import be.kokw.bean.Member;
import be.kokw.controllers.MenuController;
import be.kokw.repositories.MemberRepo;
import be.kokw.utility.controller.tables.MemberTable;
import be.kokw.utility.sceneControl.ChangeScene;
import be.kokw.utility.validation.Warning;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static javafx.collections.FXCollections.observableArrayList;

@Component
public class MembersByBDay {
    @FXML
    private DatePicker bDay;
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
        ObservableList<Member> memberList = observableArrayList(repo.findByBDay(bDay.getValue()));
        if (memberList.isEmpty()) {
            Warning.alert("No Members found!", "Er werden geen leden geboren op " + bDay.getValue() + " gevonden!");
            MenuController.window.close();
            ChangeScene.init("/fxml/home.fxml", "KOKW - Het verleden draait altijd mee!");
        } else {
            ChangeScene.init("/fxml/members/search/tableviewByBDay.fxml", "Zoeken op GeboorteDatum");
            MenuController.window.close();
            MemberTable.init(memberList, table, idCol, firstNameCol, lastNameCol, streetCol, nrCol, zipCol, cityCol, mailCol, bDayCol, payedCol, analCol);

        }
    }
}

