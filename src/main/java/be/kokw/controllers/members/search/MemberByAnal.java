package be.kokw.controllers.members.search;

import be.kokw.bean.Member;
import be.kokw.repositories.MemberRepo;
import be.kokw.utility.controller.tables.MemberTable;
import be.kokw.utility.validation.Warning;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static javafx.collections.FXCollections.observableArrayList;

/**
 * Created by ufotje on 5/11/2017.
 * View for Searchquery ByAnalNotPayed
 */

@Component
public class MemberByAnal {
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
        ObservableList<Member> memberList = observableArrayList(repo.findByAnalIsFalse());
        if (memberList.isEmpty()) {
            Warning.alert("No Members found!", "Er werden geen leden gevonden die hun Annalen nog niet ontvangen hebben!");
        } else {
            MemberTable.init(memberList, table, idCol, firstNameCol, lastNameCol, streetCol, nrCol, zipCol, cityCol, mailCol, bDayCol, payedCol, analCol);
        }
    }
}
