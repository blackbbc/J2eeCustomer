package dao;

import entity.VerifyEmailentity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by sweet on 15-6-4.
 */
@Repository
public class VerifyEmailDao {
    private EntityManager em;

    @PersistenceContext
    void setEm(EntityManager entityManager) { this.em = entityManager; }

    public VerifyEmailentity findVerifyEmailByEmail(String email) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<VerifyEmailentity> c = cb.createQuery(VerifyEmailentity.class);
        Root<VerifyEmailentity> verifyEmail = c.from(VerifyEmailentity.class);
        c.select(verifyEmail);
        ParameterExpression<String> p = cb.parameter(String.class);
        c.where(cb.equal(verifyEmail.get("email"), p));

        TypedQuery<VerifyEmailentity> query = em.createQuery(c);
        query.setParameter(p, email);

        List<VerifyEmailentity> results = query.getResultList();

        if (results.isEmpty()) {
            return null;
        } else {
            return results.get(0);
        }
    }

    public VerifyEmailentity findVerifyEmailByToken(String token) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<VerifyEmailentity> c = cb.createQuery(VerifyEmailentity.class);
        Root<VerifyEmailentity> verifyEmail = c.from(VerifyEmailentity.class);
        c.select(verifyEmail);
        ParameterExpression<String> p = cb.parameter(String.class);
        c.where(cb.equal(verifyEmail.get("token"), p));

        TypedQuery<VerifyEmailentity> query = em.createQuery(c);
        query.setParameter(p, token);

        List<VerifyEmailentity> results = query.getResultList();

        if (results.isEmpty()) {
            return null;
        } else {
            return results.get(0);
        }
    }

    public boolean createVerifyEmail(String email, String token, String expire) {
        VerifyEmailentity verifyEmailentity = new VerifyEmailentity(email, token, expire);
        em.merge(verifyEmailentity);
        return true;
    }

    public boolean removeVerifyEmailByEmail(String email) {
        Query query = em.createQuery("delete from VerifyEmailentity vr where vr.email = :email");
        query.setParameter("email", email);
        query.executeUpdate();
        return true;
    }

}
