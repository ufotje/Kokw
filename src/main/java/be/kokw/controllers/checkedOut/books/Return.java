package be.kokw.controllers.checkedOut.books;

import be.kokw.bean.CheckedOut;
import be.kokw.controllers.MenuController;
import be.kokw.repositories.books.CheckOutRepo;
import be.kokw.utility.validation.Validation;
import be.kokw.utility.validation.Warning;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

@Component
public class Return {
    @FXML
    private TextField title;
    @FXML
    private TextField fullName;
    private CheckOutRepo repo;

    @Autowired
    private void setRepo(@Qualifier("checkOutRepo") CheckOutRepo repo) {
        this.repo = repo;
    }

    public void returnBook() {
        if (Validation.validate("Naam", fullName.getText(), "[A-Za-z ]+")) {
            if (!title.getText().isEmpty() && title.getText() != null) {
                MenuController.window.close();
                int result = repo.returnBook(title.getText(),fullName.getText());
                if(result > 0){
                    Warning.alert("Book Returned!", "Het boek '" + title.getText() + "' werd succesvol terug gebracht door '" + fullName.getText() + "'!");
                    CheckedOut record = repo.getOne(title.getText(), fullName.getText());
                    LocalDate returnDate = record.getReturnDate();
                    LocalDate today =
                            LocalDate.now();
                    if (returnDate.isBefore(today)){
                        Long daysOverdue = DAYS.between(returnDate,today);
                        Double fine = daysOverdue *0.25;
                        Warning.alert("Book Overdue!","Het boek werd te laat binnen gebracht!\nEr dient een boete van " + fine + "€ betaald te worden.");
                    }
                }else{
                    Warning.alert("Error!", "Het corresponderende record werd niet terug gevonden.\nControleer aub uw invoer.\nDenk er ook aan dat u eerst de voornaam en dan de achternaam invult, gescheiden door een spatie.");
                }
            }else{
                Warning.alert("Invalid title", "Voen een titel in aub!");
            }
        }else{
            Warning.alert("Invalid Name!", "De voor- en Achternaam kan enkel uit grote of kleine letters bestaan gescheiden door een spatie!");
        }
    }
}
