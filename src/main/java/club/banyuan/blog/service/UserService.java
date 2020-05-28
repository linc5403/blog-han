package club.banyuan.blog.service;

import club.banyuan.blog.bean.User;
import club.banyuan.blog.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public User findByName(String username) {
        return userDao.getUserByName(username);
    }
    public void addUser(String name, String email, String password) {
        userDao.addUser(name, email, password);
    }
    public void activeUser(String email) {
        User user = userDao.getUserByEmail(email);
        logger.info("user is " + user);
        userDao.activeUser(user);
    }

    private Logger logger = LoggerFactory.getLogger(this.getClass());
}
