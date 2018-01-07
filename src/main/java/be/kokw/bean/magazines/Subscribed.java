package be.kokw.bean.magazines;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
@Entity
@Table
public class Subscribed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @JoinColumn(name = "id_mag", referencedColumnName = "id")
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Magazine mag;
    private String nameMag;
    private String publisher;
    private int count = 0;
    private int expected;
    private boolean completed = false;
    private String contactInfo;
    @Email(message = "*Please provide a valid Email")
    private String email;
    private String tel;

    public Subscribed() {
    }

    public Subscribed(Magazine mag, String contactInfo, int expected, String email, String tel) {
        this.mag = mag;
        nameMag = mag.getName();
        publisher = mag.getPublisher();
        this.contactInfo = contactInfo;
        this.expected = expected;
        this.email = email;
        this.tel = tel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Magazine getMag() {
        return mag;
    }

    public void setMag(Magazine mag) {
        this.mag = mag;
    }

    public String getNameMag() {
        return nameMag;
    }

    public void setNameMag(String nameMag) {
        this.nameMag = nameMag;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getExpected() {
        return expected;
    }

    public void setExpected(int expected) {
        this.expected = expected;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
