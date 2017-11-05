package be.kokw.controllers.books.update;

import be.kokw.repositories.BookRepo;
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

    @Autowired
    private void SetBookRepo(@Qualifier("bookRepo") BookRepo repo) {
        this.repo = repo;
    }

    public UpdateBook() {

    }

    @FXML
    public void update() throws Exception {
        if (validated()) {
            int result = repo.update(title.getText(), topic.getText(), firstName.getText(), lastName.getText(), publisher.getText(), place.getText(), Integer.parseInt(year.getText()), Integer.parseInt(pages.getText()));
            if (result > 0) {
                String alert = "The book with title: '" + title.getText() + "' has been successfully updated!";
                Warning.alert("Book updated!", alert);
                clear();
            }else{
                Warning.alert("Update failed", "Corresponderend boek niet gevonden!");
            }
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
    private void clear(){
        this.title.setText(null);
        title.setPromptText(title.getPromptText());
        topic.setText(null);
        topic.setPromptText(topic.getPromptText());
        firstName.setText(null);
        firstName.setPromptText(firstName.getPromptText());
        lastName.setText(null);
        lastName.setPromptText(lastName.getPromptText());
        publisher.setText(null);
        publisher.setPromptText(publisher.getPromptText());
        place.setText(null);
        place.setPromptText(place.getPromptText());
        year.setText(null);
        year.setPromptText(year.getPromptText());
        pages.setText(null);
        pages.setText(pages.getPromptText());
    }
}
