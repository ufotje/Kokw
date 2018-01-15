package be.kokw.repositories.digital;

import be.kokw.bean.digital.Digital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("digitalRepo")
public interface DigitalRepo extends JpaRepository<Digital,Long> {
}
