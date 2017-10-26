package be.kokw.controllers.members;

import be.kokw.bean.Member;
import be.kokw.controllers.MenuController;
import be.kokw.repositories.members.MemberRepo;
import be.kokw.utility.SaveAlert;
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
public class AddMember extends MenuController {
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
        if (valid()){
            Member member = new Member(firstName.getText(), lastName.getText(), street.getText(), Integer.parseInt(houseNr.getText()), Integer.parseInt(zip.getText()), city.getText(), email.getText(), bDay.getValue(), board.isSelected());
            repo.save(member);
            String name = "with name: '" + firstName.getText() + " " + lastName.getText() + "'";
            SaveAlert.saveAlert("member", name);
        }
    }

    @FXML
    private void addMore() {
        if (valid()){
            Member member = new Member(firstName.getText(), lastName.getText(), street.getText(), Integer.parseInt(houseNr.getText()), Integer.parseInt(zip.getText()), city.getText(), email.getText(), bDay.getValue(), board.isSelected());
            repo.save(member);
        }
        clearFields();
    }

    private boolean valid(){
        boolean validated = false;
        if(Validation.validate("Voornaam:", firstName.getText(), "[a-zA-Z]+") &&
                Validation.validate("Achternaam:", lastName.getText(), "[a-zA-Z]+")
                && Validation.validate("Straatnaam:", street.getText(), "[a-zA-Z]+")
                && Validation.validate("Huisnummer:", houseNr.getText(), "[0-1000]")
                && Validation.validate("Postcode:", zip.getText(), "[0-9999]")
                && Validation.validate("Stad:", city.getText(), "[a-zA-Z]+[-]")
                && Validation.validate("Email:", email.getText(), "[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+")
                && Validation.emptyValidation("Geboortedatum:", bDay.getEditor().getText().isEmpty())){
            validated = true;
        }
        return validated;
    }

    private void clearFields(){
        firstName.setText(null);
        firstName.setPromptText(firstName.getPromptText());
        lastName.setText(null);
        lastName.setPromptText(firstName.getPromptText());
        street.setText(null);
        street.setPromptText(street.getPromptText());
        houseNr.setText(null);
        houseNr.setPromptText(houseNr.getPromptText());
        zip.setText(null);
        zip.setPromptText(zip.getPromptText());
        city.setText(null);
        city.setPromptText(city.getPromptText());
        email.setText(null);
        email.setPromptText(email.getPromptText());
        bDay.getEditor().setText(null);
        bDay.getEditor().setPromptText(bDay.getPromptText());
    }
}
