package be.kokw.bean;

import be.kokw.bean.books.Book;
import be.kokw.bean.digital.Digital;
import be.kokw.bean.magazines.Magazine;

import javax.persistence.*;

/**
 * Created by Daniel Demesmaecker.
 */

@Table
@Entity
public class Copies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private String title;
    private int nrOfCopies = 1;
    private String type;
    @Lob
    @Column(length = 78545465)
    private Book book;
    @Lob
    @Column(length = 78545465)
    private Digital digital;

    @Lob
    @Column(length = 78545465)
    private Magazine magazine;
    private int volume;

    public Copies() {
    }

    public Copies(String title, String type, int volume, Book book) {
        this.title = title;
        this.type = type;
        this.volume = volume;
        this.book = book;
    }

    public Copies(String title, String type, int volume, Digital digital) {
        this.title = title;
        this.type = type;
        this.volume = volume;
        this.digital = digital;
    }

    public Copies(String title, String type, int volume, Magazine magazine) {
        this.title = title;
        this.type = type;
        this.volume = volume;
        this.magazine = magazine;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNrOfCopies() {
        return nrOfCopies;
    }

    public void setNrOfCopies(int nrOfCopies) {
        this.nrOfCopies = nrOfCopies;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }
}

