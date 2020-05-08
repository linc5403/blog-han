package club.banyuan.blog.service;

import club.banyuan.blog.bean.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public User findByName(String username) {
        return new User("zhangsan", 21);
    }
}
