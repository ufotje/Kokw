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
    List<Book> findBookByTopicsContains(String topics);

    @Transactional
    List<Book> findByAuthorsContains(String author);

    @Transactional
    Book findByIsbn(String ISBN);

    @Transactional
    Book findByDepot(String Depot);

    @Transactional
    List<Book> findByBoughtOn(LocalDate date);

    @Transactional
    List<Book> findByBoughtOnBetween(LocalDate start, LocalDate end);

    @Transactional
    List<Book> findByGiftedOn(LocalDate date);

    @Transactional
    List<Book> findByGiftedOnBetween(LocalDate start, LocalDate end);

    @Transactional
    List<Book> findByContractDateBetween(LocalDate start, LocalDate end);

    @Transactional
    List<Book> findByContractDate(LocalDate date);

    @Transactional
    List<Book> findByContractor(String name);

    @Transactional
    List<Book> findByContractDateAndContractor(LocalDate date, String name);

    @Transactional
    Book findBySubtitlesContains(String SubTitle);

    @Transactional
    List<Book> findByPublisher(String Publisher);

    @Transactional
    int deleteByTitle(String title);

    @Transactional
    int update(int id, String title, String topic, String firstName, String lastName, String publisher, String place, int year, int pages);

    @Transactional
    int updateDeratedAndDestination( String title);
}
