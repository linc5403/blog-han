package club.banyuan.blog.service;

import club.banyuan.blog.bean.User;
import club.banyuan.blog.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UserDao userDao;

    public User login(String username, String password) {
        // 返回user对象或者null
        User user = userDao.getUserByName(username);
        if (user == null) {
            return null;
        }
        if (user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }
    }
}
