package controller;

import entity.Userentity;
import jsonObject.LoginInfo;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.LoginService;
import service.RegisterService;

import javax.servlet.ServletContext;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sweet on 15-5-27.
 */
@Controller
@RequestMapping(value = "/Home")
public class HomeController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private RegisterService registerService;

    @RequestMapping(value = "/User.json", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getUser(
            @RequestParam(value = "email") String email,
            @RequestParam(value = "nickname") String nickname,
            @RequestParam(value = "password") String password) {
        Map<String, Object> result = new HashMap();

        Userentity user = registerService.getUserByEmail(email);
        if (user != null) {
            result.put("Code", 1003);
            result.put("Msg", "邮箱已被使用");
        } else {
            user = registerService.getUserByNickname(nickname);
            if (user != null) {
                result.put("Code", 1002);
                result.put("Msg", "该昵称已被使用");
            } else {
                if (registerService.createUser(email, nickname, password)) {
                    result.put("Code", 0);
                    result.put("Msg", "操作成功");
                } else {
                    result.put("Code", 3003);
                    result.put("Msg", "注册失败");
                }
            }
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");

        return new ResponseEntity<Map<String, Object>>(result, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/Login.json", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getLogin(
            @RequestParam(value = "email") String email,
            @RequestParam(value = "password") String password) {
        Map<String, Object> result = new HashMap();
        Userentity user = loginService.getUserByEmail(email);

        if (user == null) {
            result.put("Code", 1005);
            result.put("Msg", "用户不存在");
            result.put("Info", null);
        } else if (!user.getPassword().equals(password)) {
            result.put("Code", 1005);
            result.put("Msg", "密码错误");
            result.put("Info", null);
        } else if (!user.getStatus().equals("2")) {
            result.put("Code", 1005);
            result.put("Msg", "用户未验证");
            result.put("Info", null);
        } else {
            LoginInfo info = new LoginInfo(user);
            result.put("Code", 0);
            result.put("Msg", "操作成功");
            result.put("Info", info);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");

        return new ResponseEntity<Map<String, Object>>(result, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/Car.json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getCar() {
        Map<String, Object> result = new HashMap();
        result.put("Code", 0);
        result.put("Msg", "操作成功");
        result.put("Info", null);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");

        return new ResponseEntity<Map<String, Object>>(result, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/Test.json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getTest(@RequestParam(value = "test", required = false) String test) {

        String pass = "admin";
        String token = DigestUtils.md5DigestAsHex(pass.getBytes());

        Map<String, Object> result = new HashMap();
        result.put("Code", 0);
        result.put("Msg", "操作成功");
        result.put("test", test);
        result.put("token", token);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");

        return new ResponseEntity<Map<String, Object>>(result, headers, HttpStatus.OK);
    }

}
