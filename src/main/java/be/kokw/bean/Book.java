package be.kokw.bean;


import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ufotje on 4/10/2017.
 * This is the EntityClass for Table Books
 */

@Entity
@Table(name = "Books")
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "Title")
    private String title;
    @Column(name = "Topic")
    private String topic;
    @Column(name = "FirstName_Author")
    private String authorFirstName;
    @Column(name = "LastName_Author")
    private String authorLastName;
    @Column(name = "Publisher")
    private String publisher;
    @Column(name = "Place")
    private String place;
    @Column(name = "Year_Published")
    private int yearPublished;
    @Column(name = "Nr_of_Pages")
    private int nrOfPages;

    public Book() {
    }

    public Book(String title, String topic, String authorFirstName, String authorLastName, String publisher, String place, int yearPublished, int nrOfPages) {
        this.title = title;
        this.topic = topic;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.publisher = publisher;
        this.place = place;
        this.yearPublished = yearPublished;
        this.nrOfPages = nrOfPages;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }

    public int getNrOfPages() {
        return nrOfPages;
    }

    public void setNrOfPages(int nrOfPages) {
        this.nrOfPages = nrOfPages;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", topic='" + topic + '\'' +
                ", authorFirstName='" + authorFirstName + '\'' +
                ", authorLastName='" + authorLastName + '\'' +
                ", publisher='" + publisher + '\'' +
                ", yearPublished=" + yearPublished +
                ", nrOfPages=" + nrOfPages +
                '}';
    }
}
