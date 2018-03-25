package be.kokw.controllers.digital.delete;

import be.kokw.bean.Copies;
import be.kokw.bean.Derated;
import be.kokw.bean.digital.Digital;
import be.kokw.controllers.MenuController;
import be.kokw.repositories.DerateRepo;
import be.kokw.repositories.books.*;
import be.kokw.repositories.digital.DigitalDonateRepo;
import be.kokw.repositories.digital.DigitalRepo;
import be.kokw.repositories.digital.DigitalTradeRepo;
import be.kokw.utility.sceneControl.ChangeScene;
import be.kokw.utility.validation.Warning;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Created By Demesmaecker Daniel
 */

@Component
public class DerateDigital {
    @FXML
    private TextField id;
    @FXML
    private ChoiceBox<String> destination;
    private DigitalRepo repo;
    private DerateRepo derateRepo;
    private DigitalDonateRepo donateRepo;
    private DigitalTradeRepo tradeRepo;
    private CopyRepo copyRepo;

    @Autowired
    private void setDigitalRepo(@Qualifier("digitalRepo") DigitalRepo repo) {
        this.repo = repo;
    }

    @Autowired
    private void setDerateRepo(@Qualifier("derateRepo") DerateRepo derateRepo) {
        this.derateRepo = derateRepo;
    }

    @Autowired
    private void setDonateRepo(@Qualifier("digiDonateRepo") DigitalDonateRepo repo) {
        donateRepo = repo;
    }

    @Autowired
    private void setTradedRepo(@Qualifier("digiTradeRepo") DigitalTradeRepo tradeRepo) {
        this.tradeRepo = tradeRepo;
    }

    @Autowired
    private void setCopyRepo(@Qualifier("copyRepo") CopyRepo copyRepo){
        this.copyRepo = copyRepo;
    }

    /**
     * Sets the destination choicebox
     */
    public void initialize() {
        ObservableList<String> list = FXCollections.observableArrayList("Vernietigd", "Verkocht", "Weggeschonken");
        destination.setItems(list);
        destination.setValue("Vernietigd");
    }

    @FXML
    private void derate() {
        Digital d = repo.findOne(Integer.parseInt(id.getText()));
        if (d != null) {
            Copies copy = copyRepo.findByTitleAndType(d.getTitle(), "Digitale Drager");
            StringBuilder sb = new StringBuilder();
            setCopies(copy, d, sb);
            checkOrigin(d, sb);
         } else {
            Warning.alert("No item found", "de digitale drager werd niet terug gevonden.");
        }
        ChangeScene.init("/fxml/home.fxml", "KOKW - Het verleden draait altijd mee!");
    }

    /**
     * Checks for available copies and update the value
     * Promopts user witj nr of available copies
     * @param copy Copies
     * @param d Digital
     * @param sb StringBuilder
     */
    private void setCopies(Copies copy, Digital d, StringBuilder sb){
        if (copy.getNrOfCopies() > 0) {
            copy.setNrOfCopies(copy.getNrOfCopies() - 1);
            copyRepo.save(copy);
            sb.append("Er zijn nog ").append(copy.getNrOfCopies()).append(" kopieën van de digitale drager: '")
                    .append(d.getTitle()).append("' in de bibliotheek van de kokw.");
        } else {
            repo.delete(d);
            sb.append("Er zijn geen kopieën meer van de digitale drager '").append(d.getTitle()).append("' in de bibliotheek van de kokw");
        }
    }

    /**
     * Checks if a carrier is bought, donated or traded
     * @param d Digital
     * @param sb StringBuilder
     */
    private void checkOrigin(Digital d, StringBuilder sb){
        Derated deratedDigital = new Derated(d, LocalDate.now(), destination.getValue(), d.getDepot(), d.getTitle(), d.getAuthors());
        if(d.isDonated()){
            donateRepo.deleteByDigitalId(Integer.parseInt(id.getText()));
        }
        if(d.isTraded()){
            tradeRepo.deleteByDigitalId(Integer.parseInt(id.getText()));
        }
        derateRepo.save(deratedDigital);
        MenuController.window.close();
        Warning.alert("Derating succesvol", "de digitale drager: '" + d.getTitle() + "' werd met succes gedeclasseerd.\n" + sb.toString());
    }
}