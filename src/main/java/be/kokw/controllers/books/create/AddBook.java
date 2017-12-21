package be.kokw.controllers.books.create;

import be.kokw.bean.*;
import be.kokw.repositories.books.*;
import be.kokw.utility.ChangeScene;
import be.kokw.utility.NewStage;
import be.kokw.utility.Validation;
import be.kokw.utility.Warning;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Created by ufotje on 20/10/2017.
 * This class is used to save a book
 */

@Component
public class AddBook {
    @FXML
    private TextField fullName, contractNr, firstName, lastName, title, firstNameAuthor, lastNameAuthor, publisher, subTitle, year, pages, isbn, depot, edition, copies;
    @FXML
    private DatePicker date, boughtOn;
    @FXML
    private CheckBox illustrated, bought, gifted, giftedFor;
    @FXML
    private ChoiceBox<String> topic;
    @FXML
    private ChoiceBox<Integer> volume;
    private Stage window;
    private BookRepo repo;
    private GiftedForRepo giftedForRepo;
    private GiftedRepo giftedRepo;
    private StringBuilder authors = new StringBuilder();
    private List<String> bookList = new ArrayList<>();
    private StringBuilder subTitles = new StringBuilder();
    private File file;
    private Book book;

    @Autowired
    private void setBookRepo(@Qualifier("bookRepo") BookRepo repo) {
        this.repo = repo;
    }

    @Autowired
    private void setGiftedForRepo(@Qualifier("giftedForRepo") GiftedForRepo giftedForRepo) {
        this.giftedForRepo = giftedForRepo;
    }

    @Autowired
    private void setGiftedRepo(@Qualifier("giftedRepo") GiftedRepo giftedRepo) {
        this.giftedRepo = giftedRepo;
    }

    public void initialize() {
        ObservableList<String> topics = FXCollections.observableArrayList("Wereld Oorlog 1", "Wereld Ooorlog 2", "MiddelEeuwen", "Gulden Sporenslag", "Brugse Metten");
        topic.setItems(topics);
        ObservableList<Integer> volumes = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        volume.setItems(volumes);
    }

    @FXML
    public void save() throws Exception {
        book = new Book(isbn.getText(), depot.getText(), title.getText(), subTitles.toString(), Integer.parseInt(edition.getText()), Integer.parseInt(copies.getText()), volume.getValue(), publisher.getText(), Integer.parseInt(year.getText()), Integer.parseInt(pages.getText()), illustrated.isSelected(), authors.toString(), topic.getValue());
        if (validated()) {
            if (gifted.isSelected() && bought.isSelected()) {
                Warning.alert("Multiple Values", "U dient 1 iets te kiezen.\nEen boek kan niet zowel geschonken als aangekocht zijn. ");
            }
            if (gifted.isSelected() && giftedFor.isSelected()) {
                Warning.alert("Multiple Values", "U dient 1 iets te kiezen.\nEen boek kan niet zowel geschonken als gechonke voor een tegenprestatie zijn. ");
            }
            if (giftedFor.isSelected() && bought.isSelected()) {
                Warning.alert("Multiple Values", "U dient 1 iets te kiezen.\nEen boek kan niet zowel geschonken voor een tegenprestatie als aangekocht zijn. ");
            }
            if (gifted.isSelected() && bought.isSelected() && giftedFor.isSelected()) {
                Warning.alert("Multiple Values", "U dient 1 iets te kiezen.\nEen boek kan niet zowel geschonken, geschonken voor een tegenprestatie als aangekocht zijn. ");
            }
            if (gifted.isSelected()) {
                window = NewStage.getStage("Gifted By", "/fxml/books/create/gifted/giftedBy.fxml");
                window.showAndWait();
            }
            if (bought.isSelected()) {
                window = NewStage.getStage("Bought On", "/fxml/books/create/boughtOn.fxml");
                window.showAndWait();
            }
            if (giftedFor.isSelected()) {
                window = NewStage.getStage("Gifted for", "/fxml/books/create/gifted/giftedFor.fxml");
                window.showAndWait();

            }
        }
    }

