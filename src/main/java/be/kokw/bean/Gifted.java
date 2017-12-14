package be.kokw.bean;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "Gifted")
@Entity
public class Gifted {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private String firstName;
    private String lastName;
    private LocalDate giftedOn;
    @JoinColumn(name = "id_books", referencedColumnName = "id", nullable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Book book;

    public Gifted() {
    }

    public Gifted(String firstName, String lastName, LocalDate giftedOn) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.giftedOn = giftedOn;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getGiftedOn() {
        return giftedOn;
    }

    public void setGiftedOn(LocalDate giftedOn) {
        this.giftedOn = giftedOn;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
