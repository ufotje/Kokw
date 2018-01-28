package be.kokw.bean.digital;

import javax.persistence.*;
import java.io.File;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "digital_traded")
public class DigitalTraded implements Serializable{
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
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "digital_id", referencedColumnName = "id", nullable = false)
    private Digital digital;
    private String depot;
    private String title;
    private String authors;

    public DigitalTraded() {
    }

    public DigitalTraded(String name, String contractNr, File contract, LocalDate contractDate, Digital digital) {
        this.contractNr = contractNr;
        this.name = name;
        this.contract = contract;
        this.contractDate = contractDate;
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
