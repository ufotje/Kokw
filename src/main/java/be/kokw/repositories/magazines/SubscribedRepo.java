package be.kokw.repositories.magazines;

import be.kokw.bean.magazines.Subscribed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository("subscribedRepo")
public interface SubscribedRepo extends JpaRepository<Subscribed,Integer> {

    @Transactional
    Subscribed findByNameMag(String name);
}
