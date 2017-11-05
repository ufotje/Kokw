package be.kokw.controllers;

import be.kokw.controllers.books.delete.DeleteBookByTitle;
import be.kokw.utility.ChangeScene;
import be.kokw.utility.NewStage;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

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
        ChangeScene.init("/fxml/books/addBook.fxml", "addBook");
    }

    //Find a Book
    @FXML
    private void findBookByTitle() throws Exception {
        Stage window = NewStage.getStage("Vind Boek op Titel!","/fxml/books/search/dialogpaneByTitle.fxml");
        window.show();
    }

    @FXML
    private void findBookByAuthor() throws Exception {
        Stage window = NewStage.getStage("Vul de auteurs naam in!", "/fxml/books/search/byName.fxml");
        window.show();
    }

    @FXML
    private void findBookByPublisher() throws Exception {
        Stage window = NewStage.getStage("Vind Boeken op uitgeverij!","/fxml/books/search/dialogpaneByPublisher.fxml");
        window.show();
    }

    //Update a book
    @FXML
    private void updateBookByTitle() throws Exception {
        ChangeScene.init("/fxml/books/update/updateBook.fxml","Boek updaten!");
    }

    //Delete a Book
    @FXML
    private void deleteBookByTitle() throws Exception{
        DeleteBookByTitle b = new DeleteBookByTitle();
        b.init();
    }

    //MemberMethods
    //Add a Member
    @FXML
    private void addMember() throws Exception {
        ChangeScene.init("/fxml/members/addMember.fxml", "Add Member");
    }

    //Find a Member
    @FXML
    private void findMemberByName() throws Exception {
        ChangeScene.init("/fxml/members/search/byFullName.xml","Search by Name");
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
