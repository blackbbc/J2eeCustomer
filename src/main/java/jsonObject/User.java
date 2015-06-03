package jsonObject;

import org.codehaus.jackson.map.annotate.JsonView;

/**
 * Created by sweet on 15-6-2.
 */
public class User {

    int Code;
    String Msg;

    public User(int code, String msg) {
        Code = code;
        Msg = msg;
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
}
