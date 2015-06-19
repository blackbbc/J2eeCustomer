package dao;

import entity.Userentity;
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
 * Created by sweet on 15-6-3.
 */
@Repository
public class UserDao {
    private EntityManager em;

    @PersistenceContext
    void setEm(EntityManager entityManager) { this.em = entityManager; }

    public Userentity findUserById(int userId) {
        return em.find(Userentity.class, userId);
    }

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

    public Userentity findUserByNickname(String nickname) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Userentity> c = cb.createQuery(Userentity.class);
        Root<Userentity> user = c.from(Userentity.class);
        c.select(user);
        ParameterExpression<String> p = cb.parameter(String.class);
        c.where(cb.equal(user.get("nickname"), p));

        TypedQuery<Userentity> query = em.createQuery(c);
        query.setParameter(p, nickname);

        List<Userentity> results = query.getResultList();

        if (results.isEmpty()) {
            return null;
        } else {
            return results.get(0);
        }
    }

    public boolean activeUser(String email) {
        Query query = em.createQuery("UPDATE Userentity ur SET ur.status = '2' where ur.email = :email");
        query.setParameter("email", email);

        int updateCount = query.executeUpdate();

        return true;
    }

    public boolean createUser(String regTime, String email, String nickname, String password) {
        Userentity user = new Userentity(regTime, email, password, nickname);
        em.persist(user);
        return true;
    }

    public boolean updateUser(Userentity userentity) {
        em.merge(userentity);
        return true;
    }

    public boolean changeAvatar(int userId, String fileName) {
        Query query = em.createQuery("UPDATE Userentity ur SET ur.path = :path where ur.userId = :userId");
        query.setParameter("path", "http://localhost:12450/Uploads/users/" + fileName);
        query.setParameter("userId", userId);

        int updateCount = query.executeUpdate();
        return true;
    }
}
