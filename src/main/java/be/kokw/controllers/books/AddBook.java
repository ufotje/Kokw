package be.kokw.controllers.books;

import be.kokw.bean.Book;
import be.kokw.repositories.books.interfaces.BookRepo;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by ufotje on 20/10/2017.
 * This class is used to save a book
 */

 public class AddBook {
    @FXML
    private TextField title, topic, firstName, lastName, publisher, place, year, pages;
    @FXML
    private Stage window;
    private BookRepo repo;

    public AddBook(BookRepo repo) {
        this.repo = repo;
        init();
    }

    @FXML
    private void init() {

        title = new TextField();
        title.setTooltip(new Tooltip("Enter title"));
        title.setPromptText("Title");
        title.setMaxWidth(200);

        topic = new TextField();
        topic.setTooltip(new Tooltip("Enter topic"));
        topic.setPromptText("Topic");
        topic.setMaxWidth(200);

        firstName = new TextField();
        firstName.setTooltip(new Tooltip("Enter First Name Author"));
        firstName.setPromptText("First Name");
        firstName.setMaxWidth(200);

        lastName = new TextField();
        lastName.setTooltip(new Tooltip("Enter Last Name Author"));
        lastName.setPromptText("Last Name");
        lastName.setMaxWidth(200);

        publisher = new TextField();
        publisher.setTooltip(new Tooltip("Enter Publisher"));
        publisher.setPromptText("Publisher");
        publisher.setMaxWidth(200);

        place = new TextField();
        place.setTooltip(new Tooltip("Enter Published in"));
        place.setPromptText("City");
        place.setMaxWidth(200);

        year = new TextField();
        year.setTooltip(new Tooltip("Enter Year Published"));
        year.setPromptText("Year");
        year.setMaxWidth(200);

        pages = new TextField();
        pages.setTooltip(new Tooltip("Enter Nr of Pages"));
        pages.setPromptText("Pages");
        pages.setMaxWidth(200);

        Button savebtn = new Button("Save");
        savebtn.setTooltip(new Tooltip("Save"));

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(title, topic, firstName, lastName, publisher, place, year, pages, savebtn);
        vbox.setPadding(new Insets(10));

        Scene scene = new Scene(vbox);

        savebtn.setOnAction(event -> save());

        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Add New Book");
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void save() {
        Book book = new Book();
        book.setTitle(title.getText());
        book.setTopic(topic.getText());
        book.setAuthorFirstName(firstName.getText());
        book.setAuthorLastName(lastName.getText());
        book.setPublisher(publisher.getText());
        book.setPlace(place.getText());
        book.setYearPublished(Integer.parseInt(year.getText()));
        book.setNrOfPages(Integer.parseInt(pages.getText()));
        Book newBook = repo.save(book);
        saveAlert(newBook);
        window.close();
    }

    @FXML
    private void saveAlert(Book book) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("User saved successfully.");
        alert.setHeaderText(null);
        alert.setContentText("The book " + book.getTitle() + " has been created");
        alert.showAndWait();
    }
}
