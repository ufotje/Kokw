package be.kokw.repositories.books;

import be.kokw.bean.books.GiftedFor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

/**
 * Created By Demesmaecker Daniel
 */

@Repository("giftedForRepo")
public interface GiftedForRepo extends JpaRepository<GiftedFor, Long> {

    @Transactional
    List<GiftedFor> findByContractDateBetween(LocalDate start, LocalDate end);

    @Transactional
    List<GiftedFor> findByContractDate(LocalDate date);

    @Transactional
    List<GiftedFor> findByName(String name);

    @Transactional
    List<GiftedFor> findByContractDateAndName(LocalDate date, String name);

    @Transactional
    int deleteByBookId(int bookId);

    @Transactional
    GiftedFor findByBookId(int bookId);

    @Transactional
    @Query("select gf.name from GiftedFor gf")
    List<String> getNames();
}
