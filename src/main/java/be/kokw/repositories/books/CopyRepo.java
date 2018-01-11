package be.kokw.repositories.books;

import be.kokw.bean.Copies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository("copyRepo")
public interface CopyRepo extends JpaRepository <Copies, Integer>  {

    @Transactional
    Copies findByTitle(String title);
}
