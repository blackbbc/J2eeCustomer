package dao;

import entity.Applicationentity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by sweet on 15-6-7.
 */
@Repository
public class ApplicationDao {
    private EntityManager em;

    @PersistenceContext
    void setEm(EntityManager entityManager) {this.em = entityManager;}

    public boolean createApplication(int user_id, String b_NO, String NO, String goodsId) {
        Applicationentity applicationentity = new Applicationentity(user_id, b_NO, NO, goodsId);
        em.persist(applicationentity);

        return true;
    }

    public List<Applicationentity> getApps(int userId, String status, int start, int count) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Applicationentity> c = cb.createQuery(Applicationentity.class);
        Root<Applicationentity> goods = c.from(Applicationentity.class);
        c.select(goods);
        if (status.equals("0")) {
            c.where(cb.equal(goods.get("userId"), userId));
        } else {
            c.where(cb.and(cb.equal(goods.get("userId"), userId), cb.equal(goods.get("status"), status)));
        }
        c.orderBy(cb.desc(goods.get("time")));

        TypedQuery<Applicationentity> query = em.createQuery(c);
        query.setFirstResult(18*(start-1));
        query.setMaxResults(count);

        List<Applicationentity> results = query.getResultList();

        return results;

    }


}
