package be.kokw.repositories;

import be.kokw.bean.TimeStamp;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TimeStampRepo extends JpaRepository<TimeStamp,Long> {

}
