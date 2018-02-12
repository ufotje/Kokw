package be.kokw.utility.autocomplete;

import be.kokw.bean.digital.Digital;
import be.kokw.repositories.digital.DigitalRepo;
import be.kokw.utility.controller.SplitAuthor;
import be.kokw.utility.controller.SplitSubs;
import javafx.scene.control.TextField;
import org.controlsfx.control.textfield.TextFields;

import java.util.ArrayList;
import java.util.List;

public interface DigiTextFields {

    static void autocomplete(DigitalRepo repo, TextField title, TextField author, TextField subTitle, TextField publisher) {
        List<Digital> digitals = repo.findAll();
        List<String> titles = new ArrayList<>();
        List<String> authorsList = new ArrayList<>();
        List<String> publishers = new ArrayList<>();
        List<String> subsList = new ArrayList<>();
        for (Digital d : digitals) {
            titles.add(d.getTitle());
            publishers.add(d.getPublisher());
            authorsList = SplitAuthor.split(d.getAuthors(), authorsList);
            subsList = SplitSubs.split(d.getSubtitles(), subsList);
        }
        TextFields.bindAutoCompletion(title, titles);
        TextFields.bindAutoCompletion(author, authorsList);
        TextFields.bindAutoCompletion(publisher, publishers);
        TextFields.bindAutoCompletion(subTitle, subsList);
    }
}
