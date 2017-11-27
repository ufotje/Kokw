package be.kokw.repositories.implementations;

import be.kokw.bean.Book;
import be.kokw.bean.CheckedOut;
import be.kokw.bean.Member;
import be.kokw.repositories.CheckOutRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

@Repository("checkOutRepo")
public class CheckOutRepoImpl implements CheckOutRepo {
    private EntityManager manager;

    @Autowired
    private void setManager(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public CheckedOut save(Book b, Member m) {
        CheckedOut checkOut = new CheckedOut(b, m);
        manager.persist(checkOut);
        return checkOut;
    }

    @Override
    public int prolong(String title, String fullName, LocalDate returnDate) {
        Query query = manager.createQuery("update CheckedOut set returnDate =:returnDate where title=:title and fullName=:fullName");
        query.setParameter("returnDate", returnDate).setParameter("title", title).setParameter("fullName", fullName);
        return query.executeUpdate();
    }

    @Override
    public int returnBook(String title, String fullName) {
        boolean returned = true;
        Query query = manager.createQuery("update CheckedOut set returned =:returned where title=:title and fullName=:fullName");
        query.setParameter("title", title).setParameter("fullName", fullName).setParameter("returned", returned);
        return query.executeUpdate();
    }

    @Override
    public CheckedOut getOne(String title, String fullName) {
        TypedQuery <CheckedOut> query = manager.createQuery("select c from CheckedOut c where c.title=:title and c.fullName =:fullName",CheckedOut.class);
        return query.setParameter("title", title).setParameter("fullName", fullName).getSingleResult();
    }

    @Override
    public <S extends CheckedOut> S save(S s) {
        return null;
    }

    @Override
    public CheckedOut findOne(Long aLong) {
        return null;
    }

    @Override
    public boolean exists(Long aLong) {
        return false;
    }

    @Override
    public List<CheckedOut> findAll() {
        return null;
    }

    @Override
    public List<CheckedOut> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<CheckedOut> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<CheckedOut> findAll(Iterable<Long> iterable) {
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
    public void delete(CheckedOut checkedOut) {

    }

    @Override
    public void delete(Iterable<? extends CheckedOut> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void flush() {

    }

    @Override
    public void deleteInBatch(Iterable<CheckedOut> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public CheckedOut getOne(Long aLong) {
        return null;
    }

    @Override
    public <S extends CheckedOut> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public <S extends CheckedOut> List<S> save(Iterable<S> iterable) {
        return null;
    }
}
