package entity;

import javax.persistence.*;

/**
 * Created by sweet on 15-6-7.
 */
@Entity
@Table(name = "application", schema = "", catalog = "j2ee")
public class Applicationentity {
    private int appId;
    private String bNo;
    private String no;
    private String reason;
    private String status;
    private String time;
    private int userId;
    private String goodsId;

    public Applicationentity(){}

    public Applicationentity(int userId, String bNo, String NO, String goodsId) {
        this.bNo = bNo;
        this.no = NO;
        this.reason = "";
        this.status = "1";
        this.time = "" + System.currentTimeMillis() / 1000;
        this.userId = userId;
        this.goodsId = goodsId;
    }

    @Id
    @Column(name = "app_id", nullable = false, insertable = true, updatable = true)
    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    @Basic
    @Column(name = "b_NO", nullable = false, insertable = true, updatable = true, length = 20)
    public String getbNo() {
        return bNo;
    }

    public void setbNo(String bNo) {
        this.bNo = bNo;
    }

    @Basic
    @Column(name = "NO", nullable = false, insertable = true, updatable = true, length = 20)
    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    @Basic
    @Column(name = "reason", nullable = false, insertable = true, updatable = true, length = 65535)
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Basic
    @Column(name = "status", nullable = false, insertable = true, updatable = true, length = 20)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
    @Column(name = "user_id", nullable = false, insertable = true, updatable = true)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "goods_id", nullable = false, insertable = true, updatable = true, length = 255)
    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Applicationentity that = (Applicationentity) o;

        if (appId != that.appId) return false;
        if (userId != that.userId) return false;
        if (bNo != null ? !bNo.equals(that.bNo) : that.bNo != null) return false;
        if (no != null ? !no.equals(that.no) : that.no != null) return false;
        if (reason != null ? !reason.equals(that.reason) : that.reason != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        if (goodsId != null ? !goodsId.equals(that.goodsId) : that.goodsId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = appId;
        result = 31 * result + (bNo != null ? bNo.hashCode() : 0);
        result = 31 * result + (no != null ? no.hashCode() : 0);
        result = 31 * result + (reason != null ? reason.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + userId;
        result = 31 * result + (goodsId != null ? goodsId.hashCode() : 0);
        return result;
    }
}
