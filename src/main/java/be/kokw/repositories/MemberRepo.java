package be.kokw.repositories;

import be.kokw.bean.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Demesmaecker Daniel on 8/10/2017.
 * This is the JPARepository fo the memberEntity
 */

@Repository("memberRepo")
public interface MemberRepo extends JpaRepository<Member, Long> {

    @Transactional
    List<Member> findByBoardIsTrue();

    @Transactional
    List<Member> findByBDay(LocalDate bDay);

    @Transactional
    List<Member> findByBDayBetween(LocalDate last, LocalDate now);

    @Transactional
    List<Member> findByZip(int zip);

    @Transactional
    Member findByFirstNameAndLastName(String firstName, String lastName);

    @Transactional
    List<Member> findByCity(String city);

    @Transactional
    List<Member> findByPayedIsFalse();

    @Transactional
    List<Member> findByAnalIsFalse();
}
