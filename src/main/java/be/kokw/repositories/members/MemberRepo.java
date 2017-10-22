package be.kokw.repositories.members;

import be.kokw.bean.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * Created by ufotje on 8/10/2017.
 */


public interface MemberRepo extends JpaRepository<Member,Long> {

    @Transactional
    List<Member> findByBDay(Date bDay);
    @Transactional
    List<Member> findByZip(int zip);
}
