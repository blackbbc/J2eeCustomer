package dao;

import entity.Goodsentity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by sweet on 15-6-6.
 */
@Repository
public class GoodsDao {
    private EntityManager em;

    @PersistenceContext
    void setEm(EntityManager entityManager) {this.em = entityManager;}

    public Goodsentity findGoodsById(int id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Goodsentity> c = cb.createQuery(Goodsentity.class);
        Root<Goodsentity> goods = c.from(Goodsentity.class);
        c.select(goods);
        c.where(cb.equal(goods.get("goodsId"), id));

        TypedQuery<Goodsentity> query = em.createQuery(c);

        Goodsentity result = query.getSingleResult();

        em.refresh(result);

        return result;
    }

    public List<Goodsentity> findLatestGoods() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Goodsentity> c = cb.createQuery(Goodsentity.class);
        Root<Goodsentity> goods = c.from(Goodsentity.class);
        c.select(goods);
        c.where(cb.and(cb.equal(goods.get("status"), "2"), cb.equal(goods.get("tokenOff"), 0)));
        c.orderBy(cb.desc(goods.get("startTime")));

        TypedQuery<Goodsentity> query = em.createQuery(c);
        query.setMaxResults(8);

        List<Goodsentity> results = query.getResultList();

        for (Goodsentity good: results)
            em.refresh(good);

        return results;
    }

    public List<Goodsentity> findGoods(String keyWord, String type_id, int page) {
        TypedQuery<Goodsentity> query;
        if (type_id.equals("000000")) {
            query = em.createQuery("select g from Goodsentity g where g.tokenOff = 0 and g.status = '2' and g.name like :name", Goodsentity.class);
            query.setParameter("name", "%"+keyWord+"%");
        } else {
            query = em.createQuery("select g from Goodsentity g where g.tokenOff = 0 and g.status = '2' and g.typeId = :typeid and g.name like :name", Goodsentity.class);
            query.setParameter("typeid", type_id);
            query.setParameter("name", "%"+keyWord+"%");
        }

        query.setFirstResult(18*(page-1));
        query.setMaxResults(18);

        List<Goodsentity> results = query.getResultList();

        for (Goodsentity good: results)
            em.refresh(good);

        return results;
    }

    public List<Goodsentity> findSellGoods(int userId, String type, String status, int start, int count) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Goodsentity> c = cb.createQuery(Goodsentity.class);
        Root<Goodsentity> goods = c.from(Goodsentity.class);
        c.select(goods);
        if (type.equals("0")) {
            c.where(cb.and(cb.equal(goods.get("userId"), userId), cb.equal(goods.get("status"), "2")));
        } else {
            c.where(cb.and(cb.equal(goods.get("userId"), userId), cb.not(cb.equal(goods.get("status"), "2")), cb.not(cb.equal(goods.get("status"), "4"))));
        }
        c.orderBy(cb.desc(goods.get("startTime")));

        TypedQuery<Goodsentity> query = em.createQuery(c);
        query.setFirstResult(18*(start-1));
        query.setMaxResults(count);

        List<Goodsentity> results = query.getResultList();
        for (Goodsentity good: results)
            em.refresh(good);

        return results;
    }

    public boolean updateBookNum(int goodId, int num) {
        Query query = em.createQuery("UPDATE Goodsentity good SET good.bookNum = good.bookNum + :num where good.goodsId = :goodId");
        query.setParameter("num", num);
        query.setParameter("goodId", goodId);

        query.executeUpdate();

        return true;
    }

    public int createAppGoods(Goodsentity goodsentity) {
        em.persist(goodsentity);
        em.flush();

        return goodsentity.getGoodsId();
    }
}
