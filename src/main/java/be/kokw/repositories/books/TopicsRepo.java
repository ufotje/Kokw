package be.kokw.repositories.books;

import be.kokw.bean.Topics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("topicsRepo")
public interface TopicsRepo extends JpaRepository<Topics, Long> {
}
