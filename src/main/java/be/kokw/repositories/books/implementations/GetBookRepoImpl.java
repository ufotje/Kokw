package be.kokw.repositories.books.implementations;

import be.kokw.bean.Book;
import be.kokw.repositories.books.interfaces.GetBookRepo;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by ufotje on 20/10/2017.
 */
public class GetBookRepoImpl implements GetBookRepo{

    private EntityManager manager;

    @PersistenceContext
    public void setManager(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public Book findByTitle(String title) {
        TypedQuery<Book> findBookByTitle = manager.createQuery("SELECT b FROM Book b WHERE b.title like :title", Book.class);

        try {
            return findBookByTitle.setParameter("title", title).getSingleResult();
        } catch (NoResultException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Book> findByAuthor(String firstName, String lastName) {
        TypedQuery<Book> findByAuthor= manager.createQuery("SELECT b FROM Book b WHERE b.authorFirstName like :firstName and b.authorLastName like :lastName", Book.class);

        try {
            return findByAuthor.setParameter("firstName", firstName).setParameter("lastName", lastName).getResultList();
        } catch (NoResultException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Book> findByPublisher(String publisher) {
        TypedQuery<Book> findByAuthor= manager.createQuery("SELECT b FROM Book b WHERE b.publisher like :publisher", Book.class);

        try {
            return findByAuthor.setParameter("publisher", publisher).getResultList();
        } catch (NoResultException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Book> findByTopic(String topic) {
        TypedQuery<Book> findByTopic= manager.createQuery("SELECT b FROM Book b WHERE b.topic like :topic", Book.class);

        try {
            return findByTopic.setParameter("topic", topic).getResultList();
        } catch (NoResultException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Book> findByYearPubliced(int year) {
        return null;
    }
}
