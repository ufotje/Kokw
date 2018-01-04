package be.kokw.controllers.magazines.search;

import be.kokw.bean.Magazine;
import be.kokw.controllers.MenuController;
import be.kokw.repositories.MagazineRepo;
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
import org.springframework.stereotype.Component;

import static javafx.collections.FXCollections.observableArrayList;

@Component
public class FindMagByTitle {
    @FXML
    private TextField title;
    @FXML
    private TableView<Magazine> table;
    @FXML
    private TableColumn<Magazine,String> id;
    @FXML
    private TableColumn<Magazine,String> issn;
    @FXML
    private TableColumn<Magazine,String> name;
    @FXML
    private TableColumn<Magazine,String> topic;
    @FXML
    private TableColumn<Magazine,String> publisher;
    @FXML
    private TableColumn<Magazine,String> nr;
    @FXML
    private TableColumn<Magazine,String> year;
    @FXML
    private TableColumn<Magazine,String> pages;
    @FXML
    private TableColumn<Magazine,String> period;
    @FXML
    private TableColumn<Magazine,String> copies;
    @FXML
    private TableColumn<Magazine,Boolean> illustrated;
    private MagazineRepo repo;

    @Autowired
    private void setRepo(@Qualifier("magRepo") MagazineRepo repo){
        this.repo = repo;
    }

    @FXML
    public void search(){
        if (Validation.validate("name", title.getText(), "[a-zA-Z \\-]+")) {
            ObservableList<Magazine> list = observableArrayList(repo.findMagazinesByName(title.getText()));
            MenuController.window.close();
            if (list.isEmpty()){
                Warning.alert("No Magazines found!", "Er werden geen magazines gevonden met '" + title.getText() + "' als title gevonden.");
            }else{
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
            }
        }
    }
}