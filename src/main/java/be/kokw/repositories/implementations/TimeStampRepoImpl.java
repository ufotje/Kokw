package be.kokw.repositories.implementations;

import be.kokw.bean.TimeStamp;
import be.kokw.repositories.TimeStampRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("timeStampRepo")
public class TimeStampRepoImpl implements TimeStampRepo{
    private EntityManager manager;

    @PersistenceContext
    private void setManager(EntityManager manager){
        this.manager = manager;
    }

    @Override
    public <S extends TimeStamp> S save(S s) {
        manager.persist(s);
        return s;
    }

    @Override
    public TimeStamp findOne(Long aLong) {
        return null;
    }

    @Override
    public boolean exists(Long aLong) {
        return false;
    }

    @Override
    public List<TimeStamp> findAll() {
        return null;
    }

    @Override
    public List<TimeStamp> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<TimeStamp> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<TimeStamp> findAll(Iterable<Long> iterable) {
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
    public void delete(TimeStamp timeStamp) {

    }

    @Override
    public void delete(Iterable<? extends TimeStamp> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void flush() {

    }

    @Override
    public void deleteInBatch(Iterable<TimeStamp> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public TimeStamp getOne(Long aLong) {
        return null;
    }

    @Override
    public <S extends TimeStamp> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public <S extends TimeStamp> List<S> save(Iterable<S> iterable) {
        return null;
    }
}
