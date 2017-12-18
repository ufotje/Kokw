package be.kokw.bean;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table
public class Authors implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @JoinColumn(name = "id_books", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Book book;
    @ElementCollection(targetClass = String.class)
    private List<String> authors;

    public Authors() {
    }

    public Authors(Book book, List<String> authors) {
        this.book = book;
        this.authors = authors;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
