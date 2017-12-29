package be.kokw.repositories;

import be.kokw.bean.Digital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("digitalRepo")
public interface DigitalRepo extends JpaRepository<Digital,Long> {
}
