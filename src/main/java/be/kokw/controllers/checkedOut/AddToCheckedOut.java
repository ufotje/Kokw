package be.kokw.controllers.checkedOut;

import be.kokw.bean.books.Book;
import be.kokw.bean.CheckedOut;
import be.kokw.bean.Member;
import be.kokw.bean.Copies;
import be.kokw.bean.digital.Digital;
import be.kokw.controllers.MenuController;
import be.kokw.repositories.books.BookRepo;
import be.kokw.repositories.CheckOutRepo;
import be.kokw.repositories.MemberRepo;
import be.kokw.repositories.books.CopyRepo;
import be.kokw.repositories.digital.DigitalRepo;
import be.kokw.utility.validation.Warning;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import static javafx.collections.FXCollections.observableArrayList;

/**
 * Created By Demesmaecker Daniel
 */

@Component
public class AddToCheckedOut {
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField id;
    @FXML
    private ChoiceBox<String> type;
    private BookRepo bookRepo;
    private MemberRepo memberRepo;
    private DigitalRepo digitalRepo;
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
    private void setDigitalRepo(@Qualifier("digitalRepo") DigitalRepo digiRepo) {
        digitalRepo = digiRepo;
    }

    @Autowired
    private void setCheckOutRepo(@Qualifier("checkOutRepo") CheckOutRepo repo) {
        checkOutRepo = repo;
    }

    @Autowired
    private void setCopyRepo(@Qualifier("copyRepo") CopyRepo copyRepo) {
        this.copyRepo = copyRepo;
    }

    @FXML
    public void initialize(){
        ObservableList<String> items = observableArrayList("Boek", "Digitale Drager");
        type.setItems(items);
    }

    /**
     * checks the objects type and created the checkedOutObject
     */
    public void save() {
        setMember();
        MenuController.window.close();
        if (member != null) {
            if (type.getValue().equalsIgnoreCase("Boek")) {
                setBook();
                if (book != null) {
                    setRemainingCopies(copyRepo.findByTitleAndType(book.getTitle(), "boek"));
                    alertMessage(checkOutRepo.save(new CheckedOut(book, member)));
                } else {
                    Warning.alert("Book not found!", "Het boek met id: " + id.getText() + " werd niet gevonden!");
                }
            } else if (type.getValue().equalsIgnoreCase( "Digitale Dragers")) {
                Digital digital = digitalRepo.findOne((Integer.parseInt(id.getText())));
                if (digital != null) {
                    setRemainingCopies(copyRepo.findByTitleAndType(digital.getTitle(), "Digitale Drager"));
                    alertMessage(checkOutRepo.save(new CheckedOut(digital, member)));
                } else {
                    Warning.alert("Digital Carrier not found!", "De digitale drager met id: " + id.getText() + " werd niet gevonden!");
                }
            } else {
                Warning.alert("Error!", "Wrong Type selected.");
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

    /**
     * Checks if there are any copies left of the to checkout object
     * Decrements the available copies or warns the user if there aren't any left
     * @param copy Copy
     */
    private void setRemainingCopies(Copies copy) {
        if (copy.getNrOfCopies() > 0) {
            copy.setNrOfCopies(copy.getNrOfCopies() - 1);
            copyRepo.save(copy);
        } else {
            Warning.alert("No More Copies", "Er zijn geen kopieën van het/de " + type.getValue() + ": " + copy.getTitle() + " meer beschikbaar.");

        }
    }

    /**
     * Prompts the user with a resultmessage
     * @param checkOut CheckedOut
     */
    private void alertMessage(CheckedOut checkOut) {
        if (checkOut != null) {
            Warning.alert("Item checked out!", "Het/de " + type.getValue() + ": " + checkOut.getTitle() +
                    "' werd succesvol uitgeleend aan '" + firstName.getText() + " " + lastName.getText() + "'!\nDe retourdatum is " + checkOut.getReturnDate().toString());
        } else {
            Warning.alert("Check out failed", "Er is iets fout gegaan!");
        }
    }
}
