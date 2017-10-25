package be.kokw.controllers.members;

import be.kokw.Main;
import be.kokw.bean.Member;
import be.kokw.repositories.members.MemberRepo;
import be.kokw.utility.GetControllerBean;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


/**
 * Created by ufotje on 21/10/2017.
 */

@Component
public class AddMember {
    private Stage window;
    private MemberRepo repo;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField street;
    @FXML
    private TextField houseNr;
    @FXML
    private TextField zip;
    @FXML
    private TextField city;
    @FXML
    private TextField email;
    @FXML
    private RadioButton board;
    @FXML
    private DatePicker bDay;

    public AddMember() throws Exception{

    }

    @Autowired
    public void setMemberRepo(@Qualifier("memberRepo") MemberRepo memberRepo) {
        repo = memberRepo;
    }

    @FXML
    public void init() throws Exception {
        Parent root = GetControllerBean.getBean("/fxml/addMember.fxml");
        window = Main.stage;
        window.setTitle("Add New Member");
        window.setScene(new Scene(root));
        window.show();
    }

    @FXML
    private void save() {
        Boolean isBoard = board.isSelected();
        Member member = new Member(firstName.getText(),lastName.getText(),street.getText(),Integer.parseInt(houseNr.getText()),Integer.parseInt(zip.getText()),city.getText(),email.getText(),bDay.getValue(),isBoard);
        System.out.println(member.getbDay());
        repo.save(member);
    }

    @FXML
    private void addMore() {
        System.out.println(repo.toString());
    }
}
