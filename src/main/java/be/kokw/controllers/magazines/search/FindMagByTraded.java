package be.kokw.controllers.magazines.search;

import be.kokw.bean.magazines.Magazine;
import be.kokw.repositories.MagazineRepo;
import be.kokw.utility.Warning;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import static javafx.collections.FXCollections.observableArrayList;

@Component
public class FindMagByTraded {
    @FXML
    private TableView<Magazine> table;
    @FXML
    private TableColumn<Magazine, String> id;
    @FXML
    private TableColumn<Magazine, String> issn;
    @FXML
    private TableColumn<Magazine, String> name;
    @FXML
    private TableColumn<Magazine, String> topic;
    @FXML
    private TableColumn<Magazine, String> publisher;
    @FXML
    private TableColumn<Magazine, String> nr;
    @FXML
    private TableColumn<Magazine, String> year;
    @FXML
    private TableColumn<Magazine, String> pages;
    @FXML
    private TableColumn<Magazine, String> period;
    @FXML
    private TableColumn<Magazine, String> copies;
    @FXML
    private TableColumn<Magazine, Boolean> illustrated;
    private MagazineRepo repo;

    @Autowired
    private void setRepo(@Qualifier("magRepo") MagazineRepo repo) {
        this.repo = repo;
    }

    @FXML
    public void initialize() {
        ObservableList<Magazine> list = observableArrayList(repo.findMagazinesByTradedIsTrue());
        if (list.isEmpty()) {
            Warning.alert("No Magazines found!", "Er werden geen magazines gevonden waar er een ruilabonnement is.");
        } else {
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            issn.setCellValueFactory(new PropertyValueFactory<>("issn"));
            name.setCellValueFactory(new PropertyValueFactory<>("name"));
            topic.setCellValueFactory(new PropertyValueFactory<>("theme"));
            publisher.setCellValueFactory(new PropertyValueFactory<>("publisher"));
            nr.setCellValueFactory(new PropertyValueFactory<>("nr"));
            year.setCellValueFactory(new PropertyValueFactory<>("year"));
            pages.setCellValueFactory(new PropertyValueFactory<>("nrOfPages"));
            period.setCellValueFactory(new PropertyValueFactory<>("period"));
            copies.setCellValueFactory(new PropertyValueFactory<>("copies"));
            illustrated.setCellValueFactory(new PropertyValueFactory<>("illustrated"));
            table.setItems(list);
        }
        table.setRowFactory(tv -> {
            TableRow<Magazine> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 2) {
                    Magazine clickedRow = row.getItem();
                }
            });
            return row;
        });
    }
}
