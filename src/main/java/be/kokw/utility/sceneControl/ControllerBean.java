package be.kokw.utility.sceneControl;

import be.kokw.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 * Created by Demesmaecker on 25/10/2017.
 */
public interface ControllerBean {

    /**
     * Returns a bean for the corresponding controllerClass
     * @param resource String
     * @return Parent
     * @throws Exception ex
     */
    static Parent getBean(String resource)throws Exception{
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(resource));
        loader.setControllerFactory(Main.springContext::getBean);
        return loader.load();
    }
}
