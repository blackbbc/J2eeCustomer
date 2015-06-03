package jsonObject;

/**
 * Created by sweet on 15-6-3.
 */
public class Car {
    int Code;
    String Msg;
    GoodsInfo Info;

    public Car(int code, String msg, GoodsInfo info) {
        Code = code;
        Msg = msg;
        Info = info;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    public GoodsInfo getInfo() {
        return Info;
    }

    public void setInfo(GoodsInfo info) {
        Info = info;
    }
}
