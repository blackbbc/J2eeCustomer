package jsonObject;

import com.google.gson.Gson;
import entity.Goodsentity;

import java.util.ArrayList;

/**
 * Created by sweet on 15-6-6.
 */
public class LatestInfo {
    String type_id;
    int start_time;
    int t_limit;
    float price;
    String path;
    String nickname;
    int user_id;
    String name;
    int goods_id;
    int quality;
    String upath;

    public LatestInfo(Goodsentity goodsentity) {
        Gson gson = new Gson();

        this.type_id = goodsentity.getTypeId();
        this.start_time = Integer.parseInt(goodsentity.getStartTime());
        this.t_limit = Integer.parseInt(goodsentity.gettLimit());
        this.price = goodsentity.getPrice();

        ArrayList<String> img = gson.fromJson(goodsentity.getImg(), ArrayList.class);
        this.path = img.get(0);

        this.nickname = goodsentity.getNickname();
        this.user_id = goodsentity.getUserId();
        this.name = goodsentity.getName();
        this.goods_id = goodsentity.getGoodsId();
        this.quality = goodsentity.getQuality();
        this.upath = goodsentity.getUpath();
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public int getStart_time() {
        return start_time;
    }

    public void setStart_time(int start_time) {
        this.start_time = start_time;
    }

    public int getT_limit() {
        return t_limit;
    }

    public void setT_limit(int t_limit) {
        this.t_limit = t_limit;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public String getUpath() {
        return upath;
    }

    public void setUpath(String upath) {
        this.upath = upath;
    }
}
