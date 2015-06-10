package dao;

import entity.BookGoodsentity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by sweet on 15-6-10.
 */

@Repository
public class BookGoodsDao {
    private EntityManager em;

    @PersistenceContext
    void setEm(EntityManager entityManager) {this.em = entityManager;}

    public int createBookGoods(BookGoodsentity newBook) {
        em.persist(newBook);
        em.flush();
        return newBook.getId();
    }

}
