package be.kokw.repositories.digital;

import be.kokw.bean.digital.DigitalTraded;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository("digiTradeRepo")
public interface DigitalTradeRepo extends JpaRepository<DigitalTraded, Integer> {
    List<DigitalTraded> findByContractDate(LocalDate contractDate);
}
