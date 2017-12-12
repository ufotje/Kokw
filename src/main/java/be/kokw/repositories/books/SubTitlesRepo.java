package be.kokw.repositories.books;

import be.kokw.bean.SubTitles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("subRepo")
public interface SubTitlesRepo extends JpaRepository<SubTitles, Long> {
}
