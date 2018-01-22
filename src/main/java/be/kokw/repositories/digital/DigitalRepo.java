package be.kokw.repositories.digital;

import be.kokw.bean.digital.Digital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository("digitalRepo")
public interface DigitalRepo extends JpaRepository<Digital,Long> {

    @Transactional
    List<Digital> findByBoughtOn(LocalDate boughtOn);

    @Transactional
    List<Digital> findByBoughtOnIsNotNull();

    @Transactional
    List<Digital> findByBoughtOnBetween(LocalDate startDate, LocalDate endDate);

    @Transactional
    List<Digital> findByDonatedIsTrue();
}
