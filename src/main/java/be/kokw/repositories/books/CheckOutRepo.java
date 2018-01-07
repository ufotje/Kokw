package be.kokw.repositories.books;

import be.kokw.bean.books.Book;
import be.kokw.bean.books.CheckedOut;
import be.kokw.bean.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

public interface CheckOutRepo extends JpaRepository<CheckedOut,Long>{
    @Transactional
    CheckedOut save(Book b, Member m);

    @Transactional
    int prolong(String title, String fullName, LocalDate returnDate);

    @Transactional
    int returnBook(String title, String fullName);

    @Transactional
    CheckedOut getOne(String title, String fullName);

    @Transactional
    List<CheckedOut> findByReturnedIsFalse();

    @Transactional
    List<CheckedOut> findByReturnDateAndReturnedIsFalse(LocalDate returnDate);

    @Transactional
    List<CheckedOut> findByReturnDateBeforeAndReturnedIsFalse(LocalDate now);

    @Transactional
    List<CheckedOut> findByCheckOutDateBetween(LocalDate startDate, LocalDate endDate);
}
