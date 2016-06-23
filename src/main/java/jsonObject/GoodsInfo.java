package jsonObject;

import com.google.gson.Gson;
import entity.Goodsentity;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by sweet on 15-6-3.
 */
public class GoodsInfo {
    String goods_id;
    String type_id;
    String start_time;
    String name;
    String quality;
    String sold_num;
    String book_num;
    String t_limit;
    String price;
    String status;
    String user_id;
    String nickname;
    String signature;
    String upath;
    String detail;
    int token_off;
    ArrayList<HashMap<String, String>> img;

    public GoodsInfo(Goodsentity goodsentity) {
        Gson gson = new Gson();

        this.goods_id = "" + goodsentity.getGoodsId();
        this.type_id = goodsentity.getTypeId();
        this.start_time = goodsentity.getStartTime();
        this.name = goodsentity.getName();
        this.quality = "" + goodsentity.getQuality();
        this.sold_num = "" + goodsentity.getSoldNum();
        this.book_num = "" + goodsentity.getBookNum();
        this.t_limit = goodsentity.gettLimit();
        this.price = String.format("%.2f", goodsentity.getPrice());
        this.status = goodsentity.getStatus();
        this.user_id = "" + goodsentity.getUserId();
        this.nickname = goodsentity.getNickname();
        this.signature = goodsentity.getSignature();
        this.upath = goodsentity.getUpath();
        this.detail = goodsentity.getDetail();
        this.token_off = goodsentity.getTokenOff();

        ArrayList<String> arr = gson.fromJson(goodsentity.getImg(), ArrayList.class);
        this.img = new ArrayList<HashMap<String, String>>();

        for (String path:arr) {
            HashMap<String, String> entry = new HashMap<String, String>();
            entry.put("path", path);
            this.img.add(entry);
        }
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getSold_num() {
        return sold_num;
    }

    public void setSold_num(String sold_num) {
        this.sold_num = sold_num;
    }

    public String getBook_num() {
        return book_num;
    }

    public void setBook_num(String book_num) {
        this.book_num = book_num;
    }

    public String getT_limit() {
        return t_limit;
    }

    public void setT_limit(String t_limit) {
        this.t_limit = t_limit;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getUpath() {
        return upath;
    }

    public void setUpath(String upath) {
        this.upath = upath;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getToken_off() {
        return token_off;
    }

    public void setToken_off(int token_off) {
        this.token_off = token_off;
    }

    public ArrayList<HashMap<String, String>> getImg() {
        return img;
    }

    public void setImg(ArrayList<HashMap<String, String>> img) {
        this.img = img;
    }
}
