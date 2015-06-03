package service;

import dao.UserDao;
import entity.Userentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

/**
 * Created by sweet on 15-6-3.
 */

@Transactional
@Service
public class RegisterService {

    @Autowired
    private UserDao userDao;

    public Userentity getUserByEmail(String email) {
        return userDao.findUserByEmail(email);
    }

    public Userentity getUserByNickname(String nickname) {
        return userDao.findUserByNickname(nickname);
    }

    public boolean createUser(String email, String nickname, String password) {
        String regTime = "" + System.currentTimeMillis() / 1000L;
        if (userDao.createUser(regTime, email, nickname, password)) {
            String hash = regTime + email + password;
            String token = DigestUtils.md5DigestAsHex(hash.getBytes());
            return true;
        } else {
            return false;
        }
    }
}
