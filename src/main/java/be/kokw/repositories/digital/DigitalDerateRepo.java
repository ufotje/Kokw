package be.kokw.repositories.digital;

import be.kokw.bean.digital.Derated;
import be.kokw.bean.digital.Digital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

/**
 * Created By Demesmaecker Daniel
 */

@Repository("digiDerateRepo")
public interface DigitalDerateRepo extends JpaRepository<Derated,Integer> {

    @Transactional
    Derated findByTitle(String title);

    @Transactional
    List<Derated> findByDestination(String destination);

    @Transactional
    List<Derated> findByDerated(LocalDate date);

    @Transactional
    Derated findByDigital(Digital digital);

    @Query("select d.title from derated d")
    List<String> getTitle();
}
