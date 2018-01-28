package be.kokw.bean.digital;

import javax.enterprise.inject.TransientReference;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Table(name = "digital_donated")
@Entity
public class DigitalDonated implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    private String name;
    private LocalDate giftedOn;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "digital_id", referencedColumnName = "id", nullable = false)
    private Digital digital;
    private String depot;
    private String title;
    private String authors;

    public DigitalDonated() {
    }

    public DigitalDonated(String name, LocalDate giftedOn, Digital digital ) {
        this.name = name;
        this.giftedOn = giftedOn;
        this.digital = digital;
        depot = digital.getDepot();
        title = digital.getTitle();
        authors = digital.getAuthors();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public LocalDate getGiftedOn() {
        return giftedOn;
    }

    public void setGiftedOn(LocalDate giftedOn) {
        this.giftedOn = giftedOn;
    }

    public Digital getDigital() {
        return digital;
    }

    public void setDigital(Digital digital) {
        this.digital = digital;
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
