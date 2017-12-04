package be.kokw.controllers.books.create;

import be.kokw.bean.*;
import be.kokw.repositories.BookRepo;
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
    private TextField fullName, contractNr, firstName, lastName, title, author, publisher, subTitle, year, pages, isbn, depot, edition, copies;
    @FXML
    private DatePicker date, boughtOn;
    @FXML
    private CheckBox illustrated, bought, gifted, giftedFor;
    @FXML
    private ChoiceBox <String> topic;
    @FXML
    private ChoiceBox <Integer> volume;
    private BookRepo repo;
    private List<String> bookList = new ArrayList<>();
    private List<String> topicList = new ArrayList<>();
    private List<String> authorList = new ArrayList<>();
    private List<String> subTitlesList = new ArrayList<>();
    private Gifted gift;
    private File file;

    @Autowired
    private void SetBookRepo(@Qualifier("bookRepo") BookRepo repo) {
        this.repo = repo;
    }

    private void initialize() {
        ObservableList<String> topics = FXCollections.observableArrayList("Wereld Oorlog 1", "Wereld Ooorlog 2", "MiddelEeuwen", "Gulden Sporenslag", "Brugse Metten");
        topic = new ChoiceBox<>(topics);
        ObservableList<Integer> volumes = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        volume = new ChoiceBox<>(volumes);
    }

    @FXML
    public void save() throws Exception {
        if (validated()) {
            SubTitles subTitles = new SubTitles(subTitlesList);
            Book book;
            Authors authors = new Authors(authorList);
            topicList.add(topic.getValue());
            Topics topics = new Topics(topicList);
            if (gifted.isSelected() && bought.isSelected()) {
                Warning.alert("Multiple Values", "U dient 1 iets te kiezen.\nEen boek kan niet zowel geschonken als aangekocht zijn. ");
            }
            if (gifted.isSelected() && giftedFor.isSelected()) {
                Warning.alert("Multiple Values", "U dient 1 iets te kiezen.\nEen boek kan niet zowel geschonken als gechonke voor een tegenprestatie zijn. ");
            }
            if (giftedFor.isSelected() && bought.isSelected()) {
                Warning.alert("Multiple Values", "U dient 1 iets te kiezen.\nEen boek kan niet zowel geschonken voor een tegenprestatie als aangekocht zijn. ");
            }
            if(gifted.isSelected() && bought.isSelected() && giftedFor.isSelected()){
                Warning.alert("Multiple Values", "U dient 1 iets te kiezen.\nEen boek kan niet zowel geschonken, geschonken voor een tegenprestatie als aangekocht zijn. ");
            }
            if (gifted.isSelected()) {
                Stage window = NewStage.getStage("Gifted By","/fxml/books/create/gifted/giftedBy.fxml");
                gift = new Gifted(firstName.getText(), lastName.getText(), date.getValue());
                book = new Book(isbn.getText(), depot.getText(), title.getText(), subTitles, Integer.parseInt(edition.getText()), Integer.parseInt(copies.getText()), volume.getValue(), authors, publisher.getText(), Integer.parseInt(year.getText()), Integer.parseInt(pages.getText()),topics, gift,illustrated.isSelected());
                gift.setBook(book);
                window.close();
                saveBook(book);
            }
            if(bought.isSelected()){
                Stage window = NewStage.getStage("Bought On", "/fxml/books/boughtOn.fxml");
                book = new Book(isbn.getText(), depot.getText(), title.getText(), subTitles, Integer.parseInt(edition.getText()), Integer.parseInt(copies.getText()), volume.getValue(), authors, publisher.getText(), Integer.parseInt(year.getText()), Integer.parseInt(pages.getText()),topics, boughtOn.getValue(),illustrated.isSelected());
                window.close();
                saveBook(book);
            }
            if(giftedFor.isSelected()){
                Stage window = NewStage.getStage("Gifted for", "/fxml/books/create/gifted/giftedFor.fxml");
                GiftedFor giftFor = new GiftedFor(fullName.getText(), contractNr.getText(), file);
                book = new Book(isbn.getText(), depot.getText(), title.getText(), subTitles, Integer.parseInt(edition.getText()), Integer.parseInt(copies.getText()), volume.getValue(), authors, publisher.getText(), Integer.parseInt(year.getText()), Integer.parseInt(pages.getText()),topics, giftFor,illustrated.isSelected());
                giftFor.setBook(book);
                window.close();
                saveBook(book);

            }else{
                Warning.alert("No Value", "U dient 1 iets te kiezen.\nEen boek dient geschonken, geschonken voor een tegenprestatie of aangekocht te zijn. ");
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
        authorList.add(author.getText());
        author.clear();
    }

    @FXML
    public void giftedBy(){
        gift = new Gifted(firstName.getText(), lastName.getText(), date.getValue());
    }

    @FXML
    public void giftedFor(){

    }

    @FXML
    public void chooseFile(){
        Desktop desktop = Desktop.getDesktop();
        Stage stage = new Stage();
        stage.setTitle("Kies het Contract");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        file = fileChooser.showOpenDialog(stage);
        if(file != null) {
            try {
                desktop.open(file);
            } catch (IOException ex) {
                Logger.getLogger(
                        AddBook.class.getName()).log(
                        Level.SEVERE, null, ex
                );
            }
        }else{
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
                Validation.emptyValidation("isbn", isbn.getText().isEmpty()) && Validation.emptyValidation("depot", depot.getText().isEmpty()) && Validation.validate("edition", edition.getText(), "{0-999]+") && Validation.validate("copies", copies.getText(), "[0-999]+")) {
            for(String a : authorList){
                if(Validation.validate("author", a, "[a-zA-Z ]+")){
                    for(String t : topicList){
                        if(Validation.emptyValidation("topic", t.isEmpty())){
                            for(String s : subTitlesList){
                                if(Validation.emptyValidation("subTitle", s.isEmpty())){
                                    valid = true;
                                }
                            }
                        }
                    }
                }
            }
        }else {
            Warning.alert("Wrong input", "Verkeerde invoer!\nControleer uw velden aub.");
        }
        return valid;
    }

    private void clearFields() {
        title.clear();
        author.clear();
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
