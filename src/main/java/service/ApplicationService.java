package service;

import com.google.gson.Gson;
import dao.ApplicationDao;
import dao.GoodsDao;
import dao.UserDao;
import entity.Applicationentity;
import entity.Goodsentity;
import entity.Userentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by sweet on 15-6-7.
 */
@Transactional
@Service
public class ApplicationService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ApplicationDao applicationDao;

    @Autowired
    private GoodsDao goodsDao;

    public boolean createApplication(int user_id, String b_NO, String NO, ArrayList<HashMap<String, String>> infos) {
        ArrayList<Integer> goodsArr = new ArrayList<Integer>();
        Userentity userentity = userDao.findUserById(user_id);

        for (HashMap<String, String> info:infos) {
            Goodsentity goodsentity = new Goodsentity(info, userentity);
            int goods_id = goodsDao.createAppGoods(goodsentity);
            goodsArr.add(goods_id);
        }

        Gson gson = new Gson();
        String goodsId = gson.toJson(goodsArr);

        applicationDao.createApplication(user_id, b_NO, NO, goodsId);

        return true;
    }

    public List<Applicationentity> getApps(int userId, String status, int start, int count) {
        return applicationDao.getApps(userId, status, start, count);
    }

    public boolean cancelApp(int appId, String reason) {
        return applicationDao.cancalApp(appId, reason);
    }


}
