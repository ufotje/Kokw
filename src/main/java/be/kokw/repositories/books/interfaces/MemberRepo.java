package be.kokw.repositories.books.interfaces;

import be.kokw.bean.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * Created by ufotje on 8/10/2017.
 */
@Repository
public interface MemberRepo extends CrudRepository<Member,Integer> {
    @Transactional
    void deleteByName(String firstName, String lastName);
    @Transactional
    Member findByName(String firstName, String lastName);
    @Transactional
    List<Member> findByBDay(Date bDay);
    @Transactional
    List<Member> findByZip(int zip);
}
