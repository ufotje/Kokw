package be.kokw.bean.digital;

import be.kokw.bean.Member;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "checked_out_digital")
public class CheckOutDigi{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @JoinColumn(name = "id_digital", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Digital digital;
    @JoinColumn(name = "id_members", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Member member;
    private String title;
    private String address;
    private String email;
    private String fullName;
    private LocalDate checkOutDate;
    private LocalDate returnDate;
    private boolean returned;

    public CheckOutDigi() {
    }

    public CheckOutDigi(Digital digital, Member member) {
        this.digital = digital;
        this.member = member;
        checkOutDate = LocalDate.now();
        returnDate = checkOutDate.plusWeeks(3);
        title = digital.getTitle();
        email = member.getEmail();
        fullName = member.getFirstName() + " " + member.getLastName();
        address = member.getStreet() + " " + member.getHouseNr() + "\n" + member.getZip() + " " + member.getCity();
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Digital getDigital() {
        return digital;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
}

