package be.kokw.bean;

import be.kokw.bean.books.Book;
import be.kokw.bean.digital.Digital;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by Daniel Demesmaecker.
 */

@Entity
@Table
public class Derated {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Lob
    @Column(length = 78545465)
    private Book book;
    private int bookId;
    @Lob
    @Column(length = 78545465)
    private Digital digital;
    private int digitalId;
    @Column(name = "derateDate")
    private LocalDate derated;
    @Column(name = "destination")
    private String destination;
    @Column(name = "isbn")
    private String isbn;
    @Column(name = "Depot")
    private String depot;
    @Column(name = "Title")
    private String title;
    @Column(name = "authors")
    private String authors;
    private String type;

    public Derated() {
    }

    public Derated(Book book, LocalDate derated, String destination, String isbn, String depot, String title, String authors) {
        this.book = book;
        bookId = book.getId();
        this.derated = derated;
        this.destination = destination;
        this.isbn = isbn;
        this.depot = depot;
        this.title = title;
        this.authors = authors;
        type = "Boek";
    }

    public Derated(Digital digital, LocalDate derated, String destination, String depot, String title, String authors) {
        this.digital = digital;
        digitalId = digital.getId();
        this.derated = derated;
        this.destination = destination;
        this.depot = depot;
        this.title = title;
        this.authors = authors;
        type = "digital";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Digital getDigital() {
        return digital;
    }

    public void setDigital(Digital digital) {
        this.digital = digital;
    }

    public int getDigitalId() {
        return digitalId;
    }

    public void setDigitalId(int digitalId) {
        this.digitalId = digitalId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public LocalDate getDerated() {
        return derated;
    }

    public void setDerated(LocalDate derated) {
        this.derated = derated;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getDepot() {
        return depot;
    }

    public void setDepot(String depot) {
        this.depot = depot;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }
}
