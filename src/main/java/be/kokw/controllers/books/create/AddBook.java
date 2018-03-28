package be.kokw.controllers.books.create;

import be.kokw.bean.books.Book;
import be.kokw.bean.Copies;
import be.kokw.bean.books.Gifted;
import be.kokw.bean.books.GiftedFor;
import be.kokw.repositories.books.*;
import be.kokw.utility.autocomplete.BookTextFields;
import be.kokw.utility.controller.AddAuthor;
import be.kokw.utility.controller.AddSubtitle;
import be.kokw.utility.controller.FileSelector;
import be.kokw.utility.sceneControl.ChangeScene;
import be.kokw.utility.sceneControl.NewStage;
import be.kokw.utility.validation.Validation;
import be.kokw.utility.validation.Warning;
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

import static javafx.collections.FXCollections.observableArrayList;


/**
 * Created by Daniel Demesmaecker on 20/10/2017.
 * This class is used to save a book
 */

@Component
public class AddBook {
    @FXML
    private TextField fullName, contractNr, firstName, lastName, title, author, publisher, subTitle, year, pages, isbn, depot, edition;
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

    /**
     * The initialize method is used to fill the choiceboxes and to set an autocomplete on the textfields
     */
    public void initialize() {
        ObservableList<String> topics = observableArrayList("Wereld Oorlog 1", "Wereld Oorlog 2", "MiddelEeuwen", "Gulden Sporenslag", "Brugse Metten");
        topic.setItems(topics);
        ObservableList<Integer> volumes = observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        volume.setItems(volumes);
        volume.setValue(1);
        BookTextFields.autocomplete(repo.findAll(), title, author, subTitle, publisher);
    }

    /**
     * This method checks in which way the book is acquired and if there's not more then one way selected
     * After the check is performed the correct detail window is opened.
     */
    private void save() {
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
        subTitles.append(AddSubtitle.addSubtitles(subTitle));
        subTitle.clear();
    }

    @FXML
    private void addAuthor() {
        authors.append(AddAuthor.add(author.getText()));
        author.clear();
    }

    @FXML
    private void addTopic() {
        if (topic.getValue() != null) {
            topics.append(topic.getValue());
            topics.append("\n");
        }
    }

    /**
     * Checks the inputfields of the giftedByDetailswindow for incorrect values,
     * Creates an new Giftedobject and saves the book and details to the db.
     */
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
                    }
                }
            }
        }
    }

    /**
     * Checks the inputfields of the giftedForDetailswindow for incorrect values,
     * Creates an new GiftedForobject and saves the book and details to the db.
     */
    @FXML
    private void giftedFor() {
        if (Validation.validate("fullName", fullName.getText(), "[a-zA-Z \\-]+")) {
            GiftedFor giftedFor = new GiftedFor(fullName.getText(), contractNr.getText(), file, contractDate.getValue(), book);
            GiftedFor gf = giftedForRepo.save(giftedFor);
            saveCopies();
            window.close();
            if (gf != null) {
                Warning.alert("Book saved!", "Het boek '" + book.getTitle() + "' werd succesvol opgeslaan");
            }
        }
    }

    /**
     * Checks the inputfields of the BoughtDetailswindow for incorrect values
     * and saves the book and details to the db.
     */
    @FXML
    public void bought() {
        LocalDate boughtDate = boughtOn.getValue();
        book.setBoughtOn(boughtDate);
        window.close();
        repo.save(book);
        saveCopies();
    }

    @FXML
    public void chooseFile() {
        file = FileSelector.chooseFile();
    }

    @FXML
    public void more() {
        save();
        if (validated()) {
            clearFields();
        }
        authors = new StringBuilder();
        subTitles = new StringBuilder();
    }

    /**
     * Validates The Inputfields
     *
     * @return boolean
     */
    private boolean validated() {
        boolean valid = false;
        if (Validation.emptyValidation("Titel", title.getText().isEmpty() &&
                Validation.validate("Uitgeverij:", publisher.getText(), "[a-zA-Z]+") &&
                Validation.validate("Jaar van publicatie:", year.getText(), "[0-9999]+") &&
                Validation.validate("Aantal Bladzijden:", pages.getText(), "[0-9999]+")) &&
                Validation.validate("isbn", isbn.getText(), "[a-zA-Z0-9999 \\-]+") &&
                Validation.validate("depot", depot.getText(), "[a-zA-Z0-9999 -]+") &&
                Validation.validate("edition", edition.getText(), "[0-999]+")) {
            valid = true;
        }

        return valid;
    }

    /**
     * Checks if a book is already in the db, if so it increments the available copies by one,
     * else it creates a new one
     */
    private void saveCopies() {
        Copies copy = copyRepo.findByTitleAndType(book.getTitle(), "boek");
        if (copy != null) {
            copy.setNrOfCopies(copy.getNrOfCopies() + 1);
            copyRepo.save(copy);
        } else {
            Copies c = new Copies(title.getText(), "Boek", volume.getValue(), book);
            copyRepo.save(c);
        }
    }

    /**
     * Removes the values from the inputfields
     */
    private void clearFields() {
        title.clear();
        author.clear();
        isbn.clear();
        depot.clear();
        subTitle.clear();
        edition.clear();
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

    @FXML
    private void saveBook() {
        save();
        ChangeScene.init("/fxml/menu.fxml", "KOKW - Het Verleden Draait Altijd Mee!");
    }
}
