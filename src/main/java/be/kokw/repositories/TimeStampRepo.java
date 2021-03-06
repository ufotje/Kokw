package be.kokw.repositories;

import be.kokw.bean.TimeStamp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created By Demesmaecker Daniel
 */

@Repository("stampRepo")
public interface TimeStampRepo extends JpaRepository<TimeStamp,Integer> {

}
