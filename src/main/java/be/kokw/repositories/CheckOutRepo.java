package be.kokw.repositories;

import be.kokw.bean.Book;
import be.kokw.bean.CheckedOut;
import be.kokw.bean.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;


public interface CheckOutRepo extends JpaRepository<CheckedOut,Long>{
    @Transactional
    CheckedOut save(Book b, Member m);

    @Transactional
    int prolong(String title, String fullName, LocalDate returnDate);

    @Transactional
    int returnBook(String title, String fullName);

    @Transactional
    CheckedOut getOne(String title, String fullName);
}
