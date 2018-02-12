package be.kokw.controllers.maillings;

import be.kokw.bean.Member;
import be.kokw.controllers.MenuController;
import be.kokw.repositories.MemberRepo;
import be.kokw.utility.maillings.Mail;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ToBoard {
   @FXML
    TextArea text;
   @FXML
    TextField subject;
    private MemberRepo repo;

    @Autowired
    private void setRepo(@Qualifier("memberRepo") MemberRepo repo) {
        this.repo = repo;
    }

   @FXML
    private void sendMessage(){
       List<Member> memberList = repo.findByBoardIsTrue();
       for(Member m : memberList) {
           Mail.sendMail(m.getEmail(), subject.getText(), text.getText());
       }
       MenuController.window.close();
   }
}
