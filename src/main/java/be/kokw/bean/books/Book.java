package be.kokw.bean.books;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

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
    @Column(name = "isbn")
    private String isbn;
    @Column(name = "Depot")
    private String depot;
    @Column(name = "Title")
    private String title;
    @Column(name = "subtitles")
    private String subtitles;
    @Column(name = "Edition")
    private int edition;
    @Column(name = "Volume")
    private Integer volume;
    @Column(name = "Publisher")
    private String publisher;
    @Column(name = "Year_Published")
    private int yearPublished;
    @Column(name = "Nr_of_Pages")
    private int nrOfPages;
    @Column(name = "Bought_On")
    private LocalDate boughtOn;
    @Column(name = "Illustrated")
    private boolean illustrated;
    @Column(name = "authors")
    private String authors;
    private String topics;
    private boolean gifted;
    private boolean giftedFor;

    public Book() {
    }

    public Book(String isbn, String depot, String title, String subtitles, int edition, Integer volume, String publisher, int yearPublished, int nrOfPages, boolean illustrated, String authors, String topics) {
        this.isbn = isbn;
        this.depot = depot;
        this.title = title;
        this.subtitles = subtitles;
        this.edition = edition;
        this.volume = volume;
        this.publisher = publisher;
        this.yearPublished = yearPublished;
        this.nrOfPages = nrOfPages;
        this.illustrated = illustrated;
        this.authors = authors;
        this.topics = topics;
    }


    public Book(String isbn, String depot, String title, String subtitles, int edition, Integer volume, String publisher, int yearPublished, int nrOfPages, LocalDate boughtOn, boolean illustrated, String authors, String topics) {
        this.isbn = isbn;
        this.depot = depot;
        this.title = title;
        this.subtitles = subtitles;
        this.edition = edition;
        this.volume = volume;
        this.publisher = publisher;
        this.yearPublished = yearPublished;
        this.nrOfPages = nrOfPages;
        this.boughtOn = boughtOn;
        this.illustrated = illustrated;
        this.authors = authors;
        this.topics = topics;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getDepot() {
        return depot;
    }

    public void setDepot(String depot) {
        this.depot = depot;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitles() {
        return subtitles;
    }

    public void setSubtitles(String subtitles) {
        this.subtitles = subtitles;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
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

    public LocalDate getBoughtOn() {
        return boughtOn;
    }

    public void setBoughtOn(LocalDate boughtOn) {
        this.boughtOn = boughtOn;
    }

    public boolean isIllustrated() {
        return illustrated;
    }

    public void setIllustrated(boolean illustrated) {
        this.illustrated = illustrated;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getTopics() {
        return topics;
    }

    public void setTopics(String topics) {
        this.topics = topics;
    }

    public boolean isGifted() {
        return gifted;
    }

    public void setGifted(boolean gifted) {
        this.gifted = gifted;
    }

    public boolean isGiftedFor() {
        return giftedFor;
    }

    public void setGiftedFor(boolean giftedFor) {
        this.giftedFor = giftedFor;
    }
}
