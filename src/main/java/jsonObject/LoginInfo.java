package jsonObject;

import entity.Userentity;

/**
 * Created by sweet on 15-6-2.
 */
public class LoginInfo {
    String reg_time;
    String status = "2";
    int user_id;
    String telephone = "00000000000";
    String email;
    String nickname;
    String school_id = "000000";
    String birthday = "0";
    String gender = "B";
    String alipay = "";
    String name = "";
    String path;
    String sigature = "";

    public LoginInfo(Userentity user) {
        //必需的
        this.reg_time = user.getRegTime();
        this.status = user.getStatus();
        this.user_id = user.getUserId();
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.path = user.getPath();
        //可选的
        this.telephone = user.getTelephone() == null? "00000000000": user.getTelephone();
        this.school_id = user.getSchoolId() == null? "000000": user.getSchoolId();
        this.birthday = user.getSchoolId() == null? "0": user.getBirthday();
        this.gender = user.getGender() == null? "B": user.getGender();
        this.alipay = "";
        this.name = user.getName() == null? "": user.getName();
        this.sigature = "";
    }

    public LoginInfo(String reg_time, int user_id, String email, String nickname, String path) {
        this.reg_time = reg_time;
        this.user_id = user_id;
        this.email = email;
        this.nickname = nickname;
        this.path = path;
    }

    public String getReg_time() {
        return reg_time;
    }

    public void setReg_time(String reg_time) {
        this.reg_time = reg_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSchool_id() {
        return school_id;
    }

    public void setSchool_id(String school_id) {
        this.school_id = school_id;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAlipay() {
        return alipay;
    }

    public void setAlipay(String alipay) {
        this.alipay = alipay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSigature() {
        return sigature;
    }

    public void setSigature(String sigature) {
        this.sigature = sigature;
    }
}
