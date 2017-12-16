package be.kokw.bean;

import javax.persistence.*;
import java.io.File;
import java.io.Serializable;

@Entity
@Table(name = "Gifted_for")
public class GiftedFor implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "Contract_Number")
    private String contractNr;
    @Column(name = "Name")
    private String name;
    @JoinColumn(name = "id_books", referencedColumnName = "id", nullable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Book book;
    @Column(name = "Contract")
    private File contract;

    public GiftedFor() {
    }

    public GiftedFor(String contractNr, String name, Book book) {
        this.contractNr = contractNr;
        this.name = name;
        this.book = book;
    }

    public GiftedFor(String name, String contractNr, File contract, Book book) {
        this.contractNr = contractNr;
        this.name = name;
        this.contract = contract;
        this.book = book;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContractNr() {
        return contractNr;
    }

    public void setContractNr(String contractNr) {
        this.contractNr = contractNr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public File getContract() {
        return contract;
    }

    public void setContract(File contract) {
        this.contract = contract;
    }
}
