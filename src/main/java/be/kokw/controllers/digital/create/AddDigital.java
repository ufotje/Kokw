package be.kokw.controllers.digital.create;

import be.kokw.bean.Copies;
import be.kokw.bean.digital.Digital;
import be.kokw.bean.digital.DigitalDonated;
import be.kokw.bean.digital.DigitalTraded;
import be.kokw.repositories.books.CopyRepo;
import be.kokw.repositories.digital.DigitalDonateRepo;
import be.kokw.repositories.digital.DigitalRepo;
import be.kokw.repositories.digital.DigitalTradeRepo;
import be.kokw.utility.autocomplete.DigiTextFields;
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
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Demesmaecker Daniel on 20/10/2017.
 * This class is used to save a Digtal Carrier
 */

@Component
public class AddDigital {
    @FXML
    private TextField fullName, contractNr, firstName, lastName, title, nameAuthor, publisher, subTitle, year, depot;
    @FXML
    private DatePicker date, boughtOn;
    @FXML
    private CheckBox bought, gifted, giftedFor;
    @FXML
    private ChoiceBox<String> topic;
    @FXML
    private ChoiceBox<Integer> volume;
    @FXML
    DatePicker contractDate;
    private Stage window;
    private DigitalRepo digiRepo;
    private StringBuilder authors = new StringBuilder();
    private List<String> digitalList = new ArrayList<>();
    private StringBuilder subTitles = new StringBuilder();
    private StringBuilder topics = new StringBuilder();
    private File file;
    private Digital digital;
    private DigitalDonateRepo donateRepo;
    private DigitalTradeRepo tradeRepo;
    private CopyRepo copyRepo;

    @Autowired
    private void setDigiRepo(@Qualifier("digitalRepo") DigitalRepo repo) {
        digiRepo = repo;
    }

    @Autowired
    private void setDonateRepo(@Qualifier("digiDonateRepo") DigitalDonateRepo donateRepo) {
        this.donateRepo = donateRepo;
    }

    @Autowired
    private void setTradeRepo(@Qualifier("digiTradeRepo") DigitalTradeRepo tradeRepo) {
        this.tradeRepo = tradeRepo;
    }

    @Autowired
    private void setCopyRepo(@Qualifier("copyRepo") CopyRepo copyRepo) {
        this.copyRepo = copyRepo;
    }

    /**
     * The initialize method is used to fill the choiceboxes and to set an autocomplete on the textfields
     */
    public void initialize() {
        ObservableList<String> topics = FXCollections.observableArrayList("Wereld Oorlog 1", "Wereld Oorlog 2", "MiddelEeuwen", "Gulden Sporenslag", "Brugse Metten");
        topic.setItems(topics);
        ObservableList<Integer> volumes = FXCollections.observableArrayList(1, 2, 3, 4, 5);
        volume.setItems(volumes);
        volume.setValue(1);
        DigiTextFields.autocomplete(digiRepo, title, nameAuthor, subTitle, publisher);
    }

