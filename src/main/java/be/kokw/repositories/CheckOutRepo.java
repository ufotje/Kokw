package be.kokw.repositories;

import be.kokw.bean.books.Book;
import be.kokw.bean.CheckedOut;
import be.kokw.bean.Member;
import be.kokw.bean.digital.Digital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

/**
 * Created By Demesmaecker Daniel
 */

public interface CheckOutRepo extends JpaRepository<CheckedOut,Integer>{

    @Query("update CheckedOut set returnDate =:returnDate where title=:title and fullName=:fullName")
    int prolong(String title, String fullName, LocalDate returnDate);

    @Query("update CheckedOut set returned =:returned where title=:title and fullName=:fullName")
    int returnItem(String title, String fullName);

    @Transactional
    CheckedOut findByTitleAndFullName(String title, String fullName);

    @Transactional
    List<CheckedOut> findByReturnedIsFalse();

    @Transactional
    List<CheckedOut> findByReturnDateAndReturnedIsFalse(LocalDate returnDate);

    @Transactional
    List<CheckedOut> findByReturnDateBeforeAndReturnedIsFalse(LocalDate now);

    @Transactional
    List<CheckedOut> findByCheckOutDateBetween(LocalDate startDate, LocalDate endDate);
}
