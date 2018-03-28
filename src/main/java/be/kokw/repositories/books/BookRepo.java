package be.kokw.repositories.books;

import be.kokw.bean.books.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Demesmaecker Daniel on 7/10/2017.
 */
@Repository("bookRepo")
public interface BookRepo extends JpaRepository<Book, Integer> {
    @Transactional
    Book findById(int id);

    @Transactional
    List<Book> findByTitle(String title);

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
    List<Book> findBookByBoughtOnIsNotNull();

    @Transactional
    Book findBySubtitlesContains(String SubTitle);

    @Transactional
    List<Book> findByPublisher(String Publisher);

    @Transactional
    List<Book> findByGiftedIsTrue();

    @Transactional
    int deleteByTitle(String title);

    @Transactional
    @Query("select b.authors from Book b")
    List<String> findAuthors();

    @Transactional
    @Query("select b.title from Book b")
    List<String> findTitles();
}
