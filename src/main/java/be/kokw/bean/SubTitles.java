package be.kokw.bean;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "subTitles")
public class SubTitles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @ElementCollection(targetClass = String.class)
    private List<String> subTitle;
    @OneToOne(targetEntity = Book.class, mappedBy = "subTitle",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id", referencedColumnName = "id", nullable = false)
    private Book book;

    public SubTitles() {
    }

    public SubTitles(List<String> subTitles) {
        this.subTitle = subTitles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getSubTitles() {
        return subTitle;
    }

    public void setSubTitles(List<String> subTitles) {
        this.subTitle = subTitles;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}