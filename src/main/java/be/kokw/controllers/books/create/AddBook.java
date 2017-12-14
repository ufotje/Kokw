package be.kokw.controllers.books.create;

import be.kokw.bean.*;
import be.kokw.repositories.books.BookRepo;
import be.kokw.repositories.books.*;
import be.kokw.utility.NewStage;
import be.kokw.utility.Warning;
import be.kokw.utility.ChangeScene;
import be.kokw.utility.Validation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
    private AuthorsRepo authorsRepo;
    private SubTitlesRepo subTitlesRepo;
    private TopicsRepo topicsRepo;
    private List<String> bookList = new ArrayList<>();
    private List<String> topicList = new ArrayList<>();
    private List<Authors> authorList = new ArrayList<>();
    private List<String> subTitlesList = new ArrayList<>();
    private File file;
    private SubTitles subTitles;
    private Book book;
    private Authors authors;
    private Topics topics = new Topics(topicList);

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

    @Autowired
    private void setAuthorsRepo(@Qualifier("authorRepo") AuthorsRepo authorsRepo) {
        this.authorsRepo = authorsRepo;
    }

    @Autowired
    private void setSubTitlesRepo(@Qualifier("subRepo") SubTitlesRepo subTitlesRepo) {
        this.subTitlesRepo = subTitlesRepo;
    }

    @Autowired
    private void setTopicsRepo(@Qualifier("topicsRepo") TopicsRepo topicsRepo) {
        this.topicsRepo = topicsRepo;
    }

    public void initialize() {
        ObservableList<String> topics = FXCollections.observableArrayList("Wereld Oorlog 1", "Wereld Ooorlog 2", "MiddelEeuwen", "Gulden Sporenslag", "Brugse Metten");
        topic.setItems(topics);
        ObservableList<Integer> volumes = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        volume.setItems(volumes);
    }

    @FXML
    public void save() throws Exception {
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
    public void addSubTitle() {
        subTitlesList.add(subTitle.getText());
        subTitle.clear();
    }

    @FXML
    public void addAuthor() {
        authors = new Authors(firstNameAuthor.getText(), lastNameAuthor.getText());
        authorList.add(authors);
        firstNameAuthor.clear();
        lastNameAuthor.clear();
    }

    @FXML
    public void giftedBy() throws Exception {
        Gifted gift = new Gifted(firstName.getText(), lastName.getText(), date.getValue());
        book = new Book(isbn.getText(), depot.getText(), title.getText(), subTitles, Integer.parseInt(edition.getText()), Integer.parseInt(copies.getText()), volume.getSelectionModel().getSelectedItem(), authors, publisher.getText(), Integer.parseInt(year.getText()), Integer.parseInt(pages.getText()), topics, gift, illustrated.isSelected());
        gift.setBook(book);
        giftedRepo.save(gift);
        window.close();
        saveBook(book);
    }

    @FXML
    public void giftedFor() throws Exception {
        GiftedFor giftFor = new GiftedFor(fullName.getText(), contractNr.getText(), file);
        book = new Book(isbn.getText(), depot.getText(), title.getText(), subTitles, Integer.parseInt(edition.getText()), Integer.parseInt(copies.getText()), volume.getValue(), authors, publisher.getText(), Integer.parseInt(year.getText()), Integer.parseInt(pages.getText()), topics, giftFor, illustrated.isSelected());
        giftFor.setBook(book);
        giftedForRepo.save(giftFor);
        window.close();
        saveBook(book);
    }

    @FXML
    public void bought() throws Exception {
        LocalDate boughtDate = boughtOn.getValue();
        book = new Book(isbn.getText(), depot.getText(), title.getText(), subTitles, Integer.parseInt(edition.getText()), Integer.parseInt(copies.getText()), volume.getValue(), authors, publisher.getText(), Integer.parseInt(year.getText()), Integer.parseInt(pages.getText()), topics, boughtDate, illustrated.isSelected());
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
            for (Authors a : authorList) {
                if (Validation.validate("author", a.getFirstName(), "[a-zA-Z ]+")) {
                    if (Validation.validate("author", a.getLastName(), "[a-zA-Z ]+")) {
                        //  for(String t : topicList){
                        //    if(Validation.emptyValidation("topic", t.isEmpty())){
                        for (String s : subTitlesList) {
                            if (Validation.emptyValidation("subTitle", s.isEmpty())) {
                                valid = true;
                            } else {
                                Warning.alert("Wrong input", "Verkeerde invoer!\nControleer uw velden aub.");
                            }
                        }
                    }
                        /*}else {
                            Warning.alert("Wrong input", "Verkeerde invoer!\nControleer uw velden aub.");
                        }
                    }*/
                } else {
                    Warning.alert("Wrong input", "Verkeerde invoer!\nControleer uw velden aub.");
                }
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
        subTitles = new SubTitles(subTitlesList);
        for(Authors a : authorList) {
            a.setBook(book);
            authorsRepo.save(a);
            System.out.println(a.getFirstName());
        }
        topics.setBook(book);
        topicsRepo.save(topics);
        subTitles.setBook(book);
        subTitlesRepo.save(subTitles);
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
