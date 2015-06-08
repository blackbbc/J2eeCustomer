package entity;

import javax.persistence.*;
import java.util.HashMap;

/**
 * Created by sweet on 15-6-6.
 */
@Entity
@Table(name = "goods", schema = "", catalog = "j2ee")
public class Goodsentity {
    private int goodsId;
    private int userId;
    private String typeId;
    private String startTime;
    private String name;
    private int quality;
    private int soldNum;
    private int bookNum;
    private String tLimit;
    private float price;
    private String status;
    private String nickname;
    private String signature;
    private String upath;
    private String detail;
    private String img;
    private int tokenOff;

    public Goodsentity(){}

    public Goodsentity(HashMap<String, String> info, Userentity userentity) {
        this.userId = userentity.getUserId();
        Long currentTime = System.currentTimeMillis() / 1000L;
        Long lastTime = Long.parseLong(info.get("t_limit"));
        Long untilTime = currentTime + lastTime * 2592000;
        this.startTime = "" + currentTime;
        this.name = info.get("name");

        System.out.println(this.name);

        this.quality = Integer.parseInt(info.get("num"));
        this.soldNum = 0;
        this.bookNum = 0;
        this.tLimit = "" + untilTime;
        this.price = Float.parseFloat(info.get("price"));
        this.status = "1";
        this.detail = info.get("detail");
        this.tokenOff = 0;
        this.nickname = userentity.getNickname();
        this.signature = userentity.getSignature();
        this.upath = userentity.getPath();
    }

    @Id
    @Column(name = "goods_id", nullable = false, insertable = true, updatable = true)
    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
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
    @Column(name = "type_id", nullable = false, insertable = true, updatable = true, length = 6)
    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    @Basic
    @Column(name = "start_time", nullable = false, insertable = true, updatable = true, length = 20)
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "quality", nullable = false, insertable = true, updatable = true)
    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    @Basic
    @Column(name = "sold_num", nullable = false, insertable = true, updatable = true)
    public int getSoldNum() {
        return soldNum;
    }

    public void setSoldNum(int soldNum) {
        this.soldNum = soldNum;
    }

    @Basic
    @Column(name = "book_num", nullable = false, insertable = true, updatable = true)
    public int getBookNum() {
        return bookNum;
    }

    public void setBookNum(int bookNum) {
        this.bookNum = bookNum;
    }

    @Basic
    @Column(name = "t_limit", nullable = false, insertable = true, updatable = true, length = 20)
    public String gettLimit() {
        return tLimit;
    }

    public void settLimit(String tLimit) {
        this.tLimit = tLimit;
    }

    @Basic
    @Column(name = "price", nullable = false, insertable = true, updatable = true, precision = 2)
    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
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
    @Column(name = "nickname", nullable = false, insertable = true, updatable = true, length = 255)
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Basic
    @Column(name = "signature", nullable = false, insertable = true, updatable = true, length = 10)
    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Basic
    @Column(name = "upath", nullable = false, insertable = true, updatable = true, length = 255)
    public String getUpath() {
        return upath;
    }

    public void setUpath(String upath) {
        this.upath = upath;
    }

    @Basic
    @Column(name = "detail", nullable = false, insertable = true, updatable = true, length = 16777215)
    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Basic
    @Column(name = "img", nullable = true, insertable = true, updatable = true, length = 65535)
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Basic
    @Column(name = "token_off", nullable = false, insertable = true, updatable = true)
    public int getTokenOff() {
        return tokenOff;
    }

    public void setTokenOff(int tokenOff) {
        this.tokenOff = tokenOff;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Goodsentity that = (Goodsentity) o;

        if (goodsId != that.goodsId) return false;
        if (userId != that.userId) return false;
        if (quality != that.quality) return false;
        if (soldNum != that.soldNum) return false;
        if (bookNum != that.bookNum) return false;
        if (Float.compare(that.price, price) != 0) return false;
        if (tokenOff != that.tokenOff) return false;
        if (typeId != null ? !typeId.equals(that.typeId) : that.typeId != null) return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (tLimit != null ? !tLimit.equals(that.tLimit) : that.tLimit != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (nickname != null ? !nickname.equals(that.nickname) : that.nickname != null) return false;
        if (signature != null ? !signature.equals(that.signature) : that.signature != null) return false;
        if (upath != null ? !upath.equals(that.upath) : that.upath != null) return false;
        if (detail != null ? !detail.equals(that.detail) : that.detail != null) return false;
        if (img != null ? !img.equals(that.img) : that.img != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = goodsId;
        result = 31 * result + userId;
        result = 31 * result + (typeId != null ? typeId.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + quality;
        result = 31 * result + soldNum;
        result = 31 * result + bookNum;
        result = 31 * result + (tLimit != null ? tLimit.hashCode() : 0);
        result = 31 * result + (price != +0.0f ? Float.floatToIntBits(price) : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        result = 31 * result + (signature != null ? signature.hashCode() : 0);
        result = 31 * result + (upath != null ? upath.hashCode() : 0);
        result = 31 * result + (detail != null ? detail.hashCode() : 0);
        result = 31 * result + (img != null ? img.hashCode() : 0);
        result = 31 * result + tokenOff;
        return result;
    }
}
