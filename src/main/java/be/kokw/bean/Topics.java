package be.kokw.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


//@Entity
//@Table(name = "topics")
public class Topics implements Serializable{
  /*  @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @ElementCollection(targetClass = String.class)
    private List<String> topics;
    @JoinColumn(name = "id_books", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Book book;

    public Topics() {
    }

    public Topics(List<String> topics, Book book) {
        this.topics = topics;
        this.book = book;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
    }*/
}
