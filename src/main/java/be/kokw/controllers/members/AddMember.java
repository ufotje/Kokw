package be.kokw.controllers.members;

import be.kokw.Main;
import be.kokw.controllers.MenuController;
import be.kokw.repositories.members.MemberRepo;

import be.kokw.services.GetControllerBean;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.springframework.stereotype.Component;

/**
 * Created by ufotje on 21/10/2017.
 */

@Component
public class AddMember{
    private Stage window;
    private MemberRepo repo;

    public AddMember() {
    }

    public AddMember(MemberRepo repo) throws Exception{
        this.repo = repo;
        init();
    }

    @FXML
    private void init() throws Exception {
        Parent root = GetControllerBean.getBean("/fxml/addMember.fxml");
        window = Main.stage;
        window.setTitle("Add New Member");
        window.setScene(new Scene(root));
        window.show();
    }

    @FXML
    private void save(){}

    @FXML
    private  void addMore(){}
}
