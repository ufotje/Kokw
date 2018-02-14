package be.kokw.utility.autocomplete;

import be.kokw.bean.books.Book;
import be.kokw.repositories.books.BookRepo;
import be.kokw.utility.controller.SplitAuthor;
import be.kokw.utility.controller.SplitSubs;
import javafx.scene.control.TextField;
import org.controlsfx.control.textfield.TextFields;

import java.util.ArrayList;
import java.util.List;

public interface BookTextFields {

    static void autocomplete(BookRepo repo, TextField title, TextField author, TextField subTitle, TextField publisher) {
        List<Book> books = repo.findAll();
        List<String> titles = new ArrayList<>();
        List<String> authorsList = new ArrayList<>();
        List<String> publishers = new ArrayList<>();
        List<String> subsList = new ArrayList<>();
        for (Book b : books) {

            if (!publishers.contains(b.getPublisher())) {
                publishers.add(b.getPublisher());
            }
            List<String> aList = SplitAuthor.split(b.getAuthors(), authorsList);
            for(String s : aList){
                if(!authorsList.contains(s)){
                    authorsList.add(s);
                }
            }
            List<String> sList = SplitSubs.split(b.getSubtitles(), subsList);
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
