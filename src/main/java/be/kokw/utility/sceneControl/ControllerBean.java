package be.kokw.utility.sceneControl;

import be.kokw.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 * Created by ufotje on 25/10/2017.
 * Returns a bean for the corresponding controllerClass
 */
public interface ControllerBean {
    static Parent getBean(String resource)throws Exception{
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(resource));
        loader.setControllerFactory(Main.springContext::getBean);
        return loader.load();
    }
}
