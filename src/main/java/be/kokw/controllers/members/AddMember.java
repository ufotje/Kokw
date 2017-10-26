package be.kokw.controllers.members;

import be.kokw.bean.Member;
import be.kokw.repositories.members.MemberRepo;
import be.kokw.utility.Validation;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


/**
 * Created by ufotje on 21/10/2017.
 * ControllerClass to save (a) new Member(s)
 */

@Component
public class AddMember {
    private MemberRepo repo;

    @FXML
    private TextField firstName, lastName, street, houseNr, zip, city, email;

    @FXML
    private RadioButton board;

    @FXML
    private DatePicker bDay;

    public AddMember() throws Exception {

    }

    @Autowired
    public void setMemberRepo(@Qualifier("memberRepo") MemberRepo memberRepo) {
        repo = memberRepo;
    }

    @FXML
    private void save() {
        if (Validation.validate("Voornaam:", firstName.getText(), "[a-zA-Z]+") &&
                Validation.validate("Achternaam:", lastName.getText(), "[a-zA-Z]+")
                && Validation.validate("Straatnaam:", street.getText(), "[a-zA-Z]+")
                && Validation.validate("Huisnummer:", houseNr.getText(), "[0-9]")
                && Validation.validate("Postcode:", zip.getText(), "[0-9]")
                && Validation.validate("Stad:", city.getText(), "[a-zA-Z]+")
                && Validation.validate("Email:", email.getText(), "[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+")
                && Validation.emptyValidation("Geboortedatum:", bDay.getEditor().getText().isEmpty())){
            Member member = new Member(firstName.getText(), lastName.getText(), street.getText(), Integer.parseInt(houseNr.getText()), Integer.parseInt(zip.getText()), city.getText(), email.getText(), bDay.getValue(), board.isSelected());
            repo.save(member);
        }
    }

    @FXML
    private void addMore() {
    }
}
