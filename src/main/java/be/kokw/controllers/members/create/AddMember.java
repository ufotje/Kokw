package be.kokw.controllers.members.create;

import be.fedict.commons.eid.client.BeIDCard;
import be.fedict.commons.eid.client.BeIDCards;
import be.fedict.commons.eid.client.CancelledException;
import be.fedict.commons.eid.client.FileType;
import be.fedict.commons.eid.consumer.Identity;
import be.fedict.commons.eid.consumer.tlv.TlvParser;
import be.fedict.commons.eid.consumer.Address;
import be.kokw.bean.Member;
import be.kokw.repositories.MemberRepo;
import be.kokw.utility.ChangeScene;
import be.kokw.utility.Validation;
import be.kokw.utility.Warning;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.smartcardio.CardException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by ufotje on 21/10/2017.
 * ControllerClass to save (a) new Member(s)
 */

@Component
public class AddMember {
    private MemberRepo repo;
    private List<String> members = new ArrayList<>();

    @FXML
    private TextField firstName, lastName, street, houseNr, zip, city, email;

    @FXML
    private RadioButton board, anal, payed;

    @FXML
    private DatePicker bDay;

    @Autowired
    public void setMemberRepo(@Qualifier("memberRepo") MemberRepo memberRepo) {
        repo = memberRepo;
    }

    @FXML
    private void save() throws Exception {
        if (valid()) {
            Member member = new Member(firstName.getText(), lastName.getText(), street.getText(), Integer.parseInt(houseNr.getText()), Integer.parseInt(zip.getText()), city.getText(), email.getText(), bDay.getValue(), board.isSelected(), payed.isSelected(), anal.isSelected());
            repo.save(member);
            members.add(firstName.getText() + " " + lastName.getText());
            StringBuilder names = new StringBuilder("The member(s) with name: '");
            for (String s : members) {
                names.append(s);
                names.append("', '");
            }
            names.append(" has been successfully saved!");
            Warning.alert("Member saved!", names.toString());
            ChangeScene.init("/fxml/home.fxml", "KOKW - Het Verleden Draait Door!");
        }
    }

    @FXML
    private void addMore() {
        if (valid()) {
            Member member = new Member(firstName.getText(), lastName.getText(), street.getText(), Integer.parseInt(houseNr.getText()), Integer.parseInt(zip.getText()), city.getText(), email.getText(), bDay.getValue(), board.isSelected(), payed.isSelected(), anal.isSelected());
            members.add(firstName.getText() + " " + lastName.getText());
            repo.save(member);
        }
        clearFields();
    }

    private boolean valid() {
        boolean validated = false;
        if (Validation.validate("Voornaam:", firstName.getText(), "[a-zA-Z ]+") &&
                Validation.validate("Achternaam:", lastName.getText(), "[a-zA-Z ]+")
                && Validation.validate("Straatnaam:", street.getText(), "[a-zA-Z]+")
                && Validation.validate("Huisnummer:", houseNr.getText(), "[0-9]+")
                && Validation.validate("Postcode:", zip.getText(), "[0-9]+[0-9]+[0-9]+[0-9]")
                && Validation.validate("Stad:", city.getText(), "[a-zA-Z-]+")
                && Validation.validate("Email:", email.getText(), "[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+")
                && Validation.emptyValidation("Geboortedatum:", bDay.getEditor().getText().isEmpty())) {
            validated = true;
        }
        return validated;
    }

    @FXML
    private void eId() throws InterruptedException, IOException {
        final BeIDCards beIDCards = new BeIDCards();
        final Set<BeIDCard> cards = beIDCards.getAllBeIDCards();
        if (cards.size() > 0) {
            try {
                final BeIDCard card = beIDCards.getOneBeIDCard();
                try {
                    final byte[] idData = card.readFile(FileType.Identity);
                    final byte[] addressFile = card.readFile(FileType.Address);
                    final Identity id = TlvParser.parse(idData, Identity.class);
                    final Address address = TlvParser.parse(addressFile, Address.class);
                    String streetAndNr = address.getStreetAndNumber();
                    String[] splitted = streetAndNr.split(" ");
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < splitted.length - 1; i++) {
                        sb.append(splitted[i]);
                        sb.append(" ");
                    }
                    street.setText(sb.toString());
                    int i = splitted.length - 1;
                    houseNr.setText(splitted[i]);
                    zip.setText(address.getZip());
                    city.setText(address.getMunicipality());
                    String[] nameSplit = id.firstName.split(" ");
                    firstName.setText(nameSplit[0]);
                    lastName.setText(id.name);
                    LocalDate date = id.dateOfBirth.toZonedDateTime().toLocalDate();
                    bDay.setValue(date);
                } catch (final CardException cex) {
                    cex.printStackTrace();
                }
            } catch (final CancelledException cex) {
                System.out.println("Cancelled By User");
            }
        } else {
            Warning.alert("No E-ID Detected!", "Er werd geen geldige identiteitskaart gevonden.\nSteek de e-id in de kaartlezer en probeer opnieuw.");
        }
    }


    private void clearFields() {
        firstName.clear();
        lastName.clear();
        street.clear();
        houseNr.clear();
        zip.clear();
        city.clear();
        email.clear();
        bDay.getEditor().clear();
        board.setSelected(false);
        payed.setSelected(false);
        anal.setSelected(false);
    }
}
