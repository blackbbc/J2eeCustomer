package dao;

import entity.BookGoodsentity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

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

    public List<BookGoodsentity> findBookGoodsByBookId(int bookId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<BookGoodsentity> c = cb.createQuery(BookGoodsentity.class);
        Root<BookGoodsentity> goods = c.from(BookGoodsentity.class);
        c.select(goods);
        c.where(cb.equal(goods.get("bookId"), bookId));

        TypedQuery<BookGoodsentity> query = em.createQuery(c);

        List<BookGoodsentity> results = query.getResultList();

        return results;
    }

}
