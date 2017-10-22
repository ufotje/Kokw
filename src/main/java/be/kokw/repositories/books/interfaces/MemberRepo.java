package be.kokw.repositories.books.interfaces;

import be.kokw.bean.Member;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * Created by ufotje on 8/10/2017.
 */

public interface MemberRepo extends CrudRepository<Member,Integer> {

    @Transactional
    List<Member> findByBDay(Date bDay);
    @Transactional
    List<Member> findByZip(int zip);
}
