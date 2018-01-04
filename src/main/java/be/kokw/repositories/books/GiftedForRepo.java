package be.kokw.repositories.books;

import be.kokw.bean.GiftedFor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository("giftedForRepo")
public interface GiftedForRepo extends JpaRepository<GiftedFor, Long> {

    @Transactional
    List<GiftedFor> findByContractDateBetween(LocalDate start, LocalDate end);

    @Transactional
    List<GiftedFor> findByContractDate(LocalDate date);

    @Transactional
    List<GiftedFor> findByName(String name);

    @Transactional
    List<GiftedFor> findByContractDateAndName(LocalDate date, String name);
}
