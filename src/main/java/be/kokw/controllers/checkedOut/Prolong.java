package be.kokw.controllers.checkedOut;

import be.kokw.bean.CheckedOut;
import be.kokw.repositories.CheckOutRepo;
import be.kokw.utility.validation.Validation;
import be.kokw.utility.validation.Warning;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.persistence.NoResultException;
import java.time.LocalDate;

/**
 * Created By Demesmaecker Daniel
 */

@Component
public class Prolong {
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField title;
    private CheckOutRepo repo;
    private LocalDate returnDate;

    @Autowired
    private void setRepo(@Qualifier("checkOutRepo") CheckOutRepo repo) {
        this.repo = repo;
    }

    /**
     * Enables to prolong a checked out item with three weeks
     */
    @FXML
    private void prolong() {
        if (Validation.validate("Voornaam", firstName.getText(), "[A-Za-z]+")) {
            if (Validation.validate("Achternaam", lastName.getText(), "[A-Za-z]+")) {
                if (!title.getText().isEmpty() && title.getText() != null) {
                    String fullName = firstName.getText() + " " + lastName.getText();
                    try {
                        CheckedOut record = repo.getOne(title.getText(), fullName);
                        LocalDate date = record.getReturnDate();
                        returnDate = date.plusWeeks(3);
                    } catch (NoResultException e) {
                        Warning.alert("No Result Exception", "Het corresponderende record werd niet teruggevonden");
                    }
                    int result = repo.prolong(title.getText(), fullName, returnDate);
                    if (result > 0) {
                        StringBuilder sb = new StringBuilder(returnDate.toString());
                        Warning.alert("Succes", "De uitleenbeurt voor '" + title.getText() + "' werd succesvol verlengd voor '" + fullName + "' tot " + sb.reverse().toString() + "!");
                    } else {
                        Warning.alert("Error", "Er ging iets fout!\nControleer uw invoer!");
                    }
                } else {
                    Warning.alert("Invalid Title", "Voeg een titel toe aub");
                }
            } else {
                Warning.alert("Invalid LastName", "De achternaam kan ennkel uit grote of kleine letters bestaan");
            }
        } else {
            Warning.alert("Invalid Firstname", "De voornaam kan enkel uit grote of kleine letters bestaan");
        }
    }
}