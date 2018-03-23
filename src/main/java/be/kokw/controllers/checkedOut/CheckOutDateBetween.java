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
    private TableColumn<CheckedOut,String> topicCol;
    @FXML
    private TableColumn<CheckedOut,String> firstNameCol;
    @FXML
    private TableColumn<CheckedOut,String> lastNameCol ;
    @FXML
    private TableColumn<CheckedOut,String> publisherCol;
    @FXML
    private TableColumn<CheckedOut,String>yearCol;
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
                    topicCol.setCellValueFactory(new PropertyValueFactory<>("topic"));
                    firstNameCol.setCellValueFactory(new PropertyValueFactory<>("authorFirstName"));
                    lastNameCol.setCellValueFactory(new PropertyValueFactory<>("authorLastName"));
                    publisherCol.setCellValueFactory(new PropertyValueFactory<>("publisher"));
                    yearCol.setCellValueFactory(new PropertyValueFactory<>("yearPublished"));
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
