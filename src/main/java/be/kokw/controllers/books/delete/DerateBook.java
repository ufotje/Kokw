package be.kokw.controllers.books.delete;

import be.kokw.bean.Book;
import be.kokw.bean.Derated;
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
        Derated derated = new Derated(LocalDate.now(), destination.getValue(), b.getIsbn(), b.getDepot(), b.getTitle(), b.getAuthors());
        Derated d = derateRepo.save(derated);
        int i = repo.updateDeratedAndDestination(title.getText());
        MenuController.window.close();
        if(i>0 && d != null){
            Warning.alert("Derating succesvol", "Het boek '" + title.getText() + "' werd met succes gedeclasseerd.");
        }else {
            Warning.alert("No item found", "Het boek '" + title.getText() + "' werd niet terug gevonden.");
        }
    }
}