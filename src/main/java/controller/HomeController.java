package controller;

import jsonObject.Car;
import jsonObject.Login;
import jsonObject.LoginInfo;
import jsonObject.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.Null;

/**
 * Created by sweet on 15-5-27.
 */
@Controller
@RequestMapping(value = "/Home")
public class HomeController {

    @RequestMapping(value = "/User.json", method = RequestMethod.POST)
    @ResponseBody
    public User getUser() {
        User user = new User(0, "操作成功");

        return user;
    }

    @RequestMapping(value = "/Login.json", method = RequestMethod.POST)
    @ResponseBody
    public Login getLogin() {
        LoginInfo info = new LoginInfo("1432650083", 92, "505968815@qq.com", "oO\\u5e7b\\u7edd", "\\/Uploads\\/Uimg\\/default.jpeg");
        Login login = new Login(0, "操作成功", info);

        return login;
    }

    @RequestMapping(value = "/Car.json", method = RequestMethod.GET)
    @ResponseBody
    public Car getCar() {
        Car car = new Car(0, "操作成功", null);


        return car;
    }

}
