package be.kokw.bean;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by Daniel Demesmaecker.
 */

@Entity
@Table
public class TimeStamp {
    @Id
    @Column(name = "id")
    private final int ID = 1;
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