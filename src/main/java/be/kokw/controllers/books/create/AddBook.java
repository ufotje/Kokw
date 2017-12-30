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
    private Stage window;
    private BookRepo repo;
    private StringBuilder authors = new StringBuilder();
    private List<String> bookList = new ArrayList<>();
    private StringBuilder subTitles = new StringBuilder();
    private StringBuilder topics = new StringBuilder();
    private File file;
    private Book book;

    @Autowired
    private void setBookRepo(@Qualifier("bookRepo") BookRepo repo) {
        this.repo = repo;
    }

    public void initialize() {
        ObservableList<String> topics = FXCollections.observableArrayList("Wereld Oorlog 1", "Wereld Ooorlog 2", "MiddelEeuwen", "Gulden Sporenslag", "Brugse Metten");
        topic.setItems(topics);
        ObservableList<Integer> volumes = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        volume.setItems(volumes);
        volume.setValue(1);
    }

    @FXML
    public void save() throws Exception {
        if (validated()) {
            book = new Book(isbn.getText(), depot.getText(), title.getText(), subTitles.toString(), Integer.parseInt(edition.getText()), Integer.parseInt(copies.getText()), volume.getValue(), publisher.getText(), Integer.parseInt(year.getText()), Integer.parseInt(pages.getText()), illustrated.isSelected(), authors.toString(), topics.append(topic.getValue()).toString());
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
            if (!gifted.isSelected() && !bought.isSelected() && !giftedFor.isSelected()) {
                Warning.alert("No Value", "Gelieve iets te selecteren.\nEen boek dient aangekocht, gedoneerd of gedoneerd voor een tegenprestatie te zijn.");
            }
        }
    }

    @FXML
    private void addSubTitle() {
        if (Validation.emptyValidation("subTitle", subTitle.getText().isEmpty())) {
            subTitles.append(subTitle.getText());
            subTitles.append("\n");
            subTitle.clear();
        } else {
            Warning.alert("Wrong input", "Verkeerde invoer!\nControleer uw velden aub.");
        }
    }

    @FXML
    private void addAuthor() {
        if (Validation.validate("author", firstNameAuthor.getText(), "[a-zA-Z \\-]+")) {
            if (Validation.validate("author", lastNameAuthor.getText(), "[a-zA-Z \\-]+")) {
                authors.append(firstNameAuthor.getText());
                authors.append(" ");
                authors.append(lastNameAuthor.getText());
                authors.append("\n");
                firstNameAuthor.clear();
                lastNameAuthor.clear();
            } else {
                Warning.alert("Wrong Input!", "Verkeerde invoer voor achternaam Auteur.");
            }
        } else {
            Warning.alert("Wrong Input!", "Verkeerde invoer voor voornaam Auteur.");
        }
    }

    @FXML
    private void addTopic() {
        if (topic.getValue() != null) {
            topics.append(topic.getValue());
            topics.append("\n");
        }
    }

    @FXML
    private void giftedBy() throws Exception {
        if (Validation.validate("firstName", firstName.getText(), "[a-zA-Z \\-]+")) {
            if (Validation.validate("lastName", lastName.getText(), "[a-zA-Z \\-]+")) {
                if (Validation.validate("date", date.getValue().toString(), "[0-9\\-]+")) {
                    book.setNameGifter(firstName.getText() + " " + lastName.getText());
                    book.setGiftedOn(date.getValue());
                    saveBook(book);
                    window.close();
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
    private void giftedFor() throws Exception {
        if (Validation.validate("fullName", fullName.getText(), "[a-zA-Z \\-]+")) {
            book.setContractNr(contractNr.getText());
            book.setContract(file);
            book.setContractor(fullName.getText());
            window.close();
            saveBook(book);
        } else {
            Warning.alert("Wrong value", "De contractant is verkeerd ingevuld.");
        }

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
        Stage stage = new Stage();
        stage.setTitle("Kies het Contract");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        file = fileChooser.showOpenDialog(stage);
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
                Validation.validate("isbn", isbn.getText(), "[a-zA-Z0-9999 \\-]+") && Validation.validate("depot", depot.getText(), "[a-zA-Z0-9999 -]+") && Validation.validate("edition", edition.getText(), "[0-999]+") && Validation.validate("copies", copies.getText(), "[0-999]+")) {
            //    if(Validation.emptyValidation("topic", topics.to.isEmpty())){
            valid = true;
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
