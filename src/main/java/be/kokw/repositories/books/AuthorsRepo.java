package be.kokw.repositories.books;

import be.kokw.bean.Authors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("authorRepo")
public interface AuthorsRepo extends JpaRepository<Authors, Long> {
}
