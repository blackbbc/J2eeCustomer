package controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dao.GoodsDao;
import dao.UserDao;
import entity.Applicationentity;
import entity.Goodsentity;
import jsonObject.AppInfo;
import jsonObject.GoodsInfo;
import jsonObject.LatestInfo;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import service.ApplicationService;
import service.GoodsService;
import service.UserService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sweet on 15-6-7.
 */
@Controller
@RequestMapping(value = "/Manager")
public class ManagerController {

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private UserService userService;

    @Autowired
    private GoodsService goodsService;

    @RequestMapping(value = "/UserData.json", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> userData(@RequestParam(value = "bookInfo") String bookInfo) {
        Map<String, Object> result = new HashMap<String, Object>();
        Gson gson = new Gson();
        ArrayList<HashMap<String, Integer>> book = gson.fromJson(bookInfo, ArrayList.class);

        result.put("Code", 0);
        result.put("Msg", "操作成功");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");

        return new ResponseEntity<Map<String, Object>>(result, headers, HttpStatus.OK);
    }

    public static boolean saveAvatarImage(String imgData, String fileName) {
        try {
            byte[] imageDataBytes = Base64.decodeBase64(imgData);
            FileOutputStream file = new FileOutputStream("/var/local/apache-tomcat-8.0.21/webapps/ROOT/images/uploads/users/"+fileName);
            file.write(imageDataBytes);
            file.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping(value = "/ImgModify.json", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> imgModify(
            @RequestParam(value = "imgData") String imgData,
            @CookieValue(value = "loginUid") int loginUid) {
        Map<String, Object> result = new HashMap<String, Object>();
        String fileName = loginUid + ".png";

        saveAvatarImage(imgData, fileName);
        userService.changeAvatar(loginUid, fileName);
//        userService.changeAvatar(1, "8.png");

        result.put("Code", 0);
        result.put("Msg", "操作成功");
        result.put("Info","http://localhost:12450/Uploads/users/"+fileName);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Credentials", "true");
        headers.add("Access-Control-Allow-Origin", "http://localhost:3000");

        return new ResponseEntity<Map<String, Object>>(result, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/PSModify.json", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> psModify(
            @RequestParam(value = "old") String oldPS,
            @RequestParam(value = "new") String newPS) {
        Map<String, Object> result = new HashMap<String, Object>();

        result.put("Code", 0);
        result.put("Msg", "操作成功");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");

        return new ResponseEntity<Map<String, Object>>(result, headers, HttpStatus.OK);
    }


    @RequestMapping(value = "/Book.json", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> book(@RequestParam(value = "bookInfo") String bookInfo) {
        Map<String, Object> result = new HashMap<String, Object>();
        Gson gson = new Gson();
        ArrayList<HashMap<String, Integer>> book = gson.fromJson(bookInfo, ArrayList.class);

        result.put("Code", 0);
        result.put("Msg", "操作成功");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");

        return new ResponseEntity<Map<String, Object>>(result, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "Goods", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getGoods(
            @RequestParam(value = "type") String type,
            @RequestParam(value = "status") String status,
            @RequestParam(value = "start") int start,
            @RequestParam(value = "count") int count,
            @CookieValue(value = "loginUid") int loginUid) {
        Map<String, Object> result = new HashMap<String, Object>();

        List<Goodsentity> goods = goodsService.getSellGoods(loginUid, type, status, start, count);
        ArrayList<GoodsInfo> goodsInfo = new ArrayList<GoodsInfo>();
        for (Goodsentity good:goods) {
            goodsInfo.add(new GoodsInfo(good));
        }

        result.put("Code", 0);
        result.put("Msg", "操作成功");
        result.put("Info", goodsInfo);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Credentials", "true");
        headers.add("Access-Control-Allow-Origin", "http://localhost:3000");

        return new ResponseEntity<Map<String, Object>>(result, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/Application.json", method = RequestMethod.GET)
    @ResponseBody
    public  ResponseEntity<Map<String, Object>> getApply(
            @RequestParam(value = "status") String status,
            @RequestParam(value = "start") int start,
            @RequestParam(value = "count") int count,
            @CookieValue(value = "loginUid") String loginUid) {

        Gson gson = new Gson();

        Map<String, Object> result = new HashMap<String, Object>();

        ArrayList<Object> infos = new ArrayList<Object>();

        int userId = Integer.parseInt(loginUid);
        List<Applicationentity> apps = applicationService.getApps(userId, status, start, count);

        for (Applicationentity app:apps) {
            HashMap<String, Object> info = new HashMap<String, Object>();
            ArrayList<Double> goods_id = gson.fromJson(app.getGoodsId(), ArrayList.class);
            ArrayList<Object> details = new ArrayList<Object>();
            for (Double good_id:goods_id) {
                Goodsentity good = goodsService.getGoodsById(good_id.intValue());
                AppInfo appInfo = new AppInfo(good);
                details.add(appInfo);
            }
            info.put("app_id", ""+app.getAppId());
            info.put("b_no", app.getbNo());
            info.put("no", app.getNo());
            info.put("reason", app.getReason());
            info.put("status", app.getStatus());
            info.put("time", app.getTime());
            info.put("user_id", "" + userId);
            info.put("detail", details);

            infos.add(info);
        }

        result.put("Code", 0);
        result.put("Msg", "操作成功");
        result.put("Info", infos);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Credentials", "true");
        headers.add("Access-Control-Allow-Origin", "http://localhost:3000");

        return new ResponseEntity<Map<String, Object>>(result, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/Application.json", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> apply(
            @RequestParam(value = "b_NO") String b_NO,
            @RequestParam(value = "NO") String NO,
            @RequestParam(value = "info") String info,
            @CookieValue(value = "loginUid", required = false) Integer loginUid,
            @CookieValue(value = "loginKey", required = false) String loginKey) {
        Map<String, Object> result = new HashMap<String, Object>();
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<HashMap<String, String>>>(){}.getType();

        ArrayList<HashMap<String, String>> infos = gson.fromJson(info, type);

        applicationService.createApplication(loginUid, b_NO, NO, infos);

        result.put("Code", 0);
        result.put("Msg", "操作成功");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Credentials", "true");
        headers.add("Access-Control-Allow-Origin", "http://localhost:3000");

        return new ResponseEntity<Map<String, Object>>(result, headers, HttpStatus.OK);
    }
}
