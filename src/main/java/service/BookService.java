package service;

import dao.BookDao;
import dao.BookGoodsDao;
import dao.GoodsDao;
import dao.UserDao;
import entity.BookGoodsentity;
import entity.Bookentity;
import entity.Goodsentity;
import entity.Userentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by sweet on 15-6-10.
 */

@Transactional
@Service
public class BookService {

    @Autowired
    UserDao userDao;

    @Autowired
    GoodsDao goodsDao;

    @Autowired
    BookDao bookDao;

    @Autowired
    BookGoodsDao bookGoodsDao;

    public boolean book(int userId, ArrayList<HashMap<String, Integer>> bookInfo) {
        Userentity buyer = userDao.findUserById(userId);
        Bookentity bookentity = new Bookentity(userId, buyer.getSchoolId());
        int bookId = bookDao.createBook(bookentity);

        for (HashMap<String, Integer> info:bookInfo) {
            Goodsentity good = goodsDao.findGoodsById(info.get("gid"));
            if (good.getQuality() - good.getBookNum() - good.getSoldNum() >= info.get("num")) {
                BookGoodsentity newBook = new BookGoodsentity(bookId, good, info.get("num"));
                bookGoodsDao.createBookGoods(newBook);
                goodsDao.updateBookNum(info.get("gid"), info.get("num"));
            } else {
                return false;
            }
        }
        return true;
    }

    public List<Bookentity> getBooks(int userId, String status, int start, int count) {
        return bookDao.findBooks(userId, status, start, count);
    }

    public List<BookGoodsentity> getBookGoods(int bookId) {
        return bookGoodsDao.findBookGoodsByBookId(bookId);
    }

    public boolean bookCancel(int userId, int bookId) {

        List<BookGoodsentity> bookGoods = bookGoodsDao.findBookGoodsByBookId(bookId);

        for (BookGoodsentity bookGood:bookGoods) {
            goodsDao.updateBookNum(bookGood.getGoodsId(), -bookGood.getNum());
        }

        bookDao.changeBooksStatus(bookId, "2");

        return true;

    }

}
