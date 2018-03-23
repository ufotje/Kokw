package be.kokw.bean.magazines;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;

@Entity
@Table

/**
 * Created by Daniel Demesmaecker.
 */

public class Trade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @JoinColumn(name = "id_mag", referencedColumnName = "id")
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Magazine mag;
    private String nameMag;
    private String nameOrg;
    private int count = 0;
    private int expected;
    private boolean completedUs = false;
    private boolean completedThem = false;
    private String contactInfo;
    @Email(message = "*Please provide a valid Email")
    private String email;
    private String tel;

    public Trade() {
    }

    public Trade(Magazine mag, String nameOrg, int expected, String contactInfo, String email, String tel) {
        this.mag = mag;
        this.nameMag = mag.getName();
        this.nameOrg = nameOrg;
        this.expected = expected;
        this.contactInfo = contactInfo;
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

    public String getNameOrg() {
        return nameOrg;
    }

    public void setNameOrg(String nameOrg) {
        this.nameOrg = nameOrg;
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

    public boolean isCompletedUs() {
        return completedUs;
    }

    public void setCompletedUs(boolean completedUs) {
        this.completedUs = completedUs;
    }

    public boolean isCompletedThem() {
        return completedThem;
    }

    public void setCompletedThem(boolean completedThem) {
        this.completedThem = completedThem;
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
