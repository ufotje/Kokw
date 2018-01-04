package be.kokw.bean;

import javax.persistence.*;

@Entity
public class Trade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @JoinColumn(name = "id_mag", referencedColumnName = "id")
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Magazine mag;
}
