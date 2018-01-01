package be.kokw.bean;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class Derated {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "derateDate")
    private LocalDate derated;
    @Column(name = "destination")
    private String destination;
    @Column(name = "isbn")
    private String isbn;
    @Column(name = "Depot")
    private String depot;
    @Column(name = "Title")
    private String title;
    @Column(name = "authors")
    private String authors;

    public Derated() {
    }

    public Derated(LocalDate derated, String destination, String isbn, String depot, String title, String authors) {
        this.derated = derated;
        this.destination = destination;
        this.isbn = isbn;
        this.depot = depot;
        this.title = title;
        this.authors = authors;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }
}
