package be.kokw.repositories.books;

import be.kokw.bean.books.Gifted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

/**
 * Created By Demesmaecker Daniel
 */

@Repository("giftedRepo")
public interface GiftedRepo extends JpaRepository<Gifted, Long> {
    @Transactional
    List<Gifted> findByGiftedOn(LocalDate date);

    @Transactional
    List<Gifted> findByGiftedOnBetween(LocalDate start, LocalDate end);

    @Transactional
    Gifted findByBookId(int bookId);

    @Transactional
    void deleteByBookId(int bookId);


}
