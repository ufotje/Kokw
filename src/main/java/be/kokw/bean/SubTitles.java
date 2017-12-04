package be.kokw.bean;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class SubTitles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private List<String> subTitles = new ArrayList<>();
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Book book;

    public SubTitles() {
    }

    public SubTitles(List<String> subTitles) {
        this.subTitles = subTitles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
