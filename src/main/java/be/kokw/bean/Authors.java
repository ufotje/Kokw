package be.kokw.bean;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Authors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @OneToOne(targetEntity = Book.class, mappedBy = "authors",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id", referencedColumnName = "id", nullable = false)
    private Book book;
    @ElementCollection(targetClass = String.class)
    private List<String> authors;

    public Authors() {
    }

    public Authors(List<String> authors) {
        this.authors = authors;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }
}
