package be.kokw.bean;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class TimeStamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    private LocalDate last;

    public TimeStamp() {
    }

    public TimeStamp(LocalDate last) {
        this.last = last;
    }

    public LocalDate getLast() {
        return last;
    }

    public void setLast(LocalDate last) {
        this.last = last;
    }

}