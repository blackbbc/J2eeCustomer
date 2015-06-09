package controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dao.GoodsDao;
import entity.Applicationentity;
import entity.Goodsentity;
import jsonObject.AppInfo;
import jsonObject.GoodsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import service.ApplicationService;

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
    private GoodsDao goodsDao;

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

    @RequestMapping(value = "/ImgModify.json", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> imgModify(
            @RequestParam(value = "imgData") MultipartFile imgData,
            @CookieValue(value = "loginUid") int loginUid) {
        Map<String, Object> result = new HashMap<String, Object>();


        result.put("Code", 0);
        result.put("Msg", "操作成功");

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

        Goodsentity good1 = goodsDao.findGoodsById(1);
        Goodsentity good2 = goodsDao.findGoodsById(1);

        ArrayList<GoodsInfo> goods = new ArrayList<GoodsInfo>();
        goods.add(new GoodsInfo(good1));
        goods.add(new GoodsInfo(good2));

        result.put("Code", 0);
        result.put("Msg", "操作成功");
        result.put("Info", goods);

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
                Goodsentity good = goodsDao.findGoodsById(good_id.intValue());
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
