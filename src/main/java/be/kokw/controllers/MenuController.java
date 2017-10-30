package be.kokw.controllers;

import be.kokw.utility.ChangeScene;
import be.kokw.utility.GetControllerBean;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

import static be.kokw.Main.springContext;

/**
 * Created by ufotje on 20/10/2017.
 * This is the MenuControllerClass
 */

@Controller
public class MenuController {

    //BookMethods
    //Add a Book
    @FXML
    private void addBook() throws Exception {
        ChangeScene.init("/fxml/books/addBook.fxml","addBook");
    }
    //Find a Book
    @FXML
    private void findBookByTitle() {
    }

    @FXML
    private void findBookByAuthor()throws Exception {
        Stage window = new Stage();
        Parent root = GetControllerBean.getBean("/fxml/books/search/byName.fxml");
        window.setScene(new Scene(root));
        window.setTitle("Vul de auteurs naam in!");
        window.initModality(Modality.APPLICATION_MODAL);
        window.show();
    }

    @FXML
    private void findBookByPublisher() {
    }

    //Update a book
    @FXML
    private void updateBookByTitle() {
    }

    @FXML
    private void updateBookByAuthor() {
    }

    @FXML
    private void updateBookByPublisher() {
    }

    //Delete a Book
    @FXML
    private void deleteBookByTitle() {
    }

    @FXML
    private void deleteBookByAuthor() {
    }

    @FXML
    private void deleteBookByPublisher() {
    }

    //MemberMethods
    //Add a Member
    @FXML
    private void addMember() throws Exception {
        ChangeScene.init("/fxml/members/addMember.fxml","addMember");
    }

    //Find a Member
    @FXML
    private void findMemberByName() {
    }

    @FXML
    private void findMemberByCity() {
    }

    @FXML
    private void findMemberByDayOfBirth() {
    }

    @FXML
    private void findMemberByPayed() {
    }

    @FXML
    private void findMemberByAnalen() {
    }

    //Update Member
    @FXML
    private void updateMember() {
    }

    //Delete Member
    @FXML
    private void deleteMember() {
    }
}
