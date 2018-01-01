package be.kokw.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Table(name = "Gifted")
@Entity
public class Gifted implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    private String name;
    private LocalDate giftedOn;
    @OneToOne(targetEntity = Book.class, mappedBy = "giftedBy",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id", referencedColumnName = "id", nullable = false)
    private Book book;
    private String issbn;
    private String depot;
    private String title;
    private String authors;

    public Gifted() {
    }

    public Gifted(String name, LocalDate giftedOn, Book book ) {
        this.name = name;
        this.giftedOn = giftedOn;
        this.book = book;
        issbn = book.getIsbn();
        depot = book.getDepot();
        title = book.getTitle();
        authors = book.getAuthors();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public LocalDate getGiftedOn() {
        return giftedOn;
    }

    public void setGiftedOn(LocalDate giftedOn) {
        this.giftedOn = giftedOn;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getIssbn() {
        return issbn;
    }

    public void setIssbn(String issbn) {
        this.issbn = issbn;
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
