package controller;

import entity.Goodsentity;
import entity.Userentity;
import entity.VerifyEmailentity;
import jsonObject.GoodsInfo;
import jsonObject.LatestInfo;
import jsonObject.LoginInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.GoodsService;
import service.LoginService;
import service.RegisterService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    @Autowired
    private GoodsService goodsService;

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

    @RequestMapping(value = "/UserActive.json", method = RequestMethod.GET)
    public String active(Model model, @RequestParam(value = "email") String email, @RequestParam(value = "token") String token) {
        VerifyEmailentity verifyEmailentity = registerService.getVerifyEmailByEmail(email);
        Userentity userentity = registerService.getUserByEmail(email);
        String myToken = userentity.getRegTime() + userentity.getEmail() + userentity.getPassword();
        myToken = DigestUtils.md5DigestAsHex(myToken.getBytes());
        if (userentity.getStatus() == "2") {
            model.addAttribute("result", "您已激活!");
        } else if (verifyEmailentity == null) {
            model.addAttribute("result", "该记录不存在!");
        } else if (!token.equals(myToken)) {
            model.addAttribute("result", "该链接无效！请检查账户！");
        } else {
            registerService.activeUser(email);
            model.addAttribute("result", "激活成功！");
        }
        return "active";
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

    @RequestMapping(value = "/CarAdd.json", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> carAdd(
            @RequestParam(value = "goods_id") int goods_id,
            @RequestParam(value = "number") int number) {

        Map<String, Object> result = new HashMap<String, Object>();
        Goodsentity goodsentity = goodsService.getGoodsById(goods_id);
        goodsentity.setQuality(number);
        GoodsInfo goodsInfo = new GoodsInfo(goodsentity);

        result.put("Code", 0);
        result.put("Msg", "操作成功");
        result.put("Info", goodsInfo);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");

        return new ResponseEntity<Map<String, Object>>(result, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/CarRemove.json", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> carAdd(
            @RequestParam(value = "goods_id") int goods_id) {

        Map<String, Object> result = new HashMap<String, Object>();

        result.put("Code", 0);
        result.put("Msg", "操作成功");
        result.put("Info", 1);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");

        return new ResponseEntity<Map<String, Object>>(result, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/Goods.json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getGoods(
            @RequestParam(value = "goods_id") String goods_id) {
        Map<String, Object> result = new HashMap<String, Object>();

        int id = Integer.parseInt(goods_id);
        Goodsentity goodsentity = goodsService.getGoodsById(id);
        GoodsInfo goodsInfo = new GoodsInfo(goodsentity);

        result.put("Code", 0);
        result.put("Msg", "操作成功");
        result.put("Info", goodsInfo);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");

        return new ResponseEntity<Map<String, Object>>(result, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/Latest.json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getLatest() {
        Map<String, Object> result = new HashMap<String, Object>();

        List<LatestInfo> info = new ArrayList<LatestInfo>();
        List<Goodsentity> latest = goodsService.getLatestGoods();

        for (Goodsentity entry:latest) {
            info.add(new LatestInfo(entry));
        }

        result.put("Code", 0);
        result.put("Msg", "操作成功");
        result.put("Info", info);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");

        return new ResponseEntity<Map<String, Object>>(result, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/Search.json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> search(
            @RequestParam(value = "keyWord") String keyWord,
            @RequestParam(value = "type_id") String type_id,
            @RequestParam(value = "page") String page) {
        Map<String, Object> result = new HashMap<String, Object>();

        List<LatestInfo> info = new ArrayList<LatestInfo>();
        List<Goodsentity> list = goodsService.getGoods(keyWord, type_id, page);
        for (Goodsentity item:list) {
            info.add(new LatestInfo(item));
        }

        result.put("Code", 0);
        result.put("Msg", "操作成功");
        result.put("Info", info);

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
