package be.kokw.repositories.books;

import be.kokw.bean.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by ufotje on 7/10/2017.
 */

public interface BookRepo extends JpaRepository<Book, Long> {
    @Transactional
    Book findById(int id);

    @Transactional
    Book findByTitle(String title);

    @Transactional
    List<Book> findByAuthorFirstNameAndAuthorLastName(String authorFirstName, String authorLastName);

    @Transactional
    Book findByISBN(String ISBN);

    @Transactional
    Book findByDepot(String Depot);

    @Transactional
    List<Book> findByBoughtOn(LocalDate date);

    @Transactional
    List<Book> findByGiftedOn(LocalDate date);

    @Transactional
    List<Book> findByGiftedFor(String name);

    @Transactional
    List<Book> findByGiftedFor(LocalDate date);

    @Transactional
    List<Book> findByGiftedFor(String name, LocalDate date);

    @Transactional
    Book findBySubTitle(String SubTitle);

    @Transactional
    List<Book> findByPublisher(String Publisher);

    @Transactional
    List<Book> findByTopic(String topic);

    @Transactional
    int deleteByTitle(String title);

    @Transactional
    int update(int id, String title, String topic, String firstName, String lastName, String publisher, String place, int year, int pages);
}
