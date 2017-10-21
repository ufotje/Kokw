package be.kokw.repositories.books.interfaces;

import java.util.List;
import be.kokw.bean.Book;
/**
 * Created by ufotje on 20/10/2017.
 */
public interface GetBookRepo{

    public Book findByTitle(String title);
    public List<Book> findByAuthor(String firstName, String lastName);
    public List<Book> findByPublisher(String Publicer);
    public List<Book> findByTopic(String topic);
    public List<Book> findByYearPubliced(int year);
}
