package be.kokw.controllers.maillings;

import be.kokw.bean.Member;
import be.kokw.repositories.MemberRepo;
import be.kokw.utility.controller.FileSelector;
import be.kokw.utility.maillings.CheckForAttachment;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

/**
 * Created By Demesmaecker Daniel
 */

@Component
public class AllGirls {
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

    /**
     * Sends a e-mail to all Female Members
     */
    @FXML
    private void sendMessage() {
        List<Member> memberList = repo.findByGenderEquals('v');
        CheckForAttachment.check(file, subject, text, memberList);
    }

    /**
     * Enables Attachment to e-mail
     */
    @FXML
    private void chooseFile() {
        file = FileSelector.chooseFile();
    }
}
