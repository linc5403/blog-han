package club.banyuan.blog.dao;

import club.banyuan.blog.bean.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    public User getUserById(Integer id);
    public User getUserByEmail(String email);
    public User getUserByName(String name);
    public void addUser(String name, String email, String password);
    public void activeUser(User user);
}
