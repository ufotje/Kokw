package be.kokw.repositories;

import be.kokw.bean.Book;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by ufotje on 7/10/2017.
 */


public interface BookRepo extends JpaRepository<Book, Long> {
    @Transactional
    Book findById();

    @Transactional
    Book findByTitle(String title);

    @Transactional
    List<Book> findByAuthorFirstNameAndAuthorLastName(String authorFirstName, String authorLastName);

    @Transactional
    List<Book> findByPublisher(String Publisher);

    @Transactional
    List<Book> findByTopic(String topic);

    @Transactional
    int deleteByTitle(String title);

    @Transactional
    int update(int id, String title, String topic, String firstName, String lastName, String publisher, String place, int year, int pages);
}
