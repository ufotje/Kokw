package be.kokw.repositories;

import be.kokw.bean.TimeStamp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("timeStampRepo")
public interface TimeStampRepo extends JpaRepository<TimeStamp,Long> {

}
