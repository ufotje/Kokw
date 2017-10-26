package be.kokw.utility;

import be.kokw.Main;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by ufotje on 26/10/2017.
 */
public interface ChangeScene {
    @FXML
    static void init(String resource, String title) throws Exception {
        Parent root = GetControllerBean.getBean(resource);
        Stage window = Main.stage;
        window.setTitle(title);
        window.setScene(new Scene(root,800,600));
        window.show();
    }
}
