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
 * Created by sweet on 15-6-10.
 */
@Transactional
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private VerifyEmailDao verifyEmailDao;

    @Autowired
    Utils utils;

    public boolean changeAvatar(int userId, String fileName) {
        return userDao.changeAvatar(userId, fileName);
    }

    public boolean updateInfo(int userId, String telephone, String alipay, String signature, String name) {
        Userentity userentity = userDao.findUserById(userId);
        if (telephone != null)
            userentity.setTelephone(telephone);
        if (alipay != null)
            userentity.setAlipay(alipay);
        if (signature != null)
            userentity.setSignature(signature);
        if (name != null)
            userentity.setName(name);

        userDao.updateUser(userentity);

        return true;
    }

    public boolean modifyPS(int userId, String oldPS, String newPS) {
        Userentity userentity = userDao.findUserById(userId);
        if (!userentity.getPassword().equals(oldPS)) {
            return false;
        } else {
            userDao.changePS(userId, newPS);
            return true;
        }
    }

    public boolean forgetPS(String email, String newPS) {
        Userentity userentity = userDao.findUserByEmail(email);
        userentity.setPassword(newPS);
        userDao.updateUser(userentity);

        return true;
    }

    public boolean addForget(String email) {
        Long now = System.currentTimeMillis() / 1000L;
        Long tomorrow = now + 86400;
        String regTime = now.toString();
        String expire = tomorrow.toString();
        String hashCode = email + regTime;
        String token = DigestUtils.md5DigestAsHex(hashCode.getBytes());

        Userentity userentity = userDao.findUserByEmail(email);

        if (userentity == null || (!userentity.getStatus().equals("2"))) {
            return false;
        }
        else {
            verifyEmailDao.createVerifyEmail(email, token, expire);
            utils.sendForgetMail(email, token);
            return true;
        }

    }

    public boolean verifyForget(String email, String token) {
        VerifyEmailentity verifyEmailentity = verifyEmailDao.findVerifyEmailByToken(token);
        if (verifyEmailentity == null ) {
            return false;
        } else {
            verifyEmailDao.removeVerifyEmailByEmail(email);
            return true;
        }
    }
}
