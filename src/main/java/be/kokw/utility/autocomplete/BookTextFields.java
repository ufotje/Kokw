package be.kokw.utility.autocomplete;

import be.kokw.bean.books.Book;
import be.kokw.repositories.books.BookRepo;
import be.kokw.utility.controller.SplitAuthor;
import be.kokw.utility.controller.SplitSubs;
import javafx.scene.control.TextField;
import org.controlsfx.control.textfield.TextFields;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class BookTextFields {

    public static void autocomplete(List<Book> list, TextField title, TextField author, TextField subTitle, TextField publisher) {
        List<String> titles = new ArrayList<>();
        List<String> authorsList = new ArrayList<>();
        List<String> publishers = new ArrayList<>();
        List<String> subsList = new ArrayList<>();
        for (Book b : list) {
            titles = AddToList.add(titles, b.getTitle());
            publishers = AddToList.add(publishers, b.getPublisher());
            List<String> aList = SplitAuthor.split(b.getAuthors(), authorsList);
            for (String s : aList) {
                authorsList = AddToList.add(authorsList, s);
            }
            List<String> sList = SplitSubs.split(b.getSubtitles(), subsList);
            for (String s : sList) {
                subsList = AddToList.add(subsList, s);
            }
        }
        TextFields.bindAutoCompletion(title, titles);
        TextFields.bindAutoCompletion(author, authorsList);
        TextFields.bindAutoCompletion(publisher, publishers);
        TextFields.bindAutoCompletion(subTitle, subsList);
    }

    public static void autoCompleteTitle(TextField title, List<Book> list) {
        List<String> titles = new ArrayList<>();
        for (Book b : list) {
            titles = AddToList.add(titles, b.getTitle());
        }
        TextFields.bindAutoCompletion(title, titles);
    }

    public static void autoCompleteTopic(TextField topic, List<Book> list) {
        List<String> topics = new ArrayList<>();
        for (Book b : list) {
            topics = AddToList.add(topics, b.getTopics());
        }
        TextFields.bindAutoCompletion(topic, topics);
    }

    public static void autoCompletPublisher(TextField publisher, List<Book> list) {
        List<String> publishers = new ArrayList<>();
        for (Book b : list) {
            publishers = AddToList.add(publishers, b.getPublisher());
        }
        TextFields.bindAutoCompletion(publisher, publishers);
    }

    public static void autoCompleAuthor(TextField author, List<Book> list) {
        List<String> authorsList = new ArrayList<>();
        for (Book b : list) {
            List<String> aList = SplitAuthor.split(b.getAuthors(), authorsList);
            for (String s : aList) {
                authorsList = AddToList.add(authorsList, s);
            }
        }
        TextFields.bindAutoCompletion(author, authorsList);
    }
}
