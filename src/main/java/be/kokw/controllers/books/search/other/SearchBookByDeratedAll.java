package be.kokw.controllers.books.search.other;

import be.kokw.bean.books.Derated;
import be.kokw.repositories.books.DerateRepo;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static javafx.collections.FXCollections.observableArrayList;

@Component
public class SearchBookByDeratedAll {
    @FXML
    TableView<Derated> table;
    @FXML
    TableColumn<Derated, Integer> idCol;
    @FXML
    TableColumn<Derated, String> isbnCol;
    @FXML
    TableColumn<Derated, Integer> bookIdCol;
    @FXML
    TableColumn<Derated, String> depotCol;
    @FXML
    TableColumn<Derated, String> authorCol;
    @FXML
    TableColumn<Derated, String> titleCol;
    @FXML
    TableColumn<Derated, String> destinationCol;
    @FXML
    TableColumn<Derated, LocalDate> deratedCol;
    private DerateRepo repo;

    @Autowired
    private void setRepo(@Qualifier("derateRepo") DerateRepo repo){
        this.repo = repo;
    }

    @FXML
    public void initialize(){
        ObservableList<Derated> list = observableArrayList(repo.findAll());
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        isbnCol.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        depotCol.setCellValueFactory(new PropertyValueFactory<>("depot"));
        bookIdCol.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        destinationCol.setCellValueFactory(new PropertyValueFactory<>("destination"));
        deratedCol.setCellValueFactory(new PropertyValueFactory<>("derated"));
        table.setEditable(true);
        table.setItems(list);
    }
}
