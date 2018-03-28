package be.kokw.utility.maillings;

import be.kokw.bean.Member;
import be.kokw.controllers.MenuController;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;

import java.io.File;
import java.util.List;

public interface CheckForAttachment {

    static void check(File file, TextField subject, HTMLEditor text, List<Member> memberList) {
        MenuController.window.close();
        if (file == null) {
            Mail.sendMail(memberList, subject.getText(), text.getHtmlText());
        } else {
            MailWithAttachement.sendMail(subject.getText(), text.getHtmlText(), file, memberList);
        }
    }
}
