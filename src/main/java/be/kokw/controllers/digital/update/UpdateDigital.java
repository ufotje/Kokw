package be.kokw.controllers.digital.update;

import be.kokw.bean.digital.Digital;
import be.kokw.bean.digital.DigitalDonated;
import be.kokw.bean.digital.DigitalTraded;
import be.kokw.controllers.MenuController;
import be.kokw.repositories.digital.DigitalDonateRepo;
import be.kokw.repositories.digital.DigitalRepo;
import be.kokw.repositories.digital.DigitalTradeRepo;
import be.kokw.utility.controller.UpdateDate;
import be.kokw.utility.sceneControl.ChangeScene;
import be.kokw.utility.controller.FileSelector;
import be.kokw.utility.controller.GetHbox;
import be.kokw.utility.controller.SetTextField;
import be.kokw.utility.validation.Warning;
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
public class UpdateDigital {
    @FXML
    private TextField id;
    @FXML
    private BorderPane borderPane;
    private DigitalRepo repo;
    private DigitalDonateRepo giftedRepo;
    private DigitalTradeRepo giftedForRepo;
    private Digital digital;
    private DigitalTraded giftedFor;
    private DigitalDonated gifted;

    @Autowired
    private void setRepo(@Qualifier("digitalRepo") DigitalRepo repo) {
        this.repo = repo;
    }

    @Autowired
    private void setGiftedRepo(@Qualifier("digiDonateRepo") DigitalDonateRepo giftedRepo) {
        this.giftedRepo = giftedRepo;
    }

    @Autowired
    private void setGiftedForRepo(@Qualifier("digiTradeRepo") DigitalTradeRepo giftedForRepo) {
        this.giftedForRepo = giftedForRepo;
    }

    @FXML
    public void search() {
        digital = repo.findOne(Long.parseLong(id.getText()));
        if (digital != null) {
            MenuController.window.close();
            ChangeScene.init("/fxml/digital/update/updateDigital.fxml", "Digitale Drager updaten!");
            VBox vBox = new VBox();
            vBox.setAlignment(Pos.CENTER);
            HBox hb1 = GetHbox.get("Titel:");
            SetTextField.set(hb1, digital.getTitle());
            HBox.setMargin(hb1, new Insets(100, 0, 0, 0));
            vBox.getChildren().add(hb1);

            String[] subTitles = digital.getSubtitles().split("\n");
            HBox hBox = GetHbox.get("Ondertitel:");
            for (String s : subTitles) {
                SetTextField.set(hBox, s);
            }
            vBox.getChildren().add(hBox);

            String[] authors = digital.getAuthors().split("\n");
            HBox hb3 = GetHbox.get("Auteurs:");
            for (String author : authors) {
                SetTextField.set(hb3, author);
            }
            vBox.getChildren().add(hb3);

            String[] topics = digital.getTopics().split("\n");
            HBox hb2 = GetHbox.get("Onderwerpen:");
            for (String topic : topics) {
                SetTextField.set(hb2, topic);
            }
            vBox.getChildren().add(hb2);

            HBox hb4 = GetHbox.get("Uitgeverij:");
            SetTextField.set(hb4, digital.getPublisher());
            Label label5 = new Label("Jaar van Uitgave:");
            hb4.getChildren().add(label5);
            HBox.setMargin(label5, new Insets(0, 20, 30, 50));
            SetTextField.set(hb4, "" + digital.getYearPublished());
            Label label6 = new Label("Aantal Pagina's:");
            hb4.getChildren().add(label6);
            HBox.setMargin(label6, new Insets(0, 20, 30, 50));
            vBox.getChildren().add(hb4);

            if (digital.isDonated()) {
                gifted = giftedRepo.findByDigital(digital);
                HBox hb7 = GetHbox.get("Naam Gifter:");
                TextField textField7 = new TextField();
                textField7.setAlignment(Pos.CENTER);
                textField7.setText(gifted.getName());
                textField7.textProperty().addListener((observable, oldValue, newValue) -> gifted.setName(newValue));
                hb7.getChildren().add(textField7);
                HBox.setMargin(textField7, new Insets(0, 0, 30, 0));
                vBox.getChildren().add(hb7);

                HBox hb8 = GetHbox.get("Datum gift:");
                DatePicker date = new DatePicker();
                date.setValue(gifted.getGiftedOn());
                date.valueProperty().addListener((observable, oldValue, newValue) -> gifted.setGiftedOn(date.getValue()));
                hb8.getChildren().add(date);
                HBox.setMargin(date, new Insets(0, 0, 30, 0));
                vBox.getChildren().add(hb8);
            }

            if (digital.getBoughtOn() != null) {
              vBox = UpdateDate.update(digital, vBox);
            }

            if (digital.isTraded()) {
                giftedFor = giftedForRepo.findByDigitalId(digital.getId());
                HBox hb10 = GetHbox.get("ContractNummer:");
                TextField textField10 = new TextField();
                textField10.setAlignment(Pos.CENTER);
                textField10.setText(giftedFor.getContractNr());
                textField10.textProperty().addListener((observable, oldValue, newValue) -> giftedFor.setContractNr(newValue));
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


    private void update() {
        if (digital.isDonated()) {
            giftedRepo.saveAndFlush(gifted);
        }
        if (digital.getBoughtOn() != null) {
            repo.saveAndFlush(digital);
        }
        if (digital.isTraded()) {
            giftedForRepo.saveAndFlush(giftedFor);
        }
        ChangeScene.init("/fxml/home.fxml", "KOKW - Het Verleden Draait Altijd Mee!");
    }
}
