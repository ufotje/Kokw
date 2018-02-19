package be.kokw.controllers.members.update;

import be.kokw.bean.Member;
import be.kokw.controllers.MenuController;
import be.kokw.repositories.MemberRepo;
import be.kokw.utility.autocomplete.TextFieldsMembers;
import be.kokw.utility.sceneControl.ChangeScene;
import be.kokw.utility.validation.Validation;
import be.kokw.utility.validation.Warning;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class UpdateMember {
    @FXML
    private TextField firstName, lastName, street, houseNr, zip, city, email;
    @FXML
    private RadioButton board, anal, payed;
    @FXML
    private DatePicker bDay;
    private MemberRepo repo;
    private Member member;

    @Autowired
    private void setRepo(@Qualifier("memberRepo") MemberRepo repo) {
        this.repo = repo;
    }

    public void initialize() {
        TextFieldsMembers.autoCompleteAll(repo.findAll(), firstName, lastName, street, city);
    }

    @FXML
    public void search() throws Exception {
        member = repo.findByFirstNameAndLastName(firstName.getText(), lastName.getText());
        if (member != null) {
            MenuController.window.close();
            ChangeScene.init("/fxml/members/update/updateMember.fxml", "Lid updaten!");
            firstName.setText(member.getFirstName());
            lastName.setText(member.getLastName());
            street.setText(member.getStreet());
            houseNr.setText("" + member.getHouseNr());
            zip.setText("" + member.getZip());
            city.setText(member.getCity());
            email.setText(member.getEmail());
            LocalDate date = member.getbDay();
            String text = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            bDay.getEditor().setText(text);

            board.setSelected(member.isBoard());
            anal.setSelected(member.isAnal());
            payed.setSelected(member.isPayed());
        } else {
            String alert = "Het lid '" + firstName.getText() + " " + lastName.getText() + "' werd niet terug gevonden";
            Warning.alert("No Member to update found!", alert);
            ChangeScene.init("/fxml/home.fxml", "KOKW - Het verleden draait altijd mee!");
        }
    }

    @FXML
    private void update() {
        if (valid()) {
            member.setAnal(anal.isSelected());
            member.setbDay(bDay.getValue());
            member.setBoard(board.isSelected());
            member.setCity(city.getText());
            member.setEmail(email.getText());
            member.setFirstName(firstName.getText());
            member.setHouseNr(Integer.parseInt(houseNr.getText()));
            member.setLastName(lastName.getText());
            member.setPayed(payed.isSelected());
            member.setZip(Integer.parseInt(zip.getText()));
            member.setStreet(street.getText());
            repo.save(member);
            Warning.alert("Member Updated", "Het lid '" + firstName.getText() + " " + lastName.getText() + " werd succesvol upgedate!");
            ChangeScene.init("/fxml/home.fxml", "KOKW - De wereld draait door!");
        }
    }

    private boolean valid() {
        boolean validated = false;
        if (Validation.validate("Voornaam:", firstName.getText(), "[a-zA-Z]+") &&
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
}
