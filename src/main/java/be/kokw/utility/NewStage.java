package be.kokw.utility;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by ufotje on 2/11/2017.
 */
public interface NewStage {
    static Stage getStage(String title, String resource){
        Stage window = new Stage();
        Parent root = null;
        try {
            root = ControllerBean.getBean(resource);
        } catch (Exception e) {
            Warning.alert("Error!", "Er ging iets fout tijdens het laden van de pagina!");
            e.printStackTrace();
        }
        window.setScene(new Scene(root));
        window.setTitle(title);
        window.initModality(Modality.APPLICATION_MODAL);
        return window;
    }
}
