package be.kokw.controllers.books.update;

import be.kokw.bean.books.Book;
import be.kokw.bean.books.Gifted;
import be.kokw.bean.books.GiftedFor;
import be.kokw.controllers.MenuController;
import be.kokw.repositories.books.BookRepo;
import be.kokw.repositories.books.GiftedForRepo;
import be.kokw.repositories.books.GiftedRepo;
import be.kokw.utility.ChangeScene;
import be.kokw.utility.FileSelector;
import be.kokw.utility.Warning;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


/**
 * Created by ufotje on 20/10/2017.
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

    @FXML
    public void search() {
        book = repo.findOne(Integer.parseInt(id.getText()));
        if (book != null) {
            MenuController.window.close();
            ChangeScene.init("/fxml/books/update/updateBook.fxml", "Boek updaten!");
            VBox vBox = new VBox();
            vBox.setAlignment(Pos.CENTER);
            HBox hb1 = getHBox("Titel:");
            setTextfield(hb1, book.getTitle());
            vBox.getChildren().add(hb1);

            String[] subTitles = book.getSubtitles().split("\n");
            HBox hBox = getHBox("Ondertitel;");
            for (String s : subTitles) {
                setTextfield(hBox, s);
            }
            vBox.getChildren().add(hBox);

            String[] authors = book.getAuthors().split("\n");
            HBox hb3 = getHBox("Auteur");
            for (String author : authors) {
                setTextfield(hb3, author);
            }
            vBox.getChildren().add(hb3);

            String[] topics = book.getTopics().split("\n");
            HBox hb2 = getHBox("Onderwerp:");
            for (String topic : topics) {
                setTextfield(hb2, topic);
            }
            vBox.getChildren().add(hb2);

            HBox hb4 = getHBox("Uitgeverij;");
            setTextfield(hb4, book.getPublisher());
            vBox.getChildren().add(hb4);

            HBox hb5 = getHBox("Jaar van Uitgave:");
            setTextfield(hb5, "" + book.getYearPublished());
            vBox.getChildren().add(hb5);

            HBox hb6 = getHBox("Aantal Pagina's:");
            setTextfield(hb6, "" + book.getNrOfPages());
            vBox.getChildren().add(hb6);

            if (book.isGifted()) {
                Gifted gifted = giftedRepo.findByBookId(book.getId());
                HBox hb7 = getHBox("Naam Gifter:");
                TextField textField7 = new TextField();
                textField7.setAlignment(Pos.CENTER);
                textField7.setText(gifted.getName());
                textField7.setOnAction(event -> gifted.setName(textField7.getText()));
                hb7.getChildren().add(textField7);
                HBox.setMargin(textField7, new Insets(20, 0, 20, 0));
                vBox.getChildren().add(hb7);

                HBox hb8 = getHBox("Datum gift:");
                DatePicker date = new DatePicker();
                date.setValue(gifted.getGiftedOn());
                date.setOnAction(event -> gifted.setGiftedOn(date.getValue()));
                hb8.getChildren().add(date);
                HBox.setMargin(date, new Insets(20, 0, 20, 0));
                vBox.getChildren().add(hb8);
                giftedRepo.saveAndFlush(gifted);
            }

            if (book.getBoughtOn() != null) {
                HBox hb9 = getHBox("Jaar van uitgave:");
                DatePicker date2 = new DatePicker();
                date2.setValue(book.getBoughtOn());
                date2.setOnAction(event -> book.setBoughtOn(date2.getValue()));
                hb9.getChildren().add(date2);
                HBox.setMargin(date2, new Insets(20, 0, 20, 0));
                vBox.getChildren().add(hb9);
                repo.saveAndFlush(book);
            }

            if (book.isGiftedFor()) {
                GiftedFor giftedFor = giftedForRepo.findByBookId(book.getId());
                HBox hb10 = getHBox("ContractNummer:");
                TextField textField10 = new TextField();
                textField10.setAlignment(Pos.CENTER);
                textField10.setText(giftedFor.getContractNr());
                textField10.setOnAction(event -> giftedFor.setContractNr(textField10.getText()));
                Label label11 = new Label("ContractDatum:");
                DatePicker date3 = new DatePicker();
                date3.setValue(giftedFor.getContractDate());
                date3.setOnAction(event -> giftedFor.setContractDate(date3.getValue()));
                hb10.getChildren().addAll(textField10, label11, date3);
                HBox.setMargin(textField10, new Insets(20, 0, 20, 0));
                HBox.setMargin(label11, new Insets(20, 20, 20, 50));
                HBox.setMargin(date3, new Insets(20, 0, 20, 0));
                vBox.getChildren().add(hb10);

                HBox hb11 = getHBox("Contractant:");
                TextField textField11 = new TextField();
                textField11.setAlignment(Pos.CENTER);
                textField11.setText(giftedFor.getName());
                textField11.setOnAction(event -> giftedFor.setName(textField11.getText()));
                hb11.getChildren().add(textField11);
                HBox.setMargin(textField11, new Insets(20, 0, 20, 0));
                vBox.getChildren().add(hb11);

                HBox hb12 = getHBox("Contract:");
                TextField textField12 = new TextField();
                textField12.setAlignment(Pos.CENTER);
                textField12.setText(giftedFor.getContract().getAbsolutePath());
                textField12.setOnAction(event -> giftedFor.setContract(FileSelector.chooseFile()));
                hb12.getChildren().add(textField12);
                HBox.setMargin(textField12, new Insets(20, 0, 50, 0));
                vBox.getChildren().add(hb12);
                giftedForRepo.saveAndFlush(giftedFor);
            }

            Button bt = new Button("updaten");
            bt.setAlignment(Pos.CENTER);
            bt.setFont(Font.font(18));
            bt.setOnAction(event -> update());
            vBox.getChildren().add(bt);
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setContent(vBox);
            scrollPane.setFitToWidth(true);
            scrollPane.setFitToHeight(true);
            vBox.setBackground(new Background(new BackgroundImage(new Image("/images/achtergrond.jpg"),null, null, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
            borderPane.setCenter(scrollPane);

        } else {
            Warning.alert("No Book Found!", "Het boek met " + "'" + id.getText() + "'" + " als id werd niet gevonden!");
        }
    }


    private void update() {
        /*if (validated()) {
            int result = repo.update(new Book(book.getId(), title.getText(), topic.getText(), firstName.getText(), lastName.getText(), publisher.getText(), place.getText(), Integer.parseInt(year.getText()), Integer.parseInt(pages.getText()));
            if (result > 0) {
                String alert = "The book with title: '" + title.getText() + "' has been successfully updated!";
                Warning.alert("Book updated!", alert);
                ChangeScene.init("/fxml/home.fxml", "KOKW - Het verleden draait mee!");
            } else {
                Warning.alert("Update failed", "Corresponderend boek niet gevonden!");
            }
        }*/
    }

    /*  private boolean validated() {
          boolean valid = false;
          if (Validation.emptyValidation("Titel", title.getText().isEmpty() &&
                  Validation.emptyValidation("Topic", topic.getText().isEmpty()) &&
                  Validation.validate("Achternaam Auteur", lastName.getText(), "[a-zA-Z -]+") &&
                  Validation.validate("Voornaam Auteur:", firstName.getText(), "[a-zA-Z]+")) &&
                  Validation.validate("Uitgeverij:", publisher.getText(), "[a-zA-Z -]+") &&
                  Validation.emptyValidation("Plaats Uitgeverij", place.getText().isEmpty()) &&
                  Validation.validate("Jaar van publicatie:", year.getText(), "[0-9999]+") &&
                  Validation.validate("Aantal Bladzijden:", pages.getText(), "[0-9999]+")) {
              valid = true;
          }
          return valid;
      }*/
    private HBox getHBox(String value){
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        Label label1 = new Label(value);
        hBox.getChildren().add(label1);
        HBox.setMargin(label1, new Insets(20, 20, 20, 100));
        return hBox;
    }
    private void setTextfield(HBox hBox, String value) {
        TextField textField = new TextField();
        textField.setAlignment(Pos.CENTER);
        textField.setText(value);
        textField.setOnAction(event -> Warning.alert("Error!", "Dit veld kan niet aangepast worden!"));
        HBox.setMargin(textField, new Insets(0, 20, 20, 0));
        hBox.getChildren().add(textField);
    }
}
