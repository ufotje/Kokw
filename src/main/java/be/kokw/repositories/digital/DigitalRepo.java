package be.kokw.repositories.digital;

import be.kokw.bean.digital.Digital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

/**
 * Created By Demesmaecker Daniel
 */

@Repository("digitalRepo")
public interface DigitalRepo extends JpaRepository<Digital,Integer> {

    @Transactional
    List<Digital> findByBoughtOn(LocalDate boughtOn);

    @Transactional
    List<Digital> findByBoughtOnIsNotNull();

    @Transactional
    List<Digital> findByBoughtOnBetween(LocalDate startDate, LocalDate endDate);

    @Transactional
    List<Digital> findByDonatedIsTrue();

    @Transactional
    Digital findByDepot(String depot);

    @Transactional
    List<Digital> findByPublisher(String publisher);

    @Transactional
    List<Digital> findByTopicsContains(String topic);

    @Transactional
    List<Digital> findByAuthorsContains(String name);

    @Transactional
    Digital findByTitle(String title);

}
