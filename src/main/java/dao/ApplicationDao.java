package dao;

import entity.Applicationentity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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


}
