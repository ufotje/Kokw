package be.kokw.controllers.books.search.donated;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DonateDetails {

    @FXML
    private DialogPane pane;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private DatePicker dateValue;

    public void setFirstName(String firstName){
        this.firstName.setText(firstName);
    }

    public void setLastName(String lastName){
        this.lastName.setText(lastName);
    }

    public void setDateValue(LocalDate date){
        dateValue.setValue(date);
    }

    @FXML
    private void close(){
        Stage window = (Stage)pane.getScene().getWindow();
        window.close();
    }
}
