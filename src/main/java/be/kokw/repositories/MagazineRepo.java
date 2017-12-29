package be.kokw.repositories;

import be.kokw.bean.Magazine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MagazineRepo extends JpaRepository<Magazine, Long> {
}
