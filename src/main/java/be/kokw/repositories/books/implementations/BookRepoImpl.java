package be.kokw.repositories.books.implementations;

import be.kokw.bean.Book;
import be.kokw.repositories.books.interfaces.BookRepo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by ufotje on 22/10/2017.
 */
@Repository("bookRepo")
public class BookRepoImpl implements BookRepo {
    private EntityManager manager;

    @PersistenceContext
    public void setManager(EntityManager manager) {
        this.manager = manager;

    }

    @Override
    public Book findById() {
        return null;
    }

    @Override
    public Book findByTitle(String title) {
        return null;
    }

    @Override
    public List<Book> findByAuthorFirstNameAndAuthorLastName(String firstName, String lastName) {
        return null;
    }

    @Override
    public List<Book> findByPublisher(String Publisher) {
        return null;
    }

    @Override
    public List<Book> findByTopic(String topic) {
        return null;
    }

    @Override
    public List<Book> findByYearPublished(int year) {
        return null;
    }

    @Override
    public void deleteByTitle(String title) {

    }

    @Override
    public <S extends Book> S save(S s) {
        manager.persist(s);
        return s;
    }

    @Override
    public Book findOne(Long aLong) {
        return null;
    }

    @Override
    public boolean exists(Long aLong) {
        return false;
    }

    @Override
    public List<Book> findAll() {
        return null;
    }

    @Override
    public List<Book> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Book> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Book> findAll(Iterable<Long> iterable) {
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
    public void delete(Book book) {

    }

    @Override
    public void delete(Iterable<? extends Book> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void flush() {

    }

    @Override
    public void deleteInBatch(Iterable<Book> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Book getOne(Long aLong) {
        return null;
    }

    @Override
    public <S extends Book> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public <S extends Book> List<S> save(Iterable<S> iterable) {
        return null;
    }
}
