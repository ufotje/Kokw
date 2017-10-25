package be.kokw.utility;

import be.kokw.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 * Created by ufotje on 25/10/2017.
 * Returns a bean for the corresponding controllerClass
 */
public interface GetControllerBean {
    static Parent getBean(String resource)throws Exception{
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(resource));
        loader.setControllerFactory(Main.springContext::getBean);
        return loader.load();
    }
}
