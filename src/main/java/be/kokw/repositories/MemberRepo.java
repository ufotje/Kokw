package be.kokw.repositories;

import be.kokw.bean.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * Created by ufotje on 8/10/2017.
 * This is the JPARepository fo the memberEntity
 */

@Repository("memberRepo")
public interface MemberRepo extends JpaRepository<Member,Long> {

    @Transactional
    List<Member> findByBDay(Date bDay);

    @Transactional
    List<Member> findByZip(int zip);

    @Transactional
    Member findByFirstNameAndLastName(String firstName, String lastName);

    @Transactional
    List<Member> findByCity(String city);
}
