package be.kokw.repositories.implementations;

import be.kokw.bean.Book;

import be.kokw.repositories.books.BookRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by ufotje on 22/10/2017.
 * The bookrepo implementation
 */

public class BookRepoImpl {//implements BookRepo {
    private EntityManager manager;

  /*  @PersistenceContext
    public void setManager(EntityManager manager) {
        this.manager = manager;

    }

    @Override
    public Book findById(int id) {
        return null;
    }

    @Override
    public Book findByTitle(String title) {
        TypedQuery<Book> query = manager.createQuery("select b from Book b where b.title = :title",Book.class);
        return query.setParameter("title", title).getSingleResult();
    }

    @Override
    public List<Book> findByAuthorsContains(String name) {
        String author = "%" + name + "%";
        TypedQuery<Book> query = manager.createQuery("select b from Book b where b.authors like :name",Book.class);
        return query.setParameter("name", author).getResultList();
    }

    @Override
    public Book findByIsbn(String isbn) {
        TypedQuery<Book> query = manager.createQuery("select b from Book b where b.isbn =:isbn", Book.class);
        return query.setParameter("isbn", isbn).getSingleResult();
    }

    @Override
    public Book findByDepot(String depot) {
        TypedQuery<Book> query = manager.createQuery("select b from Book b where b.depot =:depot", Book.class);
        return query.setParameter("depot", depot).getSingleResult();
    }

    @Override
    public List<Book> findByBoughtOn(LocalDate date) {
        TypedQuery<Book> query = manager.createQuery("select b from Book b where b.boughtOn =:date", Book.class);
        return query.setParameter("date", date).getResultList();
    }

    @Override
    public List<Book> findByBoughtOnBetween(LocalDate start, LocalDate end) {
        TypedQuery<Book> query = manager.createQuery("select b from Book b where b.boughtOn between :start and :endDate", Book.class);
        return query.setParameter("start", start).setParameter("endDate", end).getResultList();
    }

    @Override
    public List<Book> findByBoughtOnExists() {
        TypedQuery<Book> query = manager.createQuery("select b from Book b where b.boughtOn is not null", Book.class);
        return query.getResultList();
    }



    @Override
    public Book findBySubtitlesContains(String SubTitle) {
        return null;
    }

    @Override
    public List<Book> findByPublisher(String publisher){
        TypedQuery<Book> query = manager.createQuery("select b from Book b where b.publisher = :publisher",Book.class);
        return query.setParameter("publisher", publisher).getResultList();
    }

    @Override
    public List<Book> findBookByTopicsContains(String topics){
        String topic = "%" + topics + "%";
        TypedQuery<Book> query = manager.createQuery("select b from Book b where b.topics like :topic",Book.class);
        return query.setParameter("topic", topic).getResultList();
    }

    @Override
    public int deleteByTitle(String title) {
        Query query = manager.createQuery("delete from Book AS b where b.title= :title");
        query.setParameter("title", title);
        return query.executeUpdate();
    }

    @Override
    public int update(int id, String title, String topic, String firstName, String lastName, String publisher, String place, int year, int pages) {
        Query query = manager.createQuery("update Book set title =:title, topic =:topic, authorFirstName=:firstName,authorLastName=:lastName,publisher=:publisher,place=:place,yearPublished=:year,nrOfPages=:pages where id=:id");
        query.setParameter("id", id).setParameter("title", title).setParameter("topic",topic).setParameter("firstName", firstName).setParameter("lastName",lastName).setParameter("publisher",publisher).setParameter("place",place).setParameter("year",year).setParameter("pages",pages);
        return query.executeUpdate();
    }

    @Override
    public int updateDeratedAndDestination( String title){
        Query query = manager.createQuery("update Book set copies = copies-1 where title=:title");
        query.setParameter("title", title);
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
*/}
