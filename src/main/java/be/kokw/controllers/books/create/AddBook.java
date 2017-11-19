package be.kokw.controllers.books.create;

import be.kokw.bean.Book;
import be.kokw.repositories.BookRepo;
import be.kokw.utility.Warning;
import be.kokw.utility.ChangeScene;
import be.kokw.utility.Validation;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ufotje on 20/10/2017.
 * This class is used to save a book
 */

@Component
public class AddBook{
    @FXML
    private TextField title, topic, firstName, lastName, publisher, place, year, pages;
    private BookRepo repo;
    private List <String> bookList = new ArrayList();

    @Autowired
    private void SetBookRepo(@Qualifier("bookRepo")BookRepo repo){
        this.repo = repo;
    }

    public AddBook() {

    }

    @FXML
    public void save() throws Exception {
        if (validated()) {
            Book book = new Book(title.getText(), topic.getText(), firstName.getText(), lastName.getText(), publisher.getText(), place.getText(), Integer.parseInt(year.getText()), Integer.parseInt(pages.getText()));
            repo.save(book);
            bookList.add(book.getTitle());
            String alert = "The book(s) with title: " ;
            for(String s : bookList){
                alert += "'" +
                        s + "', ";
            }
            alert += " has been successfully saved!";
            Warning.alert("Book saved!", alert);
            ChangeScene.init("/fxml/menu.fxml", "KOKW-AdminApp");
        }
    }

    @FXML
    public void addMore() {
        if (validated()) {
            Book book = new Book(title.getText(), topic.getText(), firstName.getText(), lastName.getText(), publisher.getText(), place.getText(), Integer.parseInt(year.getText()), Integer.parseInt(pages.getText()));
            repo.save(book);
            bookList.add(book.getTitle());
            clearFields();
        }
    }

    private boolean validated() {
        boolean valid = false;
        if (Validation.emptyValidation("Titel", title.getText().isEmpty() &&
                Validation.emptyValidation("Topic", topic.getText().isEmpty()) &&
                Validation.validate("Achternaam Auteur", lastName.getText(), "[a-zA-Z]+") &&
                Validation.validate("Voornaam Auteur:", firstName.getText(), "[a-zA-Z]+")) &&
                Validation.validate("Uitgeverij:", publisher.getText(), "[a-zA-Z]+") &&
                Validation.emptyValidation("Plaats Uitgeverij", place.getText().isEmpty()) &&
                Validation.validate("Jaar van publicatie:", year.getText(), "[0-9999]+") &&
                Validation.validate("Aantal Bladzijden:", pages.getText(), "[0-9999]+")) {
            valid = true;
        }
        return valid;
    }

    private void clearFields(){
        title.clear();
        topic.clear();
        firstName.clear();
        lastName.clear();
        publisher.clear();
        place.clear();
        year.clear();
        pages.clear();
    }
}
