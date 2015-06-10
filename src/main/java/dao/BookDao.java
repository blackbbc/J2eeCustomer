package dao;

import entity.Bookentity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
}
