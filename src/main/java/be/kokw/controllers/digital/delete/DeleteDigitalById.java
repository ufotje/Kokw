package be.kokw.controllers.digital.delete;


import be.kokw.bean.Copies;
import be.kokw.bean.digital.Digital;
import be.kokw.controllers.MenuController;
import be.kokw.repositories.books.CopyRepo;
import be.kokw.repositories.digital.DigitalRepo;
import be.kokw.utility.sceneControl.ChangeScene;
import be.kokw.utility.validation.Validation;
import be.kokw.utility.validation.Warning;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by DemesmaeckerDaniel on 2/11/2017.
 * Delete Book By IDClass
 *
 * @deprecated use {@link DerateDigital} instead.
 */
@Component
public class DeleteDigitalById {
    @FXML
    private TextField id;
    private DigitalRepo digitalRepo;
    private CopyRepo copyRepo;

    @Autowired
    private void setDigitalRepo(@Qualifier("digitalRepo") DigitalRepo repo) {
        digitalRepo = repo;
    }

    @Autowired
    private void setCopyRepo(@Qualifier("copyRepo") CopyRepo copyRepo) {
        this.copyRepo = copyRepo;
    }

    @FXML
    private void delete() {
        if (Validation.emptyValidation("ID", id.getText().isEmpty())) {
            Digital digital = digitalRepo.findOne(Integer.parseInt(id.getText()));
            if (digital != null) {
                Copies copy = copyRepo.findByTitleAndType(digital.getTitle(), "Digitale Drager");
                if (copy.getNrOfCopies() > 0) {
                    copy.setNrOfCopies(copy.getNrOfCopies() - 1);
                }
                digitalRepo.delete(digital);
                Warning.alert("Digital carrier Deleted", "The carrier with title: '" + digital.getTitle() + "' has been successful deleted");
                MenuController.window.close();
            } else {
                Warning.alert("Book Not Found", "The carrier with id: " + id.getText() + " has not been found!");
            }
            ChangeScene.init("/fxml/home.fxml", "KOKW - Het verleden draait altijd mee!");
        }
    }
}
