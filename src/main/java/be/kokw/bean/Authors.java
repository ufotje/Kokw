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
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Book book;
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
