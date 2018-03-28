package be.kokw.controllers.checkedOut;

import be.kokw.bean.CheckedOut;
import be.kokw.controllers.MenuController;
import be.kokw.repositories.CheckOutRepo;
import be.kokw.utility.sceneControl.ChangeScene;
import be.kokw.utility.validation.Warning;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static javafx.collections.FXCollections.observableArrayList;

/**
 * Created By Demesmaecker Daniel
 */

@Component
public class CheckOutDateBetween {
    @FXML
    private DatePicker startDate;
    @FXML
    private DatePicker endDate;
    @FXML
    private TableView<CheckedOut> table;
    @FXML
    private TableColumn<CheckedOut,Integer> idCol;
    @FXML
    private TableColumn<CheckedOut,String> titleCol;
    @FXML
    private TableColumn<CheckedOut,LocalDate> startCol;
    @FXML
    private TableColumn<CheckedOut,LocalDate> endCol;
    @FXML
    private TableColumn<CheckedOut,String> typeCol ;
    @FXML
    private TableColumn<CheckedOut,String> nameCol;
    @FXML
    private TableColumn<CheckedOut,String>addressCol;

    @FXML
    private TableColumn<CheckedOut,String> mailCol;
    private CheckOutRepo repo;

    @Autowired
    private void setRepo(@Qualifier("checkOutRepo") CheckOutRepo repo){
        this.repo = repo;
    }

    /**
     * Opens a table with all Objects checked out between two by the user defined dates
     */
    @FXML
    public void search(){
        if(startDate.getValue() != null){
            if(endDate.getValue() != null){
                ObservableList<CheckedOut> dates = observableArrayList(repo.findByCheckOutDateBetween(startDate.getValue(), endDate.getValue()));
                if(!dates.isEmpty()){
                    MenuController.window.close();
                    ChangeScene.init("/fxml/checkOut/tableViewDatesBetween.fxml", "Objects by Dates between");
                    table.setEditable(true);
                    idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
                    titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
                    typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
                    startCol.setCellValueFactory(new PropertyValueFactory<>("checkOutDate"));
                    endCol.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
                    nameCol.setCellValueFactory(new PropertyValueFactory<>("fullName"));
                    addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
                    mailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
                    table.setItems(dates);
                }else{
                    Warning.alert("No Results", "Er werden geen boeken gevonden die werden uitgeleend tussen " + startDate.getValue() + " en " + endDate.getValue());
                }
            }else{
                Warning.alert("No enddate selected", "Selecteer een einddatum aub!");
            }
        }else{
            Warning.alert("No Startdate Selected", "Selecteer aub een startdatum!");
        }
    }
}
