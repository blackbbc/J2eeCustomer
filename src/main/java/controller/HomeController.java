package controller;

import jsonObject.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by sweet on 15-5-27.
 */
@Controller
public class HomeController {

    @RequestMapping(value = "/Home/qqq.json", method = RequestMethod.GET)
    @ResponseBody
    public User getUser() {
        User user = new User("test", "test");

        return user;
    }

}
