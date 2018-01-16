package be.kokw.repositories.digital;

import be.kokw.bean.digital.DigitalDonated;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("digiDonateRepo")
public interface DigitalDonateRepo extends JpaRepository<DigitalDonated,Integer> {
}
