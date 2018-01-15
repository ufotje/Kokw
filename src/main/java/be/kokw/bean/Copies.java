package be.kokw.bean;

import javax.persistence.*;

@Table
@Entity
public class Copies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private String title;
    private int nrOfCopies = 0;
    private String type;

    public Copies() {
    }

    public Copies(String title, String type) {
        this.title = title;
        this.type = type;
        nrOfCopies += 1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNrOfCopies() {
        return nrOfCopies;
    }

    public void setNrOfCopies(int nrOfCopies) {
        this.nrOfCopies = nrOfCopies;
    }
}

