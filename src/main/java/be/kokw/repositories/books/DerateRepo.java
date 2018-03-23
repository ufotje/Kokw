package be.kokw.repositories.books;

import be.kokw.bean.books.Book;
import be.kokw.bean.books.Derated;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository("derateRepo")
public interface DerateRepo extends JpaRepository<Derated,Integer> {

    @Transactional
    Derated findByTitle(String title);

    @Transactional
    List<Derated> findByDestination(String destination);

    @Transactional
    List<Derated> findByDerated(LocalDate date);

    @Transactional
    Derated findByBook(Book book);

    @Query("select d.title from derated d")
    List<String> getTitle();
}
