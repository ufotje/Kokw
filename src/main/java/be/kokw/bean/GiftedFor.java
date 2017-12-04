package be.kokw.bean;

import javax.persistence.*;
import java.io.File;

@Entity
@Table(name = "Gifted_for")
public class GiftedFor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "Contract_Number")
    private String contractNr;
    @Column(name = "Name")
    private String name;
    @OneToOne(mappedBy = "Gifted_for", cascade = CascadeType.ALL)
    private Book book;
    @Column(name = "Contract")
    private File contract;

    public GiftedFor() {
    }

    public GiftedFor(String name, String contractNr, File contract) {
        this.contractNr = contractNr;
        this.name = name;
        this.contract = contract;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
