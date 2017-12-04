package be.kokw.bean;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table
public class Topics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private List<String> topics = new ArrayList<>();
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Book book;

    public Topics() {
    }

    public Topics(List<String> topics) {
        this.topics = topics;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
