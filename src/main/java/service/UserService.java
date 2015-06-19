package service;

import dao.UserDao;
import entity.Userentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by sweet on 15-6-10.
 */
@Transactional
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

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
}
