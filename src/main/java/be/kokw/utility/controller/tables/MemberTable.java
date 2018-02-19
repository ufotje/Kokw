package be.kokw.utility.controller.tables;

import be.kokw.bean.Member;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public interface MemberTable {

    static void init(ObservableList<Member> memberList, TableView<Member> table, TableColumn<Member, Integer> idCol,
                     TableColumn<Member, String> firstNameCol, TableColumn<Member, String> lastNameCol,
                     TableColumn<Member, String> streetCol, TableColumn<Member, Integer> nrCol,
                     TableColumn<Member, Integer> zipCol, TableColumn<Member, String> cityCol,
                     TableColumn<Member, String> mailCol, TableColumn<Member, LocalDate> bDayCol,
                     TableColumn<Member, Boolean> payedCol, TableColumn<Member, Boolean> analCol){
        table.setEditable(true);
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        streetCol.setCellValueFactory(new PropertyValueFactory<>("street"));
        nrCol.setCellValueFactory(new PropertyValueFactory<>("houseNr"));
        zipCol.setCellValueFactory(new PropertyValueFactory<>("zip"));
        cityCol.setCellValueFactory(new PropertyValueFactory<>("city"));
        mailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        bDayCol.setCellValueFactory(new PropertyValueFactory<>("bDay"));
        payedCol.setCellValueFactory(new PropertyValueFactory<>("payed"));
        analCol.setCellValueFactory(new PropertyValueFactory<>("anal"));
        table.setItems(memberList);
    }
}
