package jsonObject;

import java.util.ArrayList;

/**
 * Created by sweet on 15-6-3.
 */
public class CarInfo {
    String tokenOff;
    ArrayList<GoodsInfo> car;

    public CarInfo(String tokenOff, ArrayList<GoodsInfo> car) {
        this.tokenOff = tokenOff;
        this.car = car;
    }

    public String getTokenOff() {
        return tokenOff;
    }

    public void setTokenOff(String tokenOff) {
        this.tokenOff = tokenOff;
    }

    public ArrayList<GoodsInfo> getCar() {
        return car;
    }

    public void setCar(ArrayList<GoodsInfo> car) {
        this.car = car;
    }
}
