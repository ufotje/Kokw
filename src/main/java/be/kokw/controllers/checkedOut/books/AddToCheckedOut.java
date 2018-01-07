package be.kokw.controllers.checkedOut.books;

import be.kokw.bean.books.Book;
import be.kokw.bean.books.CheckedOut;
import be.kokw.bean.Member;
import be.kokw.controllers.MenuController;
import be.kokw.repositories.books.BookRepo;
import be.kokw.repositories.books.CheckOutRepo;
import be.kokw.repositories.MemberRepo;
import be.kokw.utility.Warning;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class AddToCheckedOut {
    @FXML
    private TextField title;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    private BookRepo bookRepo;
    private MemberRepo memberRepo;
    private CheckOutRepo checkOutRepo;
    private Member member;
    private Book book;

    @Autowired
    private void setBookRepo(@Qualifier("bookRepo") BookRepo repo){
        bookRepo = repo;
    }

    @Autowired
    private void setMemberRepo(@Qualifier("memberRepo") MemberRepo repo){
        memberRepo = repo;
    }

    @Autowired
    private void setCheckOutRepo(@Qualifier("checkOutRepo") CheckOutRepo repo){
        checkOutRepo = repo;
    }

    public void save(){
        setMember();
        setBook();
        MenuController.window.close();
        if(member != null){
            if(book != null){
                CheckedOut checkOut = checkOutRepo.save(book, member);
                if(checkOut != null){
                    StringBuilder sb = new StringBuilder(checkOut.getReturnDate().toString());
                    Warning.alert("Book checked out!", "Het boek '" + title.getText() + "' werd succesvol uitgeleend aan '" + firstName.getText() + " " + lastName.getText() + "'!\nDe retourdatum is " + sb.reverse().toString());
                }else {
                    Warning.alert("Check out failed", "Er is iets fout gegaan!");
                }
            }else{
                Warning.alert("Book not found!", "Het boek '" + title.getText() + "' werd niet gevonden!");
            }
        }else{
            Warning.alert("Member not found", "Het lid '" + firstName.getText() + " " + lastName.getText() + "' werd niet gevonden!");
        }

    }

    private void setMember() {
        member = memberRepo.findByFirstNameAndLastName(firstName.getText(), lastName.getText());
    }

    private void setBook() {
        book = bookRepo.findByTitle(title.getText());
    }
}
