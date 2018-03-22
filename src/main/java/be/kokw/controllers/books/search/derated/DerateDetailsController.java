package be.kokw.controllers.books.search.derated;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DerateDetailsController {

    @FXML
    private Pane pane;
    @FXML
    private TextField id;
    @FXML
    private TextField destination;
    @FXML
    private DatePicker date;

    public void setId(int id){
        this.id.setText("" + id);
    }

    public void setDestination(String destination){
        this.destination.setText(destination);
    }

    public void setDate(LocalDate date){
        this.date.setValue(date);
    }

    @FXML
    private void close(){
        Stage window = (Stage)pane.getScene().getWindow();
        window.close();
    }
}
