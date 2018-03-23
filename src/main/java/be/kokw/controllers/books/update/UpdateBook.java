package be.kokw.controllers.books.update;

import be.kokw.bean.books.Book;
import be.kokw.bean.books.Gifted;
import be.kokw.bean.books.GiftedFor;
import be.kokw.controllers.MenuController;
import be.kokw.repositories.books.BookRepo;
import be.kokw.repositories.books.GiftedForRepo;
import be.kokw.repositories.books.GiftedRepo;
import be.kokw.utility.sceneControl.ChangeScene;
import be.kokw.utility.controller.FileSelector;
import be.kokw.utility.controller.GetHbox;
import be.kokw.utility.controller.SetTextField;
import be.kokw.utility.validation.Warning;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


/**
 * Created by Demesmaecker Daniel on 20/10/2017.
 * This class is used to update a book
 */

@Component
public class UpdateBook {
    @FXML
    private TextField id;
    @FXML
    private BorderPane borderPane;
    private BookRepo repo;
    private GiftedRepo giftedRepo;
    private GiftedForRepo giftedForRepo;
    private Book book;
    private GiftedFor giftedFor;
    private Gifted gifted;

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

    /**
     * opens the updatepage and prefills the inputfields
     */
    @FXML
    public void search() {
        MenuController.window.close();
        book = repo.findOne(Integer.parseInt(id.getText()));
        if (book != null) {
            ChangeScene.init("/fxml/books/update/updateBook.fxml", "Boek updaten!");
            VBox vBox = new VBox();
            vBox.setAlignment(Pos.CENTER);
            HBox hb1 = GetHbox.get("Titel:");
            SetTextField.set(hb1, book.getTitle());
            HBox.setMargin(hb1, new Insets(100, 0, 0, 0));
            vBox.getChildren().add(hb1);

            String[] subTitles = book.getSubtitles().split("\n");
            HBox hBox = GetHbox.get("Ondertitel:");
            for (String s : subTitles) {
                SetTextField.set(hBox, s);
            }
            for(Node n : hBox.getChildren()) {
                HBox.setMargin(n, new Insets(0, 20, 30, 0));
            }
            HBox.setMargin(hBox.getChildren().get(0), new Insets(0,20,30,70));
            vBox.getChildren().add(hBox);

            String[] authors = book.getAuthors().split("\n");
            HBox hb3 = GetHbox.get("Auteurs:");
            for (String author : authors) {
                SetTextField.set(hb3, author);
            }
            for(Node n : hb3.getChildren()) {
                HBox.setMargin(n, new Insets(0, 20, 30, 0));
            }
            HBox.setMargin(hb3.getChildren().get(0), new Insets(0,20,30,70));
            vBox.getChildren().add(hb3);

            String[] topics = book.getTopics().split("\n");
            HBox hb2 = GetHbox.get("Onderwerpen:");
            for (String topic : topics) {
                SetTextField.set(hb2, topic);
            }
            for(Node n : hb2.getChildren()) {
                HBox.setMargin(n, new Insets(0, 20, 30, 0));
            }
            HBox.setMargin(hb2.getChildren().get(0), new Insets(0,20,30,70));
            vBox.getChildren().add(hb2);

            HBox hb4 = GetHbox.get("Uitgeverij:");
            SetTextField.set(hb4, book.getPublisher());
            Label label5 = new Label("Jaar van Uitgave:");
            hb4.getChildren().add(label5);
            HBox.setMargin(label5, new Insets(0, 20, 30, 50));
            SetTextField.set(hb4, "" + book.getYearPublished());
            Label label6 = new Label("Aantal Pagina's:");
            hb4.getChildren().add(label6);
            HBox.setMargin(label6, new Insets(0, 20, 30, 50));
            SetTextField.set(hb4, "" + book.getNrOfPages());
            vBox.getChildren().add(hb4);

            if (book.isGifted()) {
                gifted = giftedRepo.findByBookId(book.getId());
                HBox hb7 = GetHbox.get("Naam Gifter:");
                TextField textField7 = new TextField();
                textField7.setAlignment(Pos.CENTER);
                textField7.setText(gifted.getName());
                textField7.textProperty().addListener((observable, oldValue, newValue) -> gifted.setName(newValue));
                hb7.getChildren().add(textField7);
                HBox.setMargin(textField7, new Insets(0, 0, 30, 0));
                Label label8 = new Label("Datum gift:");
                hb7.getChildren().add(label8);
                HBox.setMargin(label8, new Insets(0, 20, 30, 50));
                DatePicker date = new DatePicker();
                date.setValue(gifted.getGiftedOn());
                date.valueProperty().addListener((observable, oldValue, newValue) -> gifted.setGiftedOn(date.getValue()));
                hb7.getChildren().add(date);
                HBox.setMargin(date, new Insets(0, 0, 30, 0));
                vBox.getChildren().add(hb7);
            }

            if (book.getBoughtOn() != null) {
                HBox hb9 = GetHbox.get("Datum Aankoop:");
                DatePicker date2 = new DatePicker();
                date2.setValue(book.getBoughtOn());
                date2.valueProperty().addListener((observable, oldValue, newValue) -> book.setBoughtOn(newValue));
                hb9.getChildren().add(date2);
                HBox.setMargin(date2, new Insets(0, 0, 30, 0));
                vBox.getChildren().add(hb9);
            }

            if (book.isGiftedFor()) {
                giftedFor = giftedForRepo.findByBookId(book.getId());
                HBox hb10 = GetHbox.get("ContractNummer:");
                TextField textField10 = new TextField();
                textField10.setAlignment(Pos.CENTER);
                textField10.setText(giftedFor.getContractNr());
                textField10.textProperty().addListener((observable, oldValue, newValue) -> {
                    giftedFor.setContractNr(newValue);
                    System.out.println("Contractnummer changed");
                });
                Label label11 = new Label("ContractDatum:");
                DatePicker date3 = new DatePicker();
                date3.setValue(giftedFor.getContractDate());
                date3.valueProperty().addListener((observable, oldValue, newValue) -> giftedFor.setContractDate(newValue));
                hb10.getChildren().addAll(textField10, label11, date3);
                HBox.setMargin(textField10, new Insets(0, 0, 30, 0));
                HBox.setMargin(label11, new Insets(0, 20, 30, 50));
                HBox.setMargin(date3, new Insets(0, 0, 30, 0));
                vBox.getChildren().add(hb10);

                HBox hb11 = GetHbox.get("Contractant:");
                TextField textField11 = new TextField();
                textField11.setAlignment(Pos.CENTER);
                textField11.setText(giftedFor.getName());
                textField11.textProperty().addListener((observable, oldValue, newValue) -> giftedFor.setName(newValue));
                hb11.getChildren().add(textField11);
                HBox.setMargin(textField11, new Insets(0, 0, 30, 0));

                Label label12 = new Label("Contract:");
                TextField textField12 = new TextField();
                textField12.setAlignment(Pos.CENTER);
                textField12.setText(giftedFor.getContract().getAbsolutePath());
                textField12.setOnMouseClicked(event -> giftedFor.setContract(FileSelector.chooseFile()));
                hb11.getChildren().add(label12);
                hb11.getChildren().add(textField12);
                HBox.setMargin(label12, new Insets(0, 20, 30, 50));
                HBox.setMargin(textField12, new Insets(0, 0, 30, 0));
                vBox.getChildren().add(hb11);
            }

            Button bt = new Button("updaten");
            bt.setAlignment(Pos.CENTER);
            bt.setFont(Font.font(18));
            bt.setOnAction(event -> update());
            vBox.getChildren().add(bt);
            VBox.setMargin(bt, new Insets(20, 0, 0, 100));
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setContent(vBox);
            scrollPane.setFitToWidth(true);
            scrollPane.setFitToHeight(true);
            vBox.setBackground(new Background(new BackgroundImage(new Image("/images/achtergrond.jpg"), null, null, BackgroundPosition.CENTER, new BackgroundSize(800, 400, false, false, false, true))));
            borderPane.setCenter(scrollPane);
        } else {
            Warning.alert("No Book Found!", "Het boek met " + "'" + id.getText() + "'" + " als id werd niet gevonden!");
            ChangeScene.init("/fxml/home.fxml", "KOKW - Het verleden draait altijd mee!");
        }
    }

    /**
     * Updates the book in the database
     */
    @FXML
    private void update() {
        if (book.isGifted()) {
            giftedRepo.saveAndFlush(gifted);
        }
        if (book.getBoughtOn() != null) {
            repo.saveAndFlush(book);
        }
        if (book.isGiftedFor()) {
            giftedForRepo.saveAndFlush(giftedFor);
        }
        ChangeScene.init("/fxml/home.fxml", "KOKW - Het Verleden Draait Altijd Mee!");
    }
}
