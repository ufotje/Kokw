package be.kokw.repositories.digital;

import be.kokw.bean.digital.Digital;
import be.kokw.bean.digital.DigitalDonated;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;


@Repository("digiDonateRepo")
public interface DigitalDonateRepo extends JpaRepository<DigitalDonated,Integer> {

    @Transactional
    DigitalDonated findByDigital(Digital digital);

    @Transactional
    List<DigitalDonated> findByGiftedOnBetween(LocalDate start, LocalDate end);

    @Transactional
    List<DigitalDonated> findByGiftedOn(LocalDate date);

    @Transactional
    int deleteByDigitalId(int id);
}
