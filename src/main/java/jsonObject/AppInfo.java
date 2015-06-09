package jsonObject;

import entity.Applicationentity;
import entity.Goodsentity;

import java.util.Random;

/**
 * Created by sweet on 15-6-9.
 */
public class AppInfo {
    String detail;
    String name;
    String num;
    String price;
    String process_id;
    String status;
    String t_limit;
    String time;

    public AppInfo(Goodsentity goodsentity) {
        this.detail = goodsentity.getDetail();
        this.name = goodsentity.getName();
        this.num = "" + goodsentity.getQuality();
        this.price = String.format("%2f", goodsentity.getPrice());
        this.process_id = "1"+ new Random().nextInt();
        this.status = goodsentity.getStatus();
        this.t_limit = goodsentity.gettLimit();
        this.time = goodsentity.getStartTime();
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProcess_id() {
        return process_id;
    }

    public void setProcess_id(String process_id) {
        this.process_id = process_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getT_limit() {
        return t_limit;
    }

    public void setT_limit(String t_limit) {
        this.t_limit = t_limit;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
