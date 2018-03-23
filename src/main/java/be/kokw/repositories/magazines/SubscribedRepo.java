package be.kokw.repositories.magazines;

import be.kokw.bean.magazines.Subscribed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created By Demesmaecker Daniel
 */

@Repository("subscribedRepo")
public interface SubscribedRepo extends JpaRepository<Subscribed,Integer> {

    @Transactional
    List<Subscribed> findByNameMag(String name);
}
