package be.kokw.bean;


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
    @Column(name = "Edition")
    private int edition;
    @Column(name = "Copies")
    private int copies;
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
    @Column(name = "Derated")
    private LocalDate derated;
    @Column(name = "Destination")
    private String destination;
    @Column(name = "Illustrated")
    private boolean illustrated;

    public Book() {
    }

    public Book(String isbn, String depot, String title, int edition, int copies, Integer volume, String publisher, int yearPublished, int nrOfPages, boolean illustrated) {
        this.isbn = isbn;
        this.depot = depot;
        this.title = title;
        this.edition = edition;
        this.copies = copies;
        this.volume = volume;
        this.publisher = publisher;
        this.yearPublished = yearPublished;
        this.nrOfPages = nrOfPages;
        this.illustrated = illustrated;
    }

    public Book(String isbn, String depot, String title, int edition, int copies, Integer volume, String publisher, int yearPublished, int nrOfPages, LocalDate boughtOn, boolean illustrated) {
        this.isbn = isbn;
        this.depot = depot;
        this.title = title;
        this.edition = edition;
        this.copies = copies;
        this.volume = volume;
        this.publisher = publisher;
        this.yearPublished = yearPublished;
        this.nrOfPages = nrOfPages;
        this.boughtOn = boughtOn;
        this.illustrated = illustrated;
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

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
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

    public LocalDate getDerated() {
        return derated;
    }

    public void setDerated(LocalDate derated) {
        this.derated = derated;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public boolean isIllustrated() {
        return illustrated;
    }

    public void setIllustrated(boolean illustrated) {
        this.illustrated = illustrated;
    }
}
