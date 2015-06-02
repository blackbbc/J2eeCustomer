package jsonObject;

/**
 * Created by sweet on 15-6-2.
 */
public class Login {
    int Code;
    String Msg;
    LoginInfo Info;

    public Login(int code, String msg, LoginInfo info) {
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

    public LoginInfo getInfo() {
        return Info;
    }

    public void setInfo(LoginInfo info) {
        Info = info;
    }
}
