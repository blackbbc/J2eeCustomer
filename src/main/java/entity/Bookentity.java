package entity;

import javax.persistence.*;

/**
 * Created by sweet on 15-6-10.
 */
@Entity
@Table(name = "book", schema = "", catalog = "j2ee")
public class Bookentity {
    private int bookId;
    private String bMid;
    private String delivery;
    private String fTime;
    private String time;
    private String status;
    private String schoolId;
    private int userId;

    public Bookentity(){}

    public Bookentity(int userId, String schoolId) {
        this.userId = userId;
        this.bMid = "000000";
        this.delivery = "";
        this.fTime = "0";
        this.time = "" + System.currentTimeMillis() / 1000;
        this.status = "1";
        this.schoolId = schoolId;
    }

    @Id
    @Column(name = "book_id", nullable = false, insertable = true, updatable = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Basic
    @Column(name = "b_mid", nullable = false, insertable = true, updatable = true, length = 20)
    public String getbMid() {
        return bMid;
    }

    public void setbMid(String bMid) {
        this.bMid = bMid;
    }

    @Basic
    @Column(name = "delivery", nullable = false, insertable = true, updatable = true, length = 255)
    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    @Basic
    @Column(name = "f_time", nullable = false, insertable = true, updatable = true, length = 20)
    public String getfTime() {
        return fTime;
    }

    public void setfTime(String fTime) {
        this.fTime = fTime;
    }

    @Basic
    @Column(name = "time", nullable = false, insertable = true, updatable = true, length = 20)
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Basic
    @Column(name = "status", nullable = false, insertable = true, updatable = true, length = 10)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "school_id", nullable = false, insertable = true, updatable = true, length = 20)
    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bookentity that = (Bookentity) o;

        if (bookId != that.bookId) return false;
        if (bMid != null ? !bMid.equals(that.bMid) : that.bMid != null) return false;
        if (delivery != null ? !delivery.equals(that.delivery) : that.delivery != null) return false;
        if (fTime != null ? !fTime.equals(that.fTime) : that.fTime != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (schoolId != null ? !schoolId.equals(that.schoolId) : that.schoolId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bookId;
        result = 31 * result + (bMid != null ? bMid.hashCode() : 0);
        result = 31 * result + (delivery != null ? delivery.hashCode() : 0);
        result = 31 * result + (fTime != null ? fTime.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (schoolId != null ? schoolId.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "user_id", nullable = false, insertable = true, updatable = true)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
