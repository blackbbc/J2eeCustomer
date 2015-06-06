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
}
