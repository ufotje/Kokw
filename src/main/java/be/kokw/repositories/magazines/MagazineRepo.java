package be.kokw.repositories.magazines;

import be.kokw.bean.magazines.Magazine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created By Demesmaecker Daniel
 */

@Repository("magRepo")
public interface MagazineRepo extends JpaRepository<Magazine, Integer> {
    @Transactional
    List<Magazine> findMagazinesByName(String name);

    @Transactional
    List<Magazine> findMagazinesBySubscribedIsTrue();

    @Transactional
    List<Magazine> findMagazinesByTradedIsTrue();

    @Transactional
    List<Magazine> findMagazinesByTheme(String theme);

    @Transactional
    List<Magazine> findMagazinesByPublisher(String publisher);

    @Transactional
    int deleteById(int id);

    @Transactional
    Magazine findByTitle(String title);

    @Transactional
    Magazine findMagazineByIssn(String issn);
}