    /**
     * This method checks in which way the carrier is acquired and if there's not more then one way selected
     * After the check is performed the correct detail window is opened.
     */
    @FXML
    public void save() {
        if (validated()) {
            digital = new Digital(depot.getText(), title.getText(), subTitles.toString(), volume.getValue(), publisher.getText(), Integer.parseInt(year.getText()), authors.toString(), topics.append(topic.getValue()).toString());
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
                digital.setDonated(true);
                window = NewStage.getStage("Donated By", "/fxml/digital/create/gifted/donateDetails.fxml");
                window.showAndWait();
            }
            if (bought.isSelected() && !gifted.isSelected() && !giftedFor.isSelected()) {
                window = NewStage.getStage("Bought On", "/fxml/digital/create/bought.fxml");
                window.showAndWait();
            }
            if (giftedFor.isSelected() && !bought.isSelected() && !gifted.isSelected()) {
                digital.setTraded(true);
                window = NewStage.getStage("Traded for", "/fxml/digital/create/gifted/tradeDetails.fxml");
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
        authors = AddAuthor.add(nameAuthor.getText());
        nameAuthor.clear();
    }

    @FXML
    private void addTopic() {
        if (topic.getValue() != null) {
            topics.append(topic.getValue());
            topics.append("\n");
        }
    }
    /**
     * Checks the inputfields of the donatedDetailswindow for incorrect values,
     * Creates an new DigitalDonatedobject and saves the carrier and details to the db.
     */
    @FXML
    private void giftedBy() {
        if (Validation.validate("firstName", firstName.getText(), "[a-zA-Z \\-]+")) {
            if (Validation.validate("lastName", lastName.getText(), "[a-zA-Z \\-]+")) {
                if (Validation.validate("date", date.getValue().toString(), "[0-9\\-]+")) {
                    DigitalDonated gift = new DigitalDonated(firstName.getText() + " " + lastName.getText(), date.getValue(), digital);
                    DigitalDonated d = donateRepo.save(gift);
                    saveCopies();
                    window.close();
                    if (d != null) {
                        Warning.alert("Digtal Carrier saved!", "De digitale drager met titel '" + digital.getTitle() + "' werd succesvol opgeslaan");
                        ChangeScene.init("/fxml/home.fxml", "KOKW - Het Verleden Draait Altijd Mee!");
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

    /**
     * Checks the inputfields of the tradedDetailswindow for incorrect values,
     * Creates an new DigitalTradedobject and saves the carrier and details to the db.
     */
    @FXML
    private void giftedFor() {
        if (Validation.validate("fullName", fullName.getText(), "[a-zA-Z \\-]+")) {
            DigitalTraded giftedFor = new DigitalTraded(fullName.getText(), contractNr.getText(), file, contractDate.getValue(), digital);
            DigitalTraded dt = tradeRepo.save(giftedFor);
            saveCopies();
            window.close();
            if (dt != null) {
                Warning.alert("Digital Carrier saved!", "De digitale drager met '" + digital.getTitle() + "' als titel werd succesvol opgeslaan");
                ChangeScene.init("/fxml/home.fxml", "KOKW - Het Verleden Draait Altijd Mee!");
            } else {
                Warning.alert("Error!", "Er ging iets fout");
            }
        } else {
            Warning.alert("Wrong value", "De contractant is verkeerd ingevuld.");
        }

    }

    /**
     * Checks the inputfields of the BoughtDetailswindow for incorrect values
     * and saves the carrier to the db.
     */
    @FXML
    public void bought() {
        LocalDate boughtDate = boughtOn.getValue();
        digital.setBoughtOn(boughtDate);
        window.close();
        saveDigital(digital);
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

    /**
     * Validates The Inputfields
     * @return
     */
    private boolean validated() {
        boolean valid = false;
        if (Validation.emptyValidation("Titel", title.getText().isEmpty() &&
                Validation.validate("Uitgeverij:", publisher.getText(), "[a-zA-Z]+") &&
                Validation.validate("Jaar van publicatie:", year.getText(), "[0-9999]+") &&
                Validation.validate("depot", depot.getText(), "[a-zA-Z0-9999 -]+"))) {
            valid = true;
        } else {
            Warning.alert("Wrong input", "Verkeerde invoer!\nControleer uw velden aub.");
        }

        return valid;
    }

    /**
     * Checks if a carrier is already in the db, if so it increments the available copies by one,
     * else it creates a new one
     */
    private void saveCopies() {
     /*   Copies copy = copyRepo.findByTitleAndType(digital.getTitle(), "Digitale Drager");
        if (copy != null) {
            copy.setNrOfCopies(copy.getNrOfCopies() + 1);
            copyRepo.save(copy);
        } else {
            Copies c = new Copies(title.getText(), "Digitale Drager", volume.getValue(), digital);
            copyRepo.save(c);
        }*/
    }

    /**
     * Removes the values from the inputfields
     */
    private void clearFields() {
        title.clear();
        nameAuthor.clear();
        depot.clear();
        subTitle.clear();
        giftedFor.setSelected(false);
        gifted.setSelected(false);
        bought.setSelected(false);
        volume.getSelectionModel().clearSelection();
        topic.getSelectionModel().clearSelection();
        publisher.clear();
        year.clear();
    }

    private void saveDigital(Digital digital) {
        digiRepo.save(digital);
        saveCopies();
        digitalList.add(digital.getTitle());
        StringBuilder alert = new StringBuilder("The book(s) with title: ");
        for (String s : digitalList) {
            alert.append("'");
            alert.append(s);
            alert.append("', '");
        }
        alert.append(" has been successfully saved!");
        Warning.alert("Digital Carrier saved!", alert.toString());
        ChangeScene.init("/fxml/menu.fxml", "KOKW - Het Verleden Draait Altijd Mee!");
    }
}
