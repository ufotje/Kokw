package be.kokw.controllers.books.delete;


import be.kokw.bean.Copies;
import be.kokw.bean.books.Book;
import be.kokw.controllers.MenuController;
import be.kokw.repositories.books.BookRepo;
import be.kokw.repositories.books.CopyRepo;
import be.kokw.utility.Warning;
import be.kokw.utility.Validation;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by ufotje on 2/11/2017.
 * Delete Book By TitleClass
 */
@Component
public class DeleteBookByTitle {
    @FXML
    private TextField title;
    private BookRepo bookRepo;
    private CopyRepo copyRepo;

    @Autowired
    private void setBookRepo(@Qualifier("bookRepo") BookRepo repo) {
        bookRepo = repo;
    }

    @Autowired
    private void setCopyRepo(@Qualifier("copyRepo") CopyRepo copyRepo) {
        this.copyRepo = copyRepo;
    }

    @FXML
    private void delete() {
        if (Validation.emptyValidation("Titel", title.getText().isEmpty())) {
            Book book = bookRepo.findOne(Integer.parseInt(title.getText()));
            if (book != null) {
                Copies copy = copyRepo.findByTitle(book.getTitle());
                if (copy.getNrOfCopies() > 0) {
                    copy.setNrOfCopies(copy.getNrOfCopies() - 1);
                }
                bookRepo.delete(book);
                Warning.alert("Book Deleted", "The book " + title.getText() + "has been successful deleted");
                MenuController.window.close();
            } else {
                Warning.alert("Book Not Found", "The book '" + title.getText() + "' has not been found!");
            }
        }
    }
}
