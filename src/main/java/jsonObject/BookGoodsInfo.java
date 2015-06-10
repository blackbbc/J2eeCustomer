package jsonObject;

import entity.BookGoodsentity;

/**
 * Created by sweet on 15-6-10.
 */
public class BookGoodsInfo {
    String book_id;
    String f_user_id;
    String goods_id;
    String name;
    String nickname;
    String num;
    String path;
    String price;

    public BookGoodsInfo(BookGoodsentity book) {
        this.book_id = "" + book.getBookId();
        this.f_user_id = "" + book.getfUserId();
        this.goods_id = "" + book.getBookId();
        this.name = book.getName();
        this.nickname = book.getNickname();
        this.num = "" + book.getNum();
        this.path = book.getPath();
        this.price = String.format("%2f", book.getPrice());
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public String getF_user_id() {
        return f_user_id;
    }

    public void setF_user_id(String f_user_id) {
        this.f_user_id = f_user_id;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
