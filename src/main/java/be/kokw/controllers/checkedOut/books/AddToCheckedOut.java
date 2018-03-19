package be.kokw.controllers.checkedOut.books;

import be.kokw.bean.books.Book;
import be.kokw.bean.books.CheckedOut;
import be.kokw.bean.Member;
import be.kokw.bean.Copies;
import be.kokw.controllers.MenuController;
import be.kokw.repositories.books.BookRepo;
import be.kokw.repositories.books.CheckOutRepo;
import be.kokw.repositories.MemberRepo;
import be.kokw.repositories.books.CopyRepo;
import be.kokw.utility.validation.Warning;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class AddToCheckedOut {
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField id;
    private BookRepo bookRepo;
    private MemberRepo memberRepo;
    private CheckOutRepo checkOutRepo;
    private CopyRepo copyRepo;
    private Member member;
    private Book book;

    @Autowired
    private void setBookRepo(@Qualifier("bookRepo") BookRepo repo) {
        bookRepo = repo;
    }

    @Autowired
    private void setMemberRepo(@Qualifier("memberRepo") MemberRepo repo) {
        memberRepo = repo;
    }

    @Autowired
    private void setCheckOutRepo(@Qualifier("checkOutRepo") CheckOutRepo repo) {
        checkOutRepo = repo;
    }

    @Autowired
    private void setCopyRepo(@Qualifier("copyRepo") CopyRepo copyRepo) {
        this.copyRepo = copyRepo;
    }

    public void save() {
        setMember();
        setBook();
        MenuController.window.close();
        if (member != null) {
            if (book != null) {
                Copies copy = copyRepo.findByTitle(book.getTitle());
                System.out.println(copy.getNrOfCopies());
                if (copy.getNrOfCopies() > 0) {
                    copy.setNrOfCopies(copy.getNrOfCopies() - 1);
                    copyRepo.save(copy);
                } else {
                    Warning.alert("No More Copies", "Er zijn geen kopieën van het boek '" + book.getTitle() + "' meer beschikbaar.");

                }
                CheckedOut checkOut = checkOutRepo.save(book, member);
                if (checkOut != null) {
                    StringBuilder sb = new StringBuilder(checkOut.getReturnDate().toString());
                    Warning.alert("Book checked out!", "Het boek '" + book.getTitle() + "' werd succesvol uitgeleend aan '" + firstName.getText() + " " + lastName.getText() + "'!\nDe retourdatum is " + sb.toString());
                } else {
                    Warning.alert("Check out failed", "Er is iets fout gegaan!");
                }
            } else {
                Warning.alert("Book not found!", "Het boek met id: " + id.getText() + " werd niet gevonden!");
            }
        } else {
            Warning.alert("Member not found", "Het lid '" + firstName.getText() + " " + lastName.getText() + "' werd niet gevonden!");
        }

    }

    private void setMember() {
        member = memberRepo.findByFirstNameAndLastName(firstName.getText(), lastName.getText());
    }

    private void setBook() {
        book = bookRepo.findOne(Integer.parseInt(id.getText()));
    }
}
