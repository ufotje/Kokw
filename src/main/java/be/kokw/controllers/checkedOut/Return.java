package be.kokw.controllers.checkedOut;

import be.kokw.bean.CheckedOut;
import be.kokw.bean.Copies;
import be.kokw.controllers.MenuController;
import be.kokw.repositories.CheckOutRepo;
import be.kokw.repositories.books.CopyRepo;
import be.kokw.utility.validation.Validation;
import be.kokw.utility.validation.Warning;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * Created by Demesmaecker Daniel
 */

@Component
public class Return {
    @FXML
    private TextField title;
    @FXML
    private TextField fullName;
    private CheckOutRepo repo;
    private CopyRepo copyRepo;

    @Autowired
    private void setRepo(@Qualifier("checkOutRepo") CheckOutRepo repo) {
        this.repo = repo;
    }

    @Autowired
    private void setCopyRepo(@Qualifier("copyRepo") CopyRepo copyRepo) {
        this.copyRepo = copyRepo;
    }

    /**
     * Returns a checked out item to the library
     */
    public void returnItem() {
        if (Validation.validate("Naam", fullName.getText(), "[A-Za-z ]+")) {
            if (!title.getText().isEmpty() && title.getText() != null) {
                MenuController.window.close();
                int result = repo.returnItem(title.getText(), fullName.getText());
                if (result > 0) {
                    CheckedOut record = repo.findByTitleAndFullName(title.getText(), fullName.getText());
                    Warning.alert("Item Returned!", "Het/de " + record.getType() + ": " + title.getText() + "' werd succesvol terug gebracht door '" + fullName.getText() + "'!");
                    setCopies(record);
                    calculateFine(record);
                    repo.delete(record);
                } else {
                    Warning.alert("Error!", "Het corresponderende record werd niet terug gevonden.\nControleer aub uw invoer.\nDenk er ook aan dat u eerst de voornaam en dan de achternaam invult, gescheiden door een spatie.");
                }
            } else {
                Warning.alert("Invalid title", "Voen een titel in aub!");
            }
        } else {
            Warning.alert("Invalid Name!", "De voor- en Achternaam kan enkel uit grote of kleine letters bestaan gescheiden door een spatie!");
        }
    }

    /**
     * Increments the nr of available copies by one
     *
     * @param record CheckedOut
     */
    private void setCopies(CheckedOut record) {
        Copies copy = copyRepo.findByTitleAndType(record.getTitle(), record.getType());
        if (copy.getNrOfCopies() == 0) {
            copy.setNrOfCopies(1);
        } else {
            copy.setNrOfCopies(copy.getNrOfCopies() + 1);
        }
        copyRepo.save(copy);
    }

    /**
     * Checks if a book is overdue and calculates the fine(nr of days overdue * 0.25)
     *
     * @param record CheckedOut
     */
    private void calculateFine(CheckedOut record) {
        if (record.getReturnDate().isBefore(LocalDate.now())) {
            Long daysOverdue = DAYS.between(record.getReturnDate(), LocalDate.now());
            Double fine = daysOverdue * 0.25;
            Warning.alert("Book Overdue!", "Het boek werd te laat binnen gebracht!\nEr dient een boete van " + fine + "€ betaald te worden.");
        }
    }
}
