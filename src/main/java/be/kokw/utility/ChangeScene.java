package be.kokw.utility;

import be.kokw.Main;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 * Created by ufotje on 26/10/2017.
 */
public interface ChangeScene {
    @FXML
    static void init(String resource, String title){
        Parent root = null;
        try {
            root = ControllerBean.getBean(resource);
        } catch (Exception e) {
            Warning.alert("Error!", "Er ging iets fout tijdens het laden van de pagina!");
            e.printStackTrace();
        }
        Stage window = Main.stage;
        window.setTitle(title);
        window.getScene().setRoot(root);
        window.show();
    }
}
