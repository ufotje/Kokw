package be.kokw.controllers.books.search.derated;

import be.kokw.bean.books.Derated;
import be.kokw.repositories.books.DerateRepo;
import be.kokw.utility.sceneControl.ChangeScene;
import be.kokw.utility.validation.Warning;
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
public class SearchBookByDeratedSold {
    @FXML
    private TableView<Derated> table;
    @FXML
    private TableColumn<Derated, Integer> idCol;
    @FXML
    private TableColumn<Derated, String> isbnCol;
    @FXML
    private TableColumn<Derated, String> depotCol;
    @FXML
    private TableColumn<Derated, Integer> bookIdCol;
    @FXML
    private TableColumn<Derated, String> titleCol;
    @FXML
    private TableColumn<Derated, String> authorCol;
    @FXML
    private TableColumn<Derated, LocalDate> deratedCol;
    @FXML
    private TableColumn<Derated, String> destinationCol;
    private DerateRepo repo;

    @Autowired
    private void setRepo(@Qualifier("derateRepo") DerateRepo repo) {
        this.repo = repo;
    }

    @FXML
    public void initialize() {
        ObservableList<Derated> list = observableArrayList(repo.findByDestination("verkocht"));
        if (list.isEmpty()) {
            Warning.alert("No Items Found!", "Er werden geen verkochte boeken gevonden.");
            ChangeScene.init("/fxml/home.fxml", "KOKW - Het Verleden Draait Altijd Mee!");
        } else {
            idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            isbnCol.setCellValueFactory(new PropertyValueFactory<>("isbn"));
            depotCol.setCellValueFactory(new PropertyValueFactory<>("depot"));
            bookIdCol.setCellValueFactory(new PropertyValueFactory<>("bookId"));
            titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
            authorCol.setCellValueFactory(new PropertyValueFactory<>("authors"));
            deratedCol.setCellValueFactory(new PropertyValueFactory<>("derated"));
            destinationCol.setCellValueFactory(new PropertyValueFactory<>("destination"));
            table.setItems(list);
        }
    }
}
