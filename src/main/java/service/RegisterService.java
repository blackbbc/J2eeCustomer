package service;

import dao.UserDao;
import dao.VerifyEmailDao;
import entity.Userentity;
import entity.VerifyEmailentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import utils.Utils;

/**
 * Created by sweet on 15-6-3.
 */

@Transactional
@Service
public class RegisterService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private VerifyEmailDao verifyEmailDao;

    @Autowired
    private Utils utils;

    public Userentity getUserByEmail(String email) {
        return userDao.findUserByEmail(email);
    }

    public Userentity getUserByNickname(String nickname) {
        return userDao.findUserByNickname(nickname);
    }

    public VerifyEmailentity getVerifyEmailByEmail(String email) {
        return verifyEmailDao.findVerifyEmailByEmail(email);
    }

    public boolean createUser(String email, String nickname, String password) {
        Long now = System.currentTimeMillis() / 1000L;
        Long tomorrow = now + 86400;
        String regTime = now.toString();
        String expire = tomorrow.toString();

        String token = regTime + email + password;
        token = DigestUtils.md5DigestAsHex(token.getBytes());
        if (userDao.createUser(regTime, email, nickname, password) && verifyEmailDao.createVerifyEmail(email, token, expire)) {
            utils.sendMail(email, token);
            return true;
        } else {
            return false;
        }
    }

    public boolean activeUser(String email) {
        userDao.activeUser(email);
        verifyEmailDao.removeVerifyEmailByEmail(email);
        return true;
    }
}
