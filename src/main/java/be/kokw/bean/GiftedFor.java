package be.kokw.bean;

import javax.persistence.*;
import java.io.File;
import java.io.Serializable;
import java.time.LocalDate;

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
    @Column(name = "Contract")
    private File contract;
    private LocalDate contractDate;
    @OneToOne(targetEntity = Book.class, mappedBy = "giftedFor",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id", referencedColumnName = "id", nullable = false)
    private Book book;
    private String issbn;
    private String depot;
    private String title;
    private String authors;

    public GiftedFor() {
    }

    public GiftedFor(String contractNr, String name, LocalDate contractDate, Book book) {
        this.contractNr = contractNr;
        this.name = name;
        this.contractDate = contractDate;
        this.book = book;
        issbn = book.getIsbn();
        depot = book.getDepot();
        title = book.getTitle();
        authors = book.getAuthors();
    }

    public GiftedFor(String name, String contractNr, File contract, LocalDate contractDate, Book book) {
        this.contractNr = contractNr;
        this.name = name;
        this.contract = contract;
        this.contractDate = contractDate;
        this.book = book;
        issbn = book.getIsbn();
        depot = book.getDepot();
        title = book.getTitle();
        authors = book.getAuthors();
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

    public File getContract() {
        return contract;
    }

    public void setContract(File contract) {
        this.contract = contract;
    }

    public LocalDate getContractDate() {
        return contractDate;
    }

    public void setContractDate(LocalDate contractDate) {
        this.contractDate = contractDate;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getIssbn() {
        return issbn;
    }

    public void setIssbn(String issbn) {
        this.issbn = issbn;
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
