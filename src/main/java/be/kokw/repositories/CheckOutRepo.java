package be.kokw.repositories;

import be.kokw.bean.CheckedOut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

/**
 * Created By Demesmaecker Daniel
 */

public interface CheckOutRepo extends JpaRepository<CheckedOut,Integer>{

    @Modifying
    @Transactional
    @Query("update CheckedOut set returnDate =:returnDate where title=:title and fullName=:fullName and returned = false")
    int prolong(@Param("title") String title, @Param("fullName") String fullName, @Param("returnDate") LocalDate returnDate);

    @Modifying
    @Transactional
    @Query("update CheckedOut set returned =true where title=:title and fullName=:fullName and returned = false")
    int returnItem(@Param("title") String title, @Param("fullName") String fullName);

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
