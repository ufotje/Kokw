package be.kokw.repositories.books.implementations;

import be.kokw.bean.Book;
import be.kokw.repositories.books.interfaces.BookRepo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ufotje on 22/10/2017.
 */
@Repository("bookRepo")
public class BookRepoImpl implements BookRepo {
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
        return s;
    }

    @Override
    public <S extends Book> Iterable<S> save(Iterable<S> iterable) {
        return iterable;
    }

    @Override
    public Book findOne(Integer integer) {
        return null;
    }

    @Override
    public boolean exists(Integer integer) {
        return false;
    }

    @Override
    public Iterable<Book> findAll() {
        return null;
    }

    @Override
    public Iterable<Book> findAll(Iterable<Integer> iterable) {
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
    public void delete(Book book) {

    }

    @Override
    public void delete(Iterable<? extends Book> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}
