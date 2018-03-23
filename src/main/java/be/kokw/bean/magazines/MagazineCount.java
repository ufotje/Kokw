package be.kokw.bean.magazines;

import javax.persistence.*;

/**
 * Created by Daniel Demesmaecker.
 */

@Entity
@Table
public class MagazineCount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private String name;
    private int expected;
    private int received = 1;
    private boolean traded = false;
    private boolean subscribed = false;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "trade_id", referencedColumnName = "id")
    private Trade trade;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "subscribed_id", referencedColumnName = "id")
    private Subscribed subscribtion;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "magazine_id", referencedColumnName = "id")
    private Magazine magazine;

    public MagazineCount() {
    }

    public MagazineCount(String name, int expected) {
        this.name = name;
        this.expected = expected;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getExpected() {
        return expected;
    }

    public void setExpected(int expected) {
        this.expected = expected;
    }

    public int getReceived() {
        return received;
    }

    public void setReceived(int received) {
        this.received = received;
    }

    public boolean isTraded() {
        return traded;
    }

    public void setTraded(boolean traded) {
        this.traded = traded;
    }

    public boolean isSubscribed() {
        return subscribed;
    }

    public void setSubscribed(boolean subscribed) {
        this.subscribed = subscribed;
    }

    public Trade getTrade() {
        return trade;
    }

    public void setTrade(Trade trade) {
        this.trade = trade;
    }

    public Subscribed getSubscribtion() {
        return subscribtion;
    }

    public void setSubscribtion(Subscribed subscribtion) {
        this.subscribtion = subscribtion;
    }

    public Magazine getMagazine() {
        return magazine;
    }

    public void setMagazine(Magazine magazine) {
        this.magazine = magazine;
    }
}
