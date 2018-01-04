package be.kokw.repositories;

import be.kokw.bean.Magazine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository("magRepo")
public interface MagazineRepo extends JpaRepository<Magazine, Long> {
    @Transactional
    List<Magazine> findMagazinesByName(String name);
}
