package controller;

import com.google.gson.Gson;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sweet on 15-6-7.
 */
@Controller
@RequestMapping(value = "/Manager")
public class ManagerController {
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
}
