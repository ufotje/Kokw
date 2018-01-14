package be.kokw.repositories.magazines;


import be.kokw.bean.magazines.MagazineCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository("magCountRepo")
public interface MagazineCountRepo extends JpaRepository<MagazineCount, Integer> {

    @Transactional
    MagazineCount findByName(String name);

    @Query("select m from MagazineCount m where m.expected = m.received")
    List<MagazineCount> findByExpectedEqualsReceived();

    @Query("select m from MagazineCount m where m.expected > m.received")
    List<MagazineCount> findByExpectedIsGreaterThanReceived();
}
