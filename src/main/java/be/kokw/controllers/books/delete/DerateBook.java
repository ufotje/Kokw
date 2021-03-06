package be.kokw.controllers.books.delete;

import be.kokw.bean.Copies;
import be.kokw.bean.books.Book;
import be.kokw.bean.Derated;

import be.kokw.controllers.MenuController;
import be.kokw.repositories.DerateRepo;
import be.kokw.repositories.books.*;
import be.kokw.utility.sceneControl.ChangeScene;
import be.kokw.utility.validation.Warning;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Created by Demesmaecker Daniel
 */

@Component
public class DerateBook {
    @FXML
    TextField id;
    @FXML
    ChoiceBox<String> destination;
    private BookRepo repo;
    private DerateRepo derateRepo;
    private GiftedRepo giftedRepo;
    private GiftedForRepo giftedForRepo;
    private CopyRepo copyRepo;

    @Autowired
    private void setBookRepo(@Qualifier("bookRepo") BookRepo repo) {
        this.repo = repo;
    }

    @Autowired
    private void setDerateRepo(@Qualifier("derateRepo") DerateRepo derateRepo) {
        this.derateRepo = derateRepo;
    }

    @Autowired
    private void setGiftedRepo(@Qualifier("giftedRepo") GiftedRepo repo) {
        giftedRepo = repo;
    }

    @Autowired
    private void setGiftedForRepo(@Qualifier("giftedForRepo") GiftedForRepo giftedForRepo) {
        this.giftedForRepo = giftedForRepo;
    }

    @Autowired
    private void setCopyRepo(@Qualifier("copyRepo") CopyRepo copyRepo) {
        this.copyRepo = copyRepo;
    }

    public void initialize() {
        ObservableList<String> list = FXCollections.observableArrayList("Vernietigd", "Verkocht", "Weggeschonken");
        destination.setItems(list);
        destination.setValue("Vernietigd");
    }

    /**
     * Checks the available copies of a book and decrements it or removes the book completly
     */
    @FXML
    private void derate() {
        Book b = repo.findOne(Integer.parseInt(id.getText()));
        if (b != null) {
            Copies copy = copyRepo.findByTitleAndType(b.getTitle(),"boek");
            StringBuilder sb = new StringBuilder();
            Derated derated = new Derated(b, LocalDate.now(), destination.getValue(), b.getIsbn(), b.getDepot(), b.getTitle(), b.getAuthors());
            derateRepo.save(derated);
            System.out.println(copy.getNrOfCopies());
            if (copy.getNrOfCopies() > 1) {
                copy.setNrOfCopies(copy.getNrOfCopies() - 1);
                copyRepo.save(copy);
                sb.append("Er zijn nog ").append(copy.getNrOfCopies()).append(" kopieën van het boek '").append(b.getTitle()).append("' in de bibliotheek van de kokw.");

            } else {
                if (b.isGifted()) {
                    giftedRepo.deleteByBookId(Integer.parseInt(id.getText()));
                }
                if (b.isGiftedFor()) {
                    giftedForRepo.deleteByBookId(Integer.parseInt(id.getText()));
                }
                sb.append("Er zijn geen kopieën meer van het boek '").append(b.getTitle()).append("' in de bibliotheek van de kokw");
            }
            MenuController.window.close();
            Warning.alert("Derating succesvol", "Het boek '" + b.getTitle() + "' werd met succes gedeclasseerd.\n" + sb.toString());
        } else {
            Warning.alert("No item found", "Het boek werd niet terug gevonden.");
            ChangeScene.init("/fxml/home.fxml", "KOKW - Het verleden draait altijd mee!");
        }

    }
}