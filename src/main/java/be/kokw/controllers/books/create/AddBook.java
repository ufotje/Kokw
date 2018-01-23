package be.kokw.controllers.books.create;

import be.kokw.bean.books.Book;
import be.kokw.bean.Copies;
import be.kokw.bean.books.Gifted;
import be.kokw.bean.books.GiftedFor;
import be.kokw.repositories.books.*;
import be.kokw.utility.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


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
    @FXML
    DatePicker contractDate;
    private Stage window;
    private BookRepo repo;
    private StringBuilder authors = new StringBuilder();
    private List<String> bookList = new ArrayList<>();
    private StringBuilder subTitles = new StringBuilder();
    private StringBuilder topics = new StringBuilder();
    private File file;
    private Book book;
    private GiftedRepo giftedRepo;
    private GiftedForRepo giftedForRepo;
    private CopyRepo copyRepo;

    @Autowired
    private void setBookRepo(@Qualifier("bookRepo") BookRepo repo) {
        this.repo = repo;
    }

    @Autowired
    private void setGiftedRepo(@Qualifier("giftedRepo") GiftedRepo giftedRepo) {
        this.giftedRepo = giftedRepo;
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
        ObservableList<String> topics = FXCollections.observableArrayList("Wereld Oorlog 1", "Wereld Ooorlog 2", "MiddelEeuwen", "Gulden Sporenslag", "Brugse Metten");
        topic.setItems(topics);
        ObservableList<Integer> volumes = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        volume.setItems(volumes);
        volume.setValue(1);
    }

    @FXML
    public void save() {
        if (validated()) {
            book = new Book(isbn.getText(), depot.getText(), title.getText(), subTitles.toString(), Integer.parseInt(edition.getText()), volume.getValue(), publisher.getText(), Integer.parseInt(year.getText()), Integer.parseInt(pages.getText()), illustrated.isSelected(), authors.toString(), topics.append(topic.getValue()).toString());
            if (gifted.isSelected() && bought.isSelected() && !giftedFor.isSelected()) {
                Warning.alert("Multiple Values", "U dient 1 iets te kiezen.\nEen boek kan niet zowel geschonken als aangekocht zijn. ");
            }
            if (gifted.isSelected() && giftedFor.isSelected() && !bought.isSelected()) {
                Warning.alert("Multiple Values", "U dient 1 iets te kiezen.\nEen boek kan niet zowel geschonken als gechonke voor een tegenprestatie zijn. ");
            }
            if (giftedFor.isSelected() && bought.isSelected() && !gifted.isSelected()) {
                Warning.alert("Multiple Values", "U dient 1 iets te kiezen.\nEen boek kan niet zowel geschonken voor een tegenprestatie als aangekocht zijn. ");
            }
            if (gifted.isSelected() && bought.isSelected() && giftedFor.isSelected()) {
                Warning.alert("Multiple Values", "U dient 1 iets te kiezen.\nEen boek kan niet zowel geschonken, geschonken voor een tegenprestatie als aangekocht zijn. ");
            }
            if (gifted.isSelected() && !bought.isSelected() && !giftedFor.isSelected()) {
                book.setGifted(true);
                window = NewStage.getStage("Gifted By", "/fxml/books/create/gifted/giftedBy.fxml");
                window.showAndWait();
            }
            if (bought.isSelected() && !giftedFor.isSelected() && !gifted.isSelected()) {
                window = NewStage.getStage("Bought On", "/fxml/books/create/boughtOn.fxml");
                window.showAndWait();
            }
            if (giftedFor.isSelected() && !gifted.isSelected() && !bought.isSelected()) {
                book.setGiftedFor(true);
                window = NewStage.getStage("Gifted for", "/fxml/books/create/gifted/giftedFor.fxml");
                window.showAndWait();
            }
            if (!gifted.isSelected() && !bought.isSelected() && !giftedFor.isSelected()) {
                Warning.alert("No Value", "Gelieve iets te selecteren.\nEen boek dient aangekocht, gedoneerd of gedoneerd voor een tegenprestatie te zijn.");
            }
        }
    }

    @FXML
    private void addSubTitle() {
        subTitles = AddSubtitle.addSubtitles(subTitle);
        subTitle.clear();
    }

    @FXML
    private void addAuthor() {
        authors = AddAuthor.add(firstNameAuthor, lastNameAuthor);
        firstNameAuthor.clear();
        lastNameAuthor.clear();
    }

    @FXML
    private void addTopic() {
        if (topic.getValue() != null) {
            topics.append(topic.getValue());
            topics.append("\n");
        }
    }

    @FXML
    private void giftedBy() {
        if (Validation.validate("firstName", firstName.getText(), "[a-zA-Z \\-]+")) {
            if (Validation.validate("lastName", lastName.getText(), "[a-zA-Z \\-]+")) {
                if (Validation.validate("date", date.getValue().toString(), "[0-9\\-]+")) {
                    Gifted gift = new Gifted(firstName.getText() + " " + lastName.getText(), date.getValue(), book);
                    Gifted g = giftedRepo.save(gift);
                    saveCopies();
                    window.close();
                    if (g != null) {
                        Warning.alert("Book saved!", "Het boek '" + book.getTitle() + "' werd succesvol opgeslaan");
                    } else {
                        Warning.alert("Error!", "Er ging iets fout");
                    }
                } else {
                    Warning.alert("Date Error", "Geef aub een geldige datum in.");
                }
            } else {
                Warning.alert("Lastname Error", "Geef een geldige achternaam in.\nEnkel letters, spaties of - zijn toegestaan.");
            }
        } else {
            Warning.alert("Firstname Error", "Geef een geldige voornaam in.\nEnkel letters, spaties of - zijn toegestaan.");
        }
    }

    @FXML
    private void giftedFor() {
        if (Validation.validate("fullName", fullName.getText(), "[a-zA-Z \\-]+")) {
            GiftedFor giftedFor = new GiftedFor(fullName.getText(), contractNr.getText(), file, contractDate.getValue(), book);
            GiftedFor gf = giftedForRepo.save(giftedFor);
            saveCopies();
            window.close();
            if (gf != null) {
                Warning.alert("Book saved!", "Het boek '" + book.getTitle() + "' werd succesvol opgeslaan");
            } else {
                Warning.alert("Error!", "Er ging iets fout");
            }
        } else {
            Warning.alert("Wrong value", "De contractant is verkeerd ingevuld.");
        }

    }

    @FXML
    private void bought() {
        LocalDate boughtDate = boughtOn.getValue();
        book.setBoughtOn(boughtDate);
        window.close();
        saveBook(book);
    }

    @FXML
    public void chooseFile() {
        file = FileSelector.chooseFile();
    }

    @FXML
    public void more() {
        save();
        clearFields();
    }

    private boolean validated() {
        boolean valid = false;
        if (Validation.emptyValidation("Titel", title.getText().isEmpty() &&
                Validation.validate("Uitgeverij:", publisher.getText(), "[a-zA-Z]+") &&
                Validation.validate("Jaar van publicatie:", year.getText(), "[0-9999]+") &&
                Validation.validate("Aantal Bladzijden:", pages.getText(), "[0-9999]+")) &&
                Validation.validate("isbn", isbn.getText(), "[a-zA-Z0-9999 \\-]+") && Validation.validate("depot", depot.getText(), "[a-zA-Z0-9999 -]+") && Validation.validate("edition", edition.getText(), "[0-999]+") && Validation.validate("copies", copies.getText(), "[0-999]+")) {
            valid = true;
        } else {
            Warning.alert("Wrong input", "Verkeerde invoer!\nControleer uw velden aub.");
        }

        return valid;
    }

    private void saveCopies() {
        Copies copy = copyRepo.findByTitle(book.getTitle());
        if (copy != null) {
            copy.setNrOfCopies(copy.getNrOfCopies() + 1);
            copyRepo.save(copy);
            ChangeScene.init("/fxml/home.fxml", "KOKW - Het Verleden Draait Altijd Mee!");
        } else {
            Copies c = new Copies(title.getText(), "Boek");
            copyRepo.save(c);
            ChangeScene.init("/fxml/home.fxml", "KOKW - Het Verleden Draait Altijd Mee!");
        }
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

    private void saveBook(Book book) {
        repo.save(book);
        saveCopies();
        bookList.add(book.getTitle());
        StringBuilder alert = new StringBuilder("The book(s) with title: ");
        for (String s : bookList) {
            alert.append("'");
            alert.append(s);
            alert.append("', '");
        }
        alert.append(" has been successfully saved!");
        Warning.alert("Book saved!", alert.toString());
        ChangeScene.init("/fxml/menu.fxml", "KOKW - Het Verleden Draait Altijd Mee!");
    }
}
