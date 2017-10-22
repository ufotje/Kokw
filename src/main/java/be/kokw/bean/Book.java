package be.kokw.bean;


import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ufotje on 4/10/2017.
 */

@Entity
@Table(name = "Books")
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    @Column(name = "Title")
    String title;
    @Column(name = "Topic")
    String topic;
    @Column(name = "Firstname Author")
    String authorFirstName;
    @Column(name = "Lastname Author")
    String authorLastName;
    @Column(name = "Publisher")
    String publisher;
    @Column(name = "Place")
    String place;

    @Column(name = "Year Published")
    int yearPublished;
    @Column(name = "Nr of Pages")
    int nrOfPages;

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

    public Book() {
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

    public String getPublicher() {
        return publisher;
    }

    public void setPublisher(String publicer) {
        this.publisher = publicer;
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

    public void setYearPublished(int yearPubliced) {
        this.yearPublished = yearPubliced;
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
                ", yearPubliced=" + yearPublished +
                ", nrOfPages=" + nrOfPages +
                '}';
    }
}
