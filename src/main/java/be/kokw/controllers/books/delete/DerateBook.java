package be.kokw.controllers.books.delete;

import be.kokw.bean.books.Book;
import be.kokw.bean.books.Derated;
import be.kokw.controllers.MenuController;
import be.kokw.repositories.books.BookRepo;
import be.kokw.repositories.books.DerateRepo;
import be.kokw.utility.Warning;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DerateBook {
    @FXML
    TextField title;
    @FXML
    ChoiceBox<String> destination;
    private BookRepo repo;
    private DerateRepo derateRepo;

    @Autowired
    private void setBookRepo(@Qualifier("bookRepo") BookRepo repo) {
        this.repo = repo;
    }

    @Autowired
    private void setDerateRepo(@Qualifier("derateRepo") DerateRepo derateRepo){
        this.derateRepo = derateRepo;
    }

    public void initialize() {
        ObservableList<String> list = FXCollections.observableArrayList("Vernietigd", "Verkocht", "Weggeschonken");
        destination.setItems(list);
        destination.setValue("Vernietigd");
    }
    @FXML
    private void derate(){
        Book b = repo.findByTitle(title.getText());
        Derated derated = new Derated(b.getId(),LocalDate.now(), destination.getValue(), b.getIsbn(), b.getDepot(), b.getTitle(), b.getAuthors());
        Derated d = derateRepo.save(derated);
        Book book = null;
        if(b.getCopies() > 1){
            b.setCopies(b.getCopies()-1);
            book = repo.save(b);
        }else{
            repo.delete(b);
        }
        MenuController.window.close();
        if( d != null){
            StringBuilder sb = new StringBuilder();
            if(book != null){
                sb.append("Er zijn nog ").append(book.getCopies()).append(" kopieën van het boek '").append(book.getTitle()).append("' in de bibliotheek van de kokw.");
            }else{
                sb.append("Er zijn geen kopieën meer van het boek '").append(title.getText()).append("' in de bibliotheek van de kokw");
            }
            Warning.alert("Derating succesvol", "Het boek '" + title.getText() + "' werd met succes gedeclasseerd.\n" + sb.toString());
        }else {
            Warning.alert("No item found", "Het boek '" + title.getText() + "' werd niet terug gevonden.");
        }

    }
}