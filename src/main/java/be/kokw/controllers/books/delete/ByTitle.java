package be.kokw.controllers.books.delete;


import be.kokw.repositories.BookRepo;
import be.kokw.utility.ChangeScene;
import be.kokw.utility.DeleteAlert;
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
 */
@Component
public class ByTitle {
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
        window.showAndWait();

    }
    @FXML
    private void delete(){
        if(Validation.emptyValidation("Titel",title.getText().isEmpty())){
            bookRepo.deleteByTitle(title.getText());
            window.close();
            DeleteAlert.deleteAlert("Book",title.getText());
        }
    }
}
