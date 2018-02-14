package be.kokw.repositories.digital;

import be.kokw.bean.digital.DigitalTraded;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;


@Repository("digiTradeRepo")
public interface DigitalTradeRepo extends JpaRepository<DigitalTraded, Integer> {

    @Transactional
    List<DigitalTraded> findByContractDate(LocalDate contractDate);

    @Transactional
    List<DigitalTraded> findByContractDateBetween(LocalDate start, LocalDate end);

    @Transactional
    List<DigitalTraded> findByName(String name);

    @Transactional
    List<DigitalTraded> findByContractDateAndName(LocalDate date, String name);
}
