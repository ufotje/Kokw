package be.kokw.utility;

import be.kokw.bean.books.Book;
import be.kokw.repositories.books.BookRepo;
import javafx.scene.control.TextField;
import org.controlsfx.control.textfield.TextFields;

import java.util.ArrayList;
import java.util.List;

public interface BookTextFields {

    static void autocomplete(BookRepo repo, TextField title, TextField author, TextField subTitle, TextField publisher){
        List<Book> books = repo.findAll();
        List<String> titles = new ArrayList<>();
        List<String> authorsList = new ArrayList<>();
        List <String> publishers = new ArrayList<>();
        List <String> subsList = new ArrayList<>();
        for(Book b : books){
            titles.add(b.getTitle());
            publishers.add(b.getPublisher());
            String[] authorsSplited = b.getAuthors().split("/n");
            String[] subsSplited = b.getSubtitles().split("/n");
            for(String s : authorsSplited){
                if(!authorsList.contains(s)){
                    authorsList.add(s);
                }
            }
            for(String s : subsSplited){
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
