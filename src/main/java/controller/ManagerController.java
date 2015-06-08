package controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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

        System.out.println(info);

        ArrayList<HashMap<String, String>> infos = gson.fromJson(info, type);

        System.out.println(infos.get(0).get("name"));

//        applicationService.createApplication(loginUid, b_NO, NO, infos);

        result.put("Code", 0);
        result.put("Msg", "操作成功");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Credentials", "true");
        headers.add("Access-Control-Allow-Origin", "http://localhost:3000");

        return new ResponseEntity<Map<String, Object>>(result, headers, HttpStatus.OK);
    }
}
