package be.kokw.repositories.books;

import be.kokw.bean.Gifted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository("giftedRepo")
public interface GiftedRepo extends JpaRepository<Gifted, Long> {
    @Transactional
    List<Gifted> findByGiftedOn(LocalDate date);

    @Transactional
    List<Gifted> findByGiftedOnBetween(LocalDate start, LocalDate end);


}
