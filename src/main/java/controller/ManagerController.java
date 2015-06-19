package controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dao.GoodsDao;
import dao.UserDao;
import entity.Applicationentity;
import entity.BookGoodsentity;
import entity.Bookentity;
import entity.Goodsentity;
import jsonObject.AppInfo;
import jsonObject.BookGoodsInfo;
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
import service.BookService;
import service.GoodsService;
import service.UserService;
import utils.Utils;

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

    @Autowired
    private BookService bookService;

    @Autowired
    private Utils utils;

    @RequestMapping(value = "/UserData.json", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> userData(
            @RequestParam(value = "telephone", required = false) String telephone,
            @RequestParam(value = "alipay", required = false) String alipay,
            @RequestParam(value = "signature", required = false) String signature,
            @RequestParam(value = "name", required = false) String name,
            @CookieValue(value = "loginUid") int loginUid) {
        Map<String, Object> result = new HashMap<String, Object>();

        userService.updateInfo(loginUid, telephone, alipay, signature, name);

        result.put("Code", 0);
        result.put("Msg", "操作成功");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Credentials", "true");
        headers.add("Access-Control-Allow-Origin", "http://localhost:3000");

        return new ResponseEntity<Map<String, Object>>(result, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/ImgModify.json", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> imgModify(
            @RequestParam(value = "imgData") String imgData,
            @CookieValue(value = "loginUid") int loginUid) {
        Map<String, Object> result = new HashMap<String, Object>();
        String fileName = loginUid + ".png";

        utils.saveAvatarImage(imgData, fileName);
        userService.changeAvatar(loginUid, fileName);

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
            @RequestParam(value = "new") String newPS,
            @CookieValue(value = "loginUid") int loginUid) {
        Map<String, Object> result = new HashMap<String, Object>();

        if (userService.modifyPS(loginUid, oldPS, newPS)) {
            result.put("Code", 0);
            result.put("Msg", "操作成功");
        } else {
            result.put("Code", 1030);
            result.put("Msg", "密码错误");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Credentials", "true");
        headers.add("Access-Control-Allow-Origin", "http://localhost:3000");

        return new ResponseEntity<Map<String, Object>>(result, headers, HttpStatus.OK);
    }


    @RequestMapping(value = "/Book.json", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> book(
            @RequestParam(value = "bookInfo") String bookInfo,
            @CookieValue(value = "loginUid" ) int loginUid) {
        Map<String, Object> result = new HashMap<String, Object>();
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<HashMap<String, Integer>>>(){}.getType();
        ArrayList<HashMap<String, Integer>> book = gson.fromJson(bookInfo, type);

        if (bookService.book(loginUid, book)) {
            result.put("Code", 0);
            result.put("Msg", "操作成功");
        } else {
            result.put("Code", 1007);
            result.put("Msg", "操作失败");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Credentials", "true");
        headers.add("Access-Control-Allow-Origin", "http://localhost:3000");

        return new ResponseEntity<Map<String, Object>>(result, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/BookDetail.json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> bookDetail(
            @RequestParam(value = "status") String status,
            @RequestParam(value = "start") int start,
            @RequestParam(value = "count") int count,
            @CookieValue(value = "loginUid") int loginUid) {


        Map<String, Object> result = new HashMap<String, Object>();

        ArrayList<Object> infos = new ArrayList<Object>();

        int userId = loginUid;
        List<Bookentity> books = bookService.getBooks(userId, status, start, count);

        for (Bookentity book:books) {
            HashMap<String, Object> info = new HashMap<String, Object>();
            List<BookGoodsentity> bookGoods = bookService.getBookGoods(book.getBookId());
            List<BookGoodsInfo> details = new ArrayList<BookGoodsInfo>();
            for (BookGoodsentity bookgood:bookGoods) {
                details.add(new BookGoodsInfo(bookgood));
            }
            info.put("b_mid", ""+book.getbMid());
            info.put("book_id", ""+book.getBookId());
            info.put("delivery", book.getDelivery());
            info.put("f_time", book.getfTime());
            info.put("school_id", book.getBookId());
            info.put("status", book.getStatus());
            info.put("time", book.getTime());

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

    @RequestMapping(value = "/BookCancel.json", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> bookCancel(
            @RequestParam(value = "book_id") int bookId,
            @CookieValue(value = "loginUid") int loginUid) {

        Map<String, Object> result = new HashMap<String, Object>();

        bookService.bookCancel(loginUid, bookId);

        result.put("Code", 0);
        result.put("Msg", "操作成功");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Credentials", "true");
        headers.add("Access-Control-Allow-Origin", "http://localhost:3000");

        return new ResponseEntity<Map<String, Object>>(result, headers, HttpStatus.OK);
    }



    @RequestMapping(value = "/Goods.json", method = RequestMethod.GET)
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

    @RequestMapping(value = "/ApplicationCancel.json", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> cancelApp(
            @RequestParam(value = "app_id") int appId,
            @RequestParam(value = "content") String reason,
            @CookieValue(value = "loginUid") int loginUid) {
        Map<String, Object> result = new HashMap<String, Object>();


        applicationService.cancelApp(appId, reason);

        result.put("Code", 0);
        result.put("Msg", "操作成功");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Credentials", "true");
        headers.add("Access-Control-Allow-Origin", "http://localhost:3000");

        return new ResponseEntity<Map<String, Object>>(result, headers, HttpStatus.OK);
    }
}
