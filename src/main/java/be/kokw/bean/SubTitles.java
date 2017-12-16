package be.kokw.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "subTitles")
public class SubTitles implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @ElementCollection(targetClass = String.class)
    private List<String> subTitles;
    @JoinColumn(name = "id_books", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Book book;

    public SubTitles() {
    }

    public SubTitles(List<String> subTitles, Book book) {
        this.book = book;
        this.subTitles = subTitles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<String> getSubTitles() {
        return subTitles;
    }

    public void setSubTitles(List<String> subTitles) {
        this.subTitles = subTitles;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}