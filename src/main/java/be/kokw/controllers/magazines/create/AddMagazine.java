package be.kokw.controllers.magazines.create;

import be.kokw.bean.Magazine;
import be.kokw.repositories.MagazineRepo;
import be.kokw.utility.ChangeScene;
import be.kokw.utility.Validation;
import be.kokw.utility.Warning;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class AddMagazine {
    @FXML
    private TextField name;
    @FXML
    private TextField issn;
    @FXML
    private TextField copies;
    @FXML
    private TextField pages;
    @FXML
    private TextField publisher;
    @FXML
    private TextField period;
    @FXML
    private TextField year;
    @FXML
    private TextField nr;
    @FXML
    private TextField theme;
    @FXML
    private RadioButton illustrated;
    @FXML
    private RadioButton subscription;
    @FXML
    private RadioButton traded;
    private MagazineRepo repo;

    @Autowired
    private void setRepo(@Qualifier("magazineRepo") MagazineRepo repo) {
        this.repo = repo;
    }

    @FXML
    private void save() {
        if (saveMag()) {
            try {
                ChangeScene.init("/fxml/home.fxml", "KOKW - Het verleden draait altijd mee!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @FXML
    private void more() {
        if (saveMag()) {
            clear();
        }
    }

    private boolean saveMag() {
        boolean check = false;
        if (validated()) {
            Magazine magazine = new Magazine(issn.getText(), Integer.parseInt(copies.getText()), name.getText(), publisher.getText(), period.getText(), year.getText(), Integer.parseInt(nr.getText()), Integer.parseInt(pages.getText()), theme.getText());
            if (traded.isSelected() && subscription.isSelected()) {
                Warning.alert("Selection error", "Een magazine kan niet zowel geruild als deel van een abonnement zijn.\nGelieve 1 iets te selecteren");
            } else if (traded.isSelected()) {
                magazine.setTraded(true);
            } else if (subscription.isSelected()) {
                magazine.setSubscribed(true);
            } else if (!illustrated.isSelected()) {
                magazine.setIllustrated(false);
            }
            Magazine mag = repo.save(magazine);
            check = true;
            if (mag != null) {
                Warning.alert("Magazine saved", "Het magazine '" + name.getText() + "' werd succesvol opgeslaan.");
            } else {
                Warning.alert("Saving error", "Er ging iets fout tijdens het opslaan van het magazine '" + name.getText() + "'.");
            }
        }
        return check;
    }

    private void clear() {
        name.clear();
        theme.clear();
        nr.clear();
        year.clear();
        period.clear();
        publisher.clear();
        pages.clear();
        copies.clear();
        issn.clear();
        illustrated.setSelected(true);
        traded.setSelected(false);
        subscription.setSelected(false);
    }

    private boolean validated() {
        boolean validation = false;
        if (Validation.validate("name", name.getText(), "[a-zA-Z \\-]+")) {
            if (Validation.validate("theme", theme.getText(), "[a-zA-Z \\-]+")) {
                if (Validation.validate("nr", nr.getText(), "[0-9]+")) {
                    if (Validation.validate("year", year.getText(), "[0-9]+")) {
                        if (Validation.validate("period", period.getText(), "[a-zA-Z \\-]+")) {
                            if (Validation.validate("publisher", publisher.getText(), "[a-zA-Z \\-\\!\\.]+")) {
                                if (Validation.validate("pages", pages.getText(), "[0-9]+")) {
                                    if (Validation.validate("copies", copies.getText(), "[0-9]+")) {
                                        validation = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return validation;
    }
}
