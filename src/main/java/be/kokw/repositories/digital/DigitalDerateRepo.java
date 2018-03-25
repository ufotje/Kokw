package be.kokw.repositories.digital;

import be.kokw.bean.digital.DeratedDigital;
import be.kokw.bean.digital.Digital;
import be.kokw.repositories.DerateRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

/**
 * Created By Demesmaecker Daniel
 *
 * @deprecated use {@link DerateRepo} instead.
 */

@Repository("digiDerateRepo")
public interface DigitalDerateRepo extends JpaRepository<DeratedDigital, Integer> {

    @Transactional
    DeratedDigital findByTitle(String title);

    @Transactional
    List<DeratedDigital> findByDestination(String destination);

    @Transactional
    List<DeratedDigital> findByDerated(LocalDate date);

    @Transactional
    DeratedDigital findByDigital(Digital digital);

    @Query("select d.title from Derated d")
    List<String> getTitle();
}
