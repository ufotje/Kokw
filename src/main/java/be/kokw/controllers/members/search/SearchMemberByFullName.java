package be.kokw.controllers.members.search;

import be.kokw.bean.Book;
import be.kokw.bean.Member;
import be.kokw.repositories.MemberRepo;
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

import java.time.LocalDate;

import static javafx.collections.FXCollections.observableArrayList;

/**
 * Created by ufotje on 5/11/2017.
 */
public class SearchMemberByFullName {
    @FXML
    private TextField firstName, lastName;
    @FXML
    private TableView<Member> table;
    @FXML
    private TableColumn<Member, Integer> idCol;
    @FXML
    private TableColumn<Member,String> firstNameCol;
    @FXML
    private TableColumn<Member,String> lastNameCol;
    @FXML
    private TableColumn<Member,String> streetCol;
    @FXML
    private TableColumn<Member,Integer> nrCol;
    @FXML
    private TableColumn<Member,Integer> zipCol;
    @FXML
    private TableColumn<Member,String> cityCol;
    @FXML
    private TableColumn<Member,LocalDate> bDayCol;
    @FXML
    private TableColumn<Member,String> mailCol;
    @FXML
    private TableColumn<Member,Boolean> analCol;
    private MemberRepo repo;

    @Autowired
    private void setRepo(@Qualifier("memberRepo") MemberRepo repo){
        this.repo = repo;
    }

    @FXML
    private void search(){
        if (Validation.validate("Achternaam", lastName.getText(), "[a-zA-Z]+") &&
                Validation.validate("Voornaam", firstName.getText(), "[a-zA-Z]+")) {
            ObservableList<Member> memberList = observableArrayList(repo.findByFirstNameAndLastName(firstName.getText(), lastName.getText()));
            if (memberList.isEmpty()) {
                Warning.alert("No Books found!", "Er werden geen boeken gevonden geschreven door " + firstName.getText() + " " + lastName.getText());
            } else {
                idCol.setCellValueFactory(new PropertyValueFactory("id"));
                firstNameCol.setCellValueFactory(new PropertyValueFactory("firstName"));
                lastNameCol.setCellValueFactory(new PropertyValueFactory("lastName"));
                streetCol.setCellValueFactory(new PropertyValueFactory("street"));
                nrCol.setCellValueFactory(new PropertyValueFactory("houseNr"));
                zipCol.setCellValueFactory(new PropertyValueFactory("zip"));
                cityCol.setCellValueFactory(new PropertyValueFactory("city"));
                mailCol.setCellValueFactory(new PropertyValueFactory("email"));
                bDayCol.setCellValueFactory(new PropertyValueFactory("bDay"));
                analCol.setCellValueFactory(new PropertyValueFactory("anal"));
                table.setItems(memberList);
            }
        }
    }
}
