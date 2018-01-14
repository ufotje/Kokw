package be.kokw.bean;




import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by ufotje on 8/10/2017.
 * This is the EntityClass for the table members
 */
@Entity
@Table(name = "Members")
public class Member implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    private String firstName;
    private String lastName;
    private String street;
    private int houseNr;
    private int zip;
    private String city;
    @Column(name = "email", unique = true)
    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
    private String email;
    @Column(name = "birthday")
    private LocalDate bDay;
    @Column(name = "Raad_Van_Bestuur")
    private boolean board;
    @Column(name = "Lidgeld_Betaald")
    private boolean payed;
    @Column(name="annalen_Ontvangen")
    private boolean anal;
    private char gender;

    public Member() {
    }

    public Member(String firstName, String lastName, String street, int houseNr, int zip, String city, String email, LocalDate bDay, boolean board, boolean payed, boolean anal, char gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.houseNr = houseNr;
        this.zip = zip;
        this.city = city;
        this.email = email;
        this.bDay = bDay;
        this.board = board;
        this.payed = payed;
        this.anal = anal;
        this.gender = gender;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNr() {
        return houseNr;
    }

    public void setHouseNr(int houseNr) {
        this.houseNr = houseNr;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getbDay() {
        return bDay;
    }

    public void setbDay(LocalDate bDay) {
        this.bDay = bDay;
    }

    public boolean isBoard() {
        return board;
    }

    public void setBoard(boolean board) {
        this.board = board;
    }

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }

    public boolean isAnal() {
        return anal;
    }

    public void setAnal(boolean anal) {
        this.anal = anal;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }
}
