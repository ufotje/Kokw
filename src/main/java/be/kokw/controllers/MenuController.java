package be.kokw.controllers;



import be.kokw.controllers.books.AddBook;
import be.kokw.repositories.books.interfaces.BookRepo;
import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

/**
 * Created by ufotje on 20/10/2017.
 * This is the MenuControllerClass
 */
@Controller
public class MenuController {
    private BookRepo repo;

    @Autowired
    public void setRepo(@Qualifier("bookRepo") BookRepo repo) {
        this.repo = repo;
    }

    @FXML
    private void addBook() {
        AddBook addBook = new AddBook(repo);
    }
}
