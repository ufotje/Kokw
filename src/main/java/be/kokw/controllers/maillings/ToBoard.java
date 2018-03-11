package be.kokw.controllers.maillings;

import be.kokw.bean.Member;
import be.kokw.controllers.MenuController;
import be.kokw.repositories.MemberRepo;
import be.kokw.utility.controller.FileSelector;
import be.kokw.utility.maillings.Mail;
import be.kokw.utility.maillings.MailWithAttachement;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Component
public class ToBoard {
    @FXML
    private HTMLEditor text;
    @FXML
    private TextField subject;
    private File file;
    private MemberRepo repo;

    @Autowired
    private void setRepo(@Qualifier("memberRepo") MemberRepo repo) {
        this.repo = repo;
    }

    @FXML
    private void sendMessage() {
        List<Member> memberList = repo.findByBoardIsTrue();
        if (file == null) {
            Mail.sendMail(memberList, subject.getText(), text.getHtmlText());
            MenuController.window.close();
        } else {
            for (Member m : memberList) {
                MailWithAttachement.sendMail(subject.getText(), text.getHtmlText(), file, memberList);
                MenuController.window.close();
            }
        }
    }

    @FXML
    private void chooseFile() {
        file = FileSelector.chooseFile();
    }
}
