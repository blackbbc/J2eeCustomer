package controller;

import jsonObject.Car;
import jsonObject.Login;
import jsonObject.LoginInfo;
import jsonObject.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.Null;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sweet on 15-5-27.
 */
@Controller
@RequestMapping(value = "/Home")
public class HomeController {

    @RequestMapping(value = "/User.json", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getUser() {
        Map<String, Object> result = new HashMap();
        result.put("Code", 0);
        result.put("Msg", "操作成功");

        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/Login.json", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getLogin() {
        LoginInfo info = new LoginInfo("1432650083", 92, "505968815@qq.com", "oO\\u5e7b\\u7edd", "\\/Uploads\\/Uimg\\/default.jpeg");
        Map<String, Object> result = new HashMap();
        result.put("Code", 0);
        result.put("Msg", "操作成功");
        result.put("Info", info);

        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/Car.json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getCar() {
        Map<String, Object> result = new HashMap();
        result.put("Code", 0);
        result.put("Msg", "操作成功");
        result.put("Info", null);

        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/Test.json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getTest() {
        Map<String, Object> result = new HashMap();
        result.put("Code", 0);
        result.put("Msg", "操作成功");

        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

}
