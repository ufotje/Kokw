package be.kokw.controllers.books;

import be.kokw.Main;
import be.kokw.bean.Book;
import be.kokw.repositories.books.interfaces.BookRepo;
import be.kokw.utility.ChangeScene;
import be.kokw.utility.GetControllerBean;
import be.kokw.utility.Validation;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by ufotje on 20/10/2017.
 * This class is used to save a book
 */

@Component
public class AddBook{
    @FXML
    private TextField title, topic, firstName, lastName, publisher, place, year, pages;
    private BookRepo repo;

    public AddBook() throws Exception{

    }

    @Autowired
    public void setBookRepo(@Qualifier("bookRepo") BookRepo bookRepo) {
        repo = bookRepo;
    }

    @FXML
    public void save() throws Exception {
        Book book = new Book(title.getText(), topic.getText(), firstName.getText(), lastName.getText(), publisher.getText(), place.getText(), Integer.parseInt(year.getText()), Integer.parseInt(pages.getText()));
        Book newBook = repo.save(book);
        saveAlert(newBook);
        ChangeScene.init("/fxml/sample.fxml","KOKW-AdminApp");
    }

    @FXML
    public void addMore(){}

    @FXML
    private void saveAlert(Book book) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Book saved successfully.");
        alert.setHeaderText(null);
        alert.setContentText("The book " + book.getTitle() + " has been created");
        alert.showAndWait();
    }
}
