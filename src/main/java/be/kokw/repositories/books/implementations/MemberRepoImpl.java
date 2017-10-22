package be.kokw.repositories.books.implementations;

import be.kokw.bean.Member;
import be.kokw.repositories.books.interfaces.MemberRepo;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by ufotje on 22/10/2017.
 */
@Repository("memberRepo")
public class MemberRepoImpl implements MemberRepo {
    @Override
    public List<Member> findByBDay(Date bDay) {
        return null;
    }

    @Override
    public List<Member> findByZip(int zip) {
        return null;
    }

    @Override
    public <S extends Member> S save(S s) {
        return null;
    }

    @Override
    public <S extends Member> Iterable<S> save(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Member findOne(Integer integer) {
        return null;
    }

    @Override
    public boolean exists(Integer integer) {
        return false;
    }

    @Override
    public Iterable<Member> findAll() {
        return null;
    }

    @Override
    public Iterable<Member> findAll(Iterable<Integer> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public void delete(Member member) {

    }

    @Override
    public void delete(Iterable<? extends Member> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}
