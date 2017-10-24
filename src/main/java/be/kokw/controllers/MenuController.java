package be.kokw.controllers;


import be.kokw.controllers.books.AddBook;
import be.kokw.controllers.members.AddMember;
import be.kokw.repositories.books.interfaces.BookRepo;
import be.kokw.repositories.members.MemberRepo;

import javafx.fxml.FXML;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

/**
 * Created by ufotje on 20/10/2017.
 * This is the MenuControllerClass
 */
@Controller
@Qualifier
public class MenuController {
    private BookRepo bookRepo;
    private MemberRepo memberRepo;
    private AddBook addBook;

    @Autowired
    public void setBookRepo(@Qualifier("bookRepo") BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @Autowired
    public void setMemberRepo(@Qualifier("memberRepo") MemberRepo memberRepo) {
        this.memberRepo = memberRepo;
    }

    //BookMethods
    //Add a Book
    @FXML
    private void addBook() throws Exception{
       addBook = new AddBook(bookRepo);
    }

    //Find a Book
    @FXML
    private void findBookByTitle(){}
    @FXML
    private void findBookByAuthor(){}
    @FXML
    private void findBookByPublisher(){}

    //Update a book
    @FXML
    private void updateBookByTitle(){}
    @FXML
    private void updateBookByAuthor(){}
    @FXML
    private void updateBookByPublisher(){}

    //Delete a Book
    @FXML
    private void deleteBookByTitle(){}
    @FXML
    private void deleteBookByAuthor(){}
    @FXML
    private void deleteBookByPublisher(){}

    //MemberMethods
    //Add a Member
    @FXML
    private void addMember()throws Exception{
        AddMember member = new AddMember(memberRepo);
    }

    //Find a Member
    @FXML
    private void findMemberByName(){}
    @FXML
    private void findMemberByCity(){}
    @FXML
    private void findMemberByDayOfBirth(){}
    @FXML
    private void findMemberByPayed(){}
    @FXML
    private void findMemberByAnalen(){}

    //Update Member
    @FXML
    private void updateMember(){}

    //Delete Member
    @FXML
    private void deleteMember(){}
}
