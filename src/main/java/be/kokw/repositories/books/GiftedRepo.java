package be.kokw.repositories.books;

import be.kokw.bean.Gifted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("giftedRepo")
public interface GiftedRepo extends JpaRepository<Gifted, Long> {
}
