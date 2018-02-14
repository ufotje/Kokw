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
        List<String> titles = new ArrayList<>();
        List<String> authorsList = new ArrayList<>();
        List<String> publishers = new ArrayList<>();
        List<String> subsList = new ArrayList<>();
        for (Digital d : repo.findAll()) {
            if(!titles.contains(d.getTitle())) {
                titles.add(d.getTitle());
            }
            if (!publishers.contains(d.getPublisher())) {
                publishers.add(d.getPublisher());
            }
            List<String> aList = SplitAuthor.split(d.getAuthors(), authorsList);
            for(String s : aList){
                if(!authorsList.contains(s)){
                    authorsList.add(s);
                }
            }
            List<String> sList = SplitSubs.split(d.getSubtitles(), subsList);
            for(String s : sList){
                if(!subsList.contains(s)){
                    subsList.add(s);
                }
            }
        }
        TextFields.bindAutoCompletion(title, titles);
        TextFields.bindAutoCompletion(author, authorsList);
        TextFields.bindAutoCompletion(publisher, publishers);
        TextFields.bindAutoCompletion(subTitle, subsList);
    }
}
