package be.kokw.bean;

import be.kokw.bean.books.Book;
import be.kokw.bean.digital.Digital;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by Daniel Demesmaecker.
 */

@Entity
@Table(name = "checked_out_books")
public class CheckedOut implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @JoinColumn(name = "id_books", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Book book;
    @JoinColumn(name = "id_digital", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Digital digital;
    @JoinColumn(name = "id_members", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Member member;
    private String title;
    private String email;
    private String fullName;
    private LocalDate checkOutDate;
    private LocalDate returnDate;
    private boolean returned;
    private String adress;

    public CheckedOut() {
    }

    public CheckedOut(Book book, Member member) {
        this.book = book;
        this.member = member;
        checkOutDate = LocalDate.now();
        returnDate = checkOutDate.plusWeeks(3);
        title = book.getTitle();
        email = member.getEmail();
        fullName = member.getFirstName() + " " + member.getLastName();
        adress = member.getStreet() + " " + member.getHouseNr() + "\n" + member.getZip() + " " + member.getCity();
    }

    public CheckedOut(Digital digital, Member member) {
        this.digital = digital;
        this.member = member;
        checkOutDate = LocalDate.now();
        returnDate = checkOutDate.plusWeeks(3);
        title = book.getTitle();
        email = member.getEmail();
        fullName = member.getFirstName() + " " + member.getLastName();
        adress = member.getStreet() + " " + member.getHouseNr() + "\n" + member.getZip() + " " + member.getCity();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Digital getDigital() {
        return digital;
    }

    public void setDigital(Digital digital) {
        this.digital = digital;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}

