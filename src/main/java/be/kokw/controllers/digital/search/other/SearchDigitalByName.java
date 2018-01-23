 package be.kokw.controllers.digital.search.other;

import be.kokw.bean.digital.Digital;
import be.kokw.controllers.MenuController;
import be.kokw.repositories.digital.DigitalRepo;
import be.kokw.utility.ChangeScene;
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


 /**
  * Created by ufotje on 28/10/2017.
  * The search byAuthorsNameControllerClass
  */

 @Component
 public class SearchDigitalByName {
     @FXML
     private TextField firstName, lastName;
     @FXML
     private TableView<Digital> table;
     @FXML
     private TableColumn<Digital, Integer> idCol;
     @FXML
     private TableColumn<Digital, String> depotCol;
     @FXML
     private TableColumn<Digital, Integer> volumeCol;
     @FXML
     private TableColumn<Digital, String> titleCol;
     @FXML
     private TableColumn<Digital, String> topicCol;
     @FXML
     private TableColumn<Digital, String> authorCol;
     @FXML
     private TableColumn<Digital, String> subTitleCol;
     @FXML
     private TableColumn<Digital, String> publisherCol;
     @FXML
     private TableColumn<Digital, String> yearCol;
     private DigitalRepo repo;

     @Autowired
     private void setRepo(@Qualifier("digitalRepo") DigitalRepo repo) {
         this.repo = repo;
     }

     @FXML
     public void search() throws Exception {
         if (Validation.validate("Achternaam Auteur", lastName.getText(), "[a-zA-Z]+") &&
                 Validation.validate("Voornaam Auteur:", firstName.getText(), "[a-zA-Z]+")) {
             ObservableList<Digital> digiList = observableArrayList(repo.findByAuthorsContains(firstName.getText() + " " + lastName.getText()));
             if (digiList.isEmpty()) {
                 Warning.alert("No Books found!", "Er werden geen boeken gevonden geschreven door " + firstName.getText() + " " + lastName.getText());
                 MenuController.window.close();
             } else {

                 MenuController.window.close();
                 ChangeScene.init("/fxml/digital/found/other/tableView.fxml", "Books by Director's name");
                 table.setEditable(true);
                 idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
                 depotCol.setCellValueFactory(new PropertyValueFactory<>("depot"));
                 titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
                 volumeCol.setCellValueFactory(new PropertyValueFactory<>("volume"));
                 topicCol.setCellValueFactory(new PropertyValueFactory<>("topics"));
                 authorCol.setCellValueFactory(new PropertyValueFactory<>("authors"));
                 subTitleCol.setCellValueFactory(new PropertyValueFactory<>("subtitles"));
                 publisherCol.setCellValueFactory(new PropertyValueFactory<>("publisher"));
                 yearCol.setCellValueFactory(new PropertyValueFactory<>("yearPublished"));
                 table.setItems(digiList);
             }
         }
     }
 }
