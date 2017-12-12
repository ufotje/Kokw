package be.kokw.repositories.books;

import be.kokw.bean.GiftedFor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("giftedForRepo")
public interface GiftedForRepo extends JpaRepository<GiftedFor, Long> {
}
