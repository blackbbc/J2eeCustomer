package dao;

import entity.Bookentity;
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
public class BookDao {

    private EntityManager em;

    @PersistenceContext
    void setEm(EntityManager entityManager) {this.em = entityManager;}

    public int createBook(Bookentity bookentity) {
        em.persist(bookentity);
        em.flush();
        return bookentity.getBookId();
    }

    public List<Bookentity> findBooks(int userId, String status, int start, int count) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Bookentity> c = cb.createQuery(Bookentity.class);
        Root<Bookentity> goods = c.from(Bookentity.class);
        c.select(goods);
        if (status.equals("0")) {
            c.where(cb.equal(goods.get("userId"), userId));
        } else {
            c.where(cb.and(cb.equal(goods.get("userId"), userId), cb.equal(goods.get("status"), status)));
        }
        c.orderBy(cb.desc(goods.get("time")));

        TypedQuery<Bookentity> query = em.createQuery(c);
        query.setFirstResult(18*(start-1));
        query.setMaxResults(count);

        List<Bookentity> results = query.getResultList();

        return results;
    }
}
