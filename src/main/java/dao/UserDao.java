package dao;

import entity.Userentity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by sweet on 15-6-3.
 */
@Repository
public class UserDao {
    private EntityManager em;

    @PersistenceContext
    void setEm(EntityManager entityManager) { this.em = entityManager; }

    public Userentity findUserByEmail(String email) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Userentity> c = cb.createQuery(Userentity.class);
        Root<Userentity> user = c.from(Userentity.class);
        c.select(user);
        ParameterExpression<String> p = cb.parameter(String.class);
        c.where(cb.equal(user.get("email"), p));

        TypedQuery<Userentity> query = em.createQuery(c);
        query.setParameter(p, email);

        List<Userentity> results = query.getResultList();

        if (results.isEmpty()) {
            return null;
        } else {
            return results.get(0);
        }
    }
}
