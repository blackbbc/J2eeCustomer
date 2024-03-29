package service;

import com.sun.istack.internal.Nullable;
import dao.GoodsDao;
import entity.Goodsentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by sweet on 15-6-6.
 */

@Transactional
@Service
public class GoodsService {

    @Autowired
    private GoodsDao goodsDao;

    public Goodsentity getGoodsById(int id) {
        return goodsDao.findGoodsById(id);
    }

    public @Nullable
    List<Goodsentity> getLatestGoods() {
        return goodsDao.findLatestGoods();
    }

    public List<Goodsentity> getGoods(String keyWord, String typeId, String page) {
        return goodsDao.findGoods(keyWord, typeId, Integer.parseInt(page));
    }

    public List<Goodsentity> getSellGoods(int userId, String type, String status, int start, int count) {
        return goodsDao.findSellGoods(userId, type, status, start, count);
    }

    public boolean updateGoodsBookNum(int goodsId, int num) {
        return goodsDao.updateBookNum(goodsId, num);
    }
}
