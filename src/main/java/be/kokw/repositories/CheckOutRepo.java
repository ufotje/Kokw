package be.kokw.repositories;

import be.kokw.bean.Book;
import be.kokw.bean.CheckedOut;
import be.kokw.bean.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import javax.transaction.Transactional;


public interface CheckOutRepo extends JpaRepository<CheckedOut,Long>{
    @Transactional
    CheckedOut save(Book b, Member m);
}
