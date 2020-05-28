package club.banyuan.blog.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Stack;

@Service
public class RegisterService {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private MailService mailService;

    public void register(String name, String password, String email) throws NoSuchAlgorithmException {
        // 1. 添加用户到mysql
        userService.addUser(name, email, password);
        // 2. 构造激活邮件并发送
        // 2.1 生成token
        String token = "" + name.hashCode() + password.hashCode() + email.hashCode();
        // 2.2 保存到redis
        Integer timeout = 2;
        redisService.setString(token, email, timeout);
        // 2.3 构造邮件内容
        String title = "邮件激活通知";
        String content = "你已注册blog账号，请在2小时内点击下面邮件:\n";
        content += "http://localhost:8091/active?token=";
        content += token;
        // 2.4 发送
        mailService.sendMail(email, title, content);
    }

    public void active(String token) {
        String email = redisService.getString(token);
        logger.info("email is " + email);
        if (email != null) {
            // userService激活用户
            userService.activeUser(email);
        }
    }

    private Logger logger = LoggerFactory.getLogger(this.getClass());
}
