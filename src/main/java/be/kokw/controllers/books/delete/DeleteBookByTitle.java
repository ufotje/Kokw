package be.kokw.controllers.books.delete;


import be.kokw.repositories.BookRepo;
import be.kokw.utility.Warning;
import be.kokw.utility.NewStage;
import be.kokw.utility.Validation;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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
    private Stage window;

    @Autowired
    private void SetBookRepo(@Qualifier("bookRepo") BookRepo repo) {
        bookRepo = repo;
    }

    @FXML
    public void init()throws Exception{
        window = NewStage.getStage("Verwijder Boek op Titel!","/fxml/books/delete/dialogpaneByTitle.fxml");
        window.show();
    }

    @FXML
    private void delete(){
        if(Validation.emptyValidation("Titel",title.getText().isEmpty())){
            if(bookRepo.deleteByTitle(title.getText())>0) {
                Warning.alert("Book Deleted", "The book " + title.getText() + "has been successful deleted");
                window.getScene().getWindow().hide();
            }else{
                Warning.alert("Book Not Found","The book '" + title.getText() + "' has not been found!");
            }
        }
    }
}
