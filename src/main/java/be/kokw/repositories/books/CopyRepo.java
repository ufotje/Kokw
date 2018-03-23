package be.kokw.repositories.books;

import be.kokw.bean.Copies;
import be.kokw.bean.books.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created By Demesmaecker Daniel
 */

@Repository("copyRepo")
public interface CopyRepo extends JpaRepository <Copies, Integer>  {

    @Transactional
    Copies findByTitleAndType(String title, String type);
}
