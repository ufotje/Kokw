package be.kokw.repositories.books.implementations;

import be.kokw.bean.Member;
import be.kokw.repositories.members.MemberRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by ufotje on 22/10/2017.
 */
@Repository()
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
    public Member findOne(Long aLong) {
        return null;
    }

    @Override
    public boolean exists(Long aLong) {
        return false;
    }

    @Override
    public List<Member> findAll() {
        return null;
    }

    @Override
    public List<Member> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Member> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Member> findAll(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void delete(Long aLong) {

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

    @Override
    public void flush() {

    }

    @Override
    public void deleteInBatch(Iterable<Member> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Member getOne(Long aLong) {
        return null;
    }

    @Override
    public <S extends Member> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public <S extends Member> List<S> save(Iterable<S> iterable) {
        return null;
    }
}
