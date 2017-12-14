package be.kokw.controllers.books.update;

import be.kokw.bean.Book;
import be.kokw.controllers.MenuController;
import be.kokw.repositories.books.BookRepo;
import be.kokw.utility.ChangeScene;
import be.kokw.utility.Validation;
import be.kokw.utility.Warning;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


/**
 * Created by ufotje on 20/10/2017.
 * This class is used to update a book
 */

@Component
public class UpdateBook {
    @FXML
    private TextField title, topic, firstName, lastName, publisher, place, year, pages;
    private BookRepo repo;
    private Book book;

    @Autowired
    private void SetBookRepo(@Qualifier("bookRepo") BookRepo repo) {
        this.repo = repo;
    }

    @FXML
    public void search() throws Exception {
     /*   book = repo.findByTitle(title.getText());
        if (book != null) {
            MenuController.window.close();
            ChangeScene.init("/fxml/books/update/updateBook.fxml", "Boek updaten!");
            title.setText(book.getTitle());
            topic.setText(book.getTopic());
            firstName.setText(book.getAuthorFirstName());
            lastName.setText(book.getAuthorLastName());
            publisher.setText(book.getPublisher());
            place.setText(book.getPlace());
            year.setText("" + book.getYearPublished());
            pages.setText("" + book.getNrOfPages());
        } else {
            Warning.alert("No Book Found!", "Er werd geen boek met " + "'" + title.getText() + "'" + " als titel gevonden!");
        }*/
    }

    @FXML
    public void update() throws Exception {
        if (validated()) {
            int result = repo.update(book.getId(), title.getText(), topic.getText(), firstName.getText(), lastName.getText(), publisher.getText(), place.getText(), Integer.parseInt(year.getText()), Integer.parseInt(pages.getText()));
            if (result > 0) {
                String alert = "The book with title: '" + title.getText() + "' has been successfully updated!";
                Warning.alert("Book updated!", alert);
                ChangeScene.init("/fxml/home.fxml", "KOKW - Het verleden draait door!");
            } else {
                Warning.alert("Update failed", "Corresponderend boek niet gevonden!");
            }
        }
    }

    private boolean validated() {
        boolean valid = false;
        if (Validation.emptyValidation("Titel", title.getText().isEmpty() &&
                Validation.emptyValidation("Topic", topic.getText().isEmpty()) &&
                Validation.validate("Achternaam Auteur", lastName.getText(), "[a-zA-Z -]+") &&
                Validation.validate("Voornaam Auteur:", firstName.getText(), "[a-zA-Z]+")) &&
                Validation.validate("Uitgeverij:", publisher.getText(), "[a-zA-Z -]+") &&
                Validation.emptyValidation("Plaats Uitgeverij", place.getText().isEmpty()) &&
                Validation.validate("Jaar van publicatie:", year.getText(), "[0-9999]+") &&
                Validation.validate("Aantal Bladzijden:", pages.getText(), "[0-9999]+")) {
            valid = true;
        }
        return valid;
    }
}