    @FXML
    private void addSubTitle() {
        subTitles.append(subTitle.getText());
        subTitles.append("\n");
        subTitle.clear();
    }

    @FXML
    private void addAuthor() {
        authors.append(firstNameAuthor.getText());
        authors.append(" ");
        authors.append( lastNameAuthor.getText());
        authors.append("\n");
        firstNameAuthor.clear();
        lastNameAuthor.clear();
    }

    @FXML
    private void giftedBy() throws Exception {
        Gifted gift = new Gifted(firstName.getText(), lastName.getText(), date.getValue(), book);
        giftedRepo.save(gift);
        window.close();
        saveBook(book);
    }

    @FXML
    private void giftedFor() throws Exception {
        GiftedFor giftFor = new GiftedFor(fullName.getText(), contractNr.getText(), file, book);
        giftedForRepo.save(giftFor);
        window.close();
        saveBook(book);
    }

    @FXML
    private void bought() throws Exception {
        LocalDate boughtDate = boughtOn.getValue();
        book.setBoughtOn(boughtDate);
        window.close();
        saveBook(book);
    }

    @FXML
    public void chooseFile() {
        Desktop desktop = Desktop.getDesktop();
        Stage stage = new Stage();
        stage.setTitle("Kies het Contract");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            try {
                desktop.open(file);
            } catch (IOException ex) {
                Logger.getLogger(
                        AddBook.class.getName()).log(
                        Level.SEVERE, null, ex
                );
            }
        } else {
            Warning.alert("No file Selected", "U hebt geen contract geselecteerd!");
        }
    }

    @FXML
    public void more() throws Exception {
        save();
        clearFields();
    }

    private boolean validated() {
        boolean valid = false;
        if (Validation.emptyValidation("Titel", title.getText().isEmpty() &&
                Validation.validate("Uitgeverij:", publisher.getText(), "[a-zA-Z]+") &&
                Validation.validate("Jaar van publicatie:", year.getText(), "[0-9999]+") &&
                Validation.validate("Aantal Bladzijden:", pages.getText(), "[0-9999]+")) &&
                Validation.validate("isbn", isbn.getText(), "[a-zA-Z0-9999 -]+") && Validation.validate("depot", depot.getText(), "[a-zA-Z0-9999 -]+") && Validation.validate("edition", edition.getText(), "[0-999]+") && Validation.validate("copies", copies.getText(), "[0-999]+")) {
            if (Validation.validate("author", authors.toString(), "[a-zA-Z ]+")) {

                //    if(Validation.emptyValidation("topic", topics.to.isEmpty())){
                if (Validation.emptyValidation("subTitle", subTitles.toString().isEmpty())) {
                    valid = true;
                } else {
                    Warning.alert("Wrong input", "Verkeerde invoer!\nControleer uw onderitels aub.");
                }
            } else {
                Warning.alert("Wrong input", "Verkeerde invoer!\nControleer uw auteurs aub.");
            }
        } else {
            Warning.alert("Wrong input", "Verkeerde invoer!\nControleer uw velden aub.");
        }
        return valid;
    }

    private void clearFields() {
        title.clear();
        firstNameAuthor.clear();
        lastNameAuthor.clear();
        isbn.clear();
        depot.clear();
        subTitle.clear();
        edition.clear();
        copies.clear();
        illustrated.setSelected(false);
        giftedFor.setSelected(false);
        gifted.setSelected(false);
        bought.setSelected(false);
        volume.getSelectionModel().clearSelection();
        topic.getSelectionModel().clearSelection();
        publisher.clear();
        year.clear();
        pages.clear();
    }

    private void saveBook(Book book) throws Exception {
        repo.save(book);
        bookList.add(book.getTitle());
        StringBuilder alert = new StringBuilder("The book(s) with title: ");
        for (String s : bookList) {
            alert.append("'");
            alert.append(s);
            alert.append("', '");
        }
        alert.append(" has been successfully saved!");
        Warning.alert("Book saved!", alert.toString());
        ChangeScene.init("/fxml/menu.fxml", "KOKW-AdminApp");
    }
}
