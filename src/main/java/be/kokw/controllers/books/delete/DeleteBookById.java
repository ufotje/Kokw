package be.kokw.controllers.books.delete;


import be.kokw.bean.Copies;
import be.kokw.bean.books.Book;
import be.kokw.controllers.MenuController;
import be.kokw.repositories.books.BookRepo;
import be.kokw.repositories.books.CopyRepo;
import be.kokw.repositories.books.GiftedForRepo;
import be.kokw.repositories.books.GiftedRepo;
import be.kokw.utility.sceneControl.ChangeScene;
import be.kokw.utility.validation.Warning;
import be.kokw.utility.validation.Validation;
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
public class DeleteBookById {
    @FXML
    private TextField id;
    private BookRepo bookRepo;
    private CopyRepo copyRepo;
    private GiftedRepo giftedRepo;
    private GiftedForRepo giftedForRepo;

    @Autowired
    private void setBookRepo(@Qualifier("bookRepo") BookRepo repo) {
        bookRepo = repo;
    }

    @Autowired
    private void setCopyRepo(@Qualifier("copyRepo") CopyRepo copyRepo) {
        this.copyRepo = copyRepo;
    }

    @Autowired
    private void setGiftedRepo(@Qualifier("giftedRepo") GiftedRepo giftedRepo) {
        this.giftedRepo = giftedRepo;
    }

    @Autowired
    private void setGiftedForRepo(@Qualifier("giftedForRepo") GiftedForRepo giftedForRepo) {
        this.giftedForRepo = giftedForRepo;
    }

    @FXML
    private void delete() {
        if (Validation.emptyValidation("Id", id.getText().isEmpty())) {
            Book book = bookRepo.findOne(Integer.parseInt(id.getText()));
            if (book != null) {
                if (book.isGifted()) {
                    giftedRepo.deleteByBookId(book.getId());
                }
                if (book.isGiftedFor()) {
                    giftedForRepo.deleteByBookId(book.getId());
                }
                Copies copy = copyRepo.findByTitle(book.getTitle());
                if (copy.getNrOfCopies() > 0) {
                    copy.setNrOfCopies(copy.getNrOfCopies() - 1);
                } else {
                    copyRepo.delete(copy);
                }
                bookRepo.delete(book);
                Warning.alert("Book Deleted", "The book " + book.getTitle() + "has been successful deleted");
                MenuController.window.close();
            } else {
                Warning.alert("Book Not Found", "The book wit id '" + id.getText() + "' has not been found!");
                ChangeScene.init("/fxml/home.fxml", "KOKW - Het verleden draait altijd mee!");
            }
        }
    }
}
