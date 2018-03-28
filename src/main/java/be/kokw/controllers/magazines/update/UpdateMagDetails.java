package be.kokw.controllers.magazines.update;

import be.kokw.bean.magazines.Magazine;
import be.kokw.bean.magazines.Subscribed;
import be.kokw.bean.magazines.Trade;
import be.kokw.controllers.MenuController;
import be.kokw.repositories.magazines.MagazineRepo;
import be.kokw.repositories.magazines.SubscribedRepo;
import be.kokw.repositories.magazines.TradeRepo;
import be.kokw.utility.sceneControl.ChangeScene;
import be.kokw.utility.sceneControl.NewStage;
import be.kokw.utility.validation.Warning;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class UpdateMagDetails {
    @FXML
    private TextField id;
    @FXML
    private TextField detailsId;
    @FXML
    private TextField magId;
    @FXML
    private TextField title;
    @FXML
    private TextField expected;
    @FXML
    private TextField mail;
    @FXML
    private TextField org;
    @FXML
    private TextField telephone;
    @FXML
    private TextArea address;
    private MagazineRepo magazineRepo;
    private TradeRepo tradeRepo;
    private SubscribedRepo subscribedRepo;
    private Trade details;
    private Subscribed subscribed;
    private Stage stage;

    @Autowired
    private void setMagazineRepo(@Qualifier("magRepo") MagazineRepo magazineRepo) {
        this.magazineRepo = magazineRepo;
    }

    @Autowired
    private void setTradeRepo(@Qualifier("tradeRepo") TradeRepo tradeRepo) {
        this.tradeRepo = tradeRepo;
    }

    @Autowired
    private void setSubscribedRepo(@Qualifier("subscribedRepo") SubscribedRepo subscribedRepo) {
        this.subscribedRepo = subscribedRepo;
    }

    @FXML
    public void search() {
        Magazine mag = magazineRepo.findOne(Integer.parseInt(id.getText()));
        MenuController.window.close();
        if (mag != null) {
            if (mag.isTraded()) {
                details = tradeRepo.findByMag(mag);
                if (details != null) {
                    stage = NewStage.getStage("Update Magazine", "/fxml/magazines/update/updateMagDetails.fxml");
                    stage.show();
                    setTradeDetails();
                } else {
                    Warning.alert("No TradeDetails Found", "Er werden geen ruildetails gevonden voor het magazine "
                            + mag.getName() + " met als titel: " + mag.getTitle());
                }
            } else if (mag.isSubscribed()) {
                subscribed = subscribedRepo.findByMag(mag);
                if (subscribed != null) {
                    stage = NewStage.getStage("Update Magazine", "/fxml/magazines/update/updateMagDetails.fxml");
                    stage.show();
                    setSubscribedDetails();
                } else {
                    Warning.alert("No SubscriptionDetails Found", "Er werden geen abonnementsdetails gevonden voor het magazine "
                            + mag.getName() + " met als titel: " + mag.getTitle());
                }
            } else {
                Warning.alert("Not updatable!", "Het magazine maakt geen onderdeel uit van een abonnement\n" +
                        "en werd ook niet geruild met een andere organisatie, daarom is het niet updatebaar.");
            }
        } else {
            Warning.alert("Magazine not found!", "Het magazine met id: " + id.getText() + " werd niet gevonden.");
        }


    }

    @FXML
    private void update() {
        if (details != null) {
            details.setContactInfo(address.getText());
            details.setEmail(mail.getText());
            details.setNameOrg(org.getText());
            details.setExpected(Integer.parseInt(expected.getText()));
            details.setTel(telephone.getText());
            Trade trade = tradeRepo.save(details);
            if (trade != null) {
                Warning.alert("Updated!", "Het magazine " + trade.getMag().getName() + " met als titel: " + trade.getMag().getTitle() + " werd succesvol aangepast.");
            } else {
                Warning.alert("Error!", "Er ging iets fout tijdens het updaten van het magazine.");
            }
        } else {
            subscribed.setContactInfo(address.getText());
            subscribed.setEmail(mail.getText());
            subscribed.setNameMag(org.getText());
            subscribed.setExpected(Integer.parseInt(expected.getText()));
            subscribed.setTel(telephone.getText());
            Subscribed result = subscribedRepo.save(subscribed);
            if (result != null) {
                Warning.alert("Updated!", "Het magazine " + subscribed.getMag().getName() + " met als titel: " + subscribed.getMag().getTitle() + " werd succesvol aangepast.");
            } else {
                Warning.alert("Error!", "Er ging iets fout tijdens het updaten van het magazine.");
            }
        }
        stage.close();
        ChangeScene.init("/fxml/home.fxml", "KOKW - Het Verleden Draait Altijd Mee.");
    }

    private void setTradeDetails() {
        detailsId.setText("" + details.getId());
        detailsId.setEditable(false);
        detailsId.setOnMouseClicked(mouseEvent -> Warning.alert("Unchangeable Field", "u kunt de detailsId niet aanpassen"));
        magId.setText("" + id.getText());
        magId.setEditable(false);
        magId.setOnMouseClicked(mouseEvent -> Warning.alert("Unchangeable Field", "De id van het magazine kan niet aangepast worden."));
        expected.setText("" + details.getExpected());
        title.setText(details.getMag().getTitle());
        title.setEditable(false);
        title.setOnMouseClicked(mouseEvent -> Warning.alert("Unchangeable Field", "Sorry, je kunt de titel van het magazine niet aanpassen."));
        org.setText(details.getNameOrg());
        address.setText(details.getContactInfo());
        mail.setText(details.getEmail());
        telephone.setText(details.getTel());
    }

    private void setSubscribedDetails() {
        detailsId.setText("" + subscribed.getId());
        detailsId.setEditable(false);
        detailsId.setOnMouseClicked(mouseEvent -> Warning.alert("Unchangeable Field", "u kunt de detailsId niet aanpassen"));
        magId.setText("" + id.getText());
        magId.setEditable(false);
        magId.setOnMouseClicked(mouseEvent -> Warning.alert("Unchangeable Field", "De id van het magazine kan niet aangepast worden."));
        expected.setText("" + subscribed.getExpected());
        title.setText(subscribed.getMag().getTitle());
        title.setEditable(false);
        title.setOnMouseClicked(mouseEvent -> Warning.alert("Unchangeable Field", "Sorry, je kunt de titel van het magazine niet aanpassen."));
        org.setText(subscribed.getPublisher());
        address.setText(subscribed.getContactInfo());
        mail.setText(subscribed.getEmail());
        telephone.setText(subscribed.getTel());
    }
}
