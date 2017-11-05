package be.kokw.repositories.books.implementations;

import be.kokw.bean.Book;

import be.kokw.repositories.BookRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by ufotje on 22/10/2017.
 * The bookrepo implementation
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
        TypedQuery<Book> query = manager.createQuery("select b from Book b where b.title = :title",Book.class);
        return query.setParameter("title", title).getSingleResult();
    }

    @Override
    public List<Book> findByAuthorFirstNameAndAuthorLastName(String firstName, String lastName) {
        TypedQuery<Book> query = manager.createQuery("select b from Book b where b.authorFirstName = :firstname and b.authorLastName = :lastname",Book.class);
        return query.setParameter("firstname", firstName).setParameter("lastname",lastName).getResultList();
    }

    @Override
    public List<Book> findByPublisher(String publisher){
        TypedQuery<Book> query = manager.createQuery("select b from Book b where b.publisher = :publisher",Book.class);
        return query.setParameter("publisher", publisher).getResultList();
    }

    @Override
    public List<Book> findByTopic(String topic) {
        return null;
    }

    @Override
    public int deleteByTitle(String title) {
        Query query = manager.createQuery("delete from Book AS b where b.title= :title");
        query.setParameter("title", title);
        return query.executeUpdate();
    }

    @Override
    public int update(String title, String topic, String firstName, String lastName, String publisher, String place, int year, int pages) {
        Query query = manager.createQuery("update Book set title =:title, topic =:topic, authorFirstName=:firstName,authorLastName=:lastName,publisher=:publisher,place=:place,yearPublished=:year,nrOfPages=:pages where title=:title");
        query.setParameter("title", title).setParameter("topic",topic).setParameter("firstName", firstName).setParameter("lastName",lastName).setParameter("publisher",publisher).setParameter("place",place).setParameter("year",year).setParameter("pages",pages);
        return query.executeUpdate();
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
