package service;

import dao.UserDao;
import entity.Userentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by sweet on 15-6-3.
 */
@Transactional
@Service
public class LoginService {

    @Autowired
    private UserDao userDao;

    public Userentity getUserByEmail(String email) {
        return userDao.findUserByEmail(email);
    }
}
