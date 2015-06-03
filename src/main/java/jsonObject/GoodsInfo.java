package jsonObject;

/**
 * Created by sweet on 15-6-3.
 */
public class GoodsInfo {
    String goods_id;
    String type_id;
    String status;
    String user_id;
    String name;
    String t_limit;
    String price;
    int quality;
    String nickname;
    String path;

    public GoodsInfo(String goods_id, String type_id, String status, String user_id, String name, String t_limit, String price, int quality, String nickname, String path) {
        this.goods_id = goods_id;
        this.type_id = type_id;
        this.status = status;
        this.user_id = user_id;
        this.name = name;
        this.t_limit = t_limit;
        this.price = price;
        this.quality = quality;
        this.nickname = nickname;
        this.path = path;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
