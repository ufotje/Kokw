package be.kokw.repositories.books.interfaces;

import be.kokw.bean.Book;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by ufotje on 7/10/2017.
 */

public interface BookRepo extends CrudRepository<Book,Integer> {
    @Transactional
    public Book findById();
    @Transactional
    public Book findByTitle(String title);
    @Transactional
    public List<Book> findByAuthorFirstNameAndAuthorLastName(String firstName, String lastName);
    @Transactional
    public List<Book> findByPublisher(String Publisher);
    @Transactional
    public List<Book> findByTopic(String topic);
    @Transactional
    public List<Book> findByYearPublished(int year);
    @Transactional
    public void deleteByTitle(String title);
}
