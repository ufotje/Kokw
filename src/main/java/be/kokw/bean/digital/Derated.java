package be.kokw.bean.digital;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by Daniel Demesmaecker.
 */

@Entity
@Table
public class Derated {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Lob
    @Column(length = 78545465)
    private Digital digital;
    private int digitalId;
    @Column(name = "derateDate")
    private LocalDate derated;
    @Column(name = "destination")
    private String destination;
    @Column(name = "Depot")
    private String depot;
    @Column(name = "Title")
    private String title;
    @Column(name = "authors")
    private String authors;

    public Derated() {
    }

    public Derated(Digital digital, LocalDate derated, String destination, String depot, String title, String authors) {
        this.digital = digital;
        digitalId = digital.getId();
        this.derated = derated;
        this.destination = destination;
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

    public Digital getDigital() {
        return digital;
    }

    public void setDigital(Digital digital) {
        this.digital = digital;
    }

    public int getDigitalId() {
        return digitalId;
    }

    public void setDigitalId(int digitalId) {
        this.digitalId = digitalId;
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
