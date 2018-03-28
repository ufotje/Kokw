package be.kokw.controllers.magazines.delete;

import be.kokw.bean.magazines.Magazine;
import be.kokw.controllers.MenuController;
import be.kokw.repositories.magazines.MagazineRepo;
import be.kokw.utility.sceneControl.ChangeScene;
import be.kokw.utility.validation.Warning;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class DeleteMag {
    @FXML
    private TextField id;
    private MagazineRepo repo;

    @Autowired
    private void setRepo(@Qualifier("magRepo") MagazineRepo repo){
        this.repo = repo;
    }

    @FXML
    private void delete(){
        MenuController.window.close();
        Magazine magazine = repo.getOne(Integer.parseInt(id.getText()));
        int result = repo.deleteById(Integer.parseInt(id.getText()));
        if(result > 0){
            Warning.alert("Magazine Removed!", "Het magazine: " + magazine.getName() + " met titel " + magazine.getTitle() + " werd succesvol werwijderd.");
        } else{
            Warning.alert("Magazine Not Found!", "Het magazine met id: " + id.getText() + " werd niet teruggevonden");
        }
        ChangeScene.init("/fxml/home.fxml", "KOKW - Het Verleden Draait Altijd Mee");
    }
}
