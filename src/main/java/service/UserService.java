package service;

import dao.UserDao;
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
}
