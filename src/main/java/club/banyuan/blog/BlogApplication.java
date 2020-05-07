package club.banyuan.blog;

import club.banyuan.blog.bean.Blog;
import club.banyuan.blog.bean.Comment;
import club.banyuan.blog.bean.User;
import club.banyuan.blog.dao.BlogDao;
import club.banyuan.blog.dao.CommentDao;
import club.banyuan.blog.dao.UserDao;
import club.banyuan.blog.service.MailService;
import club.banyuan.blog.service.RedisService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import javax.mail.MessagingException;
import java.util.List;

@SpringBootApplication
public class BlogApplication {

	public static void main(String[] args) throws MessagingException {
		SpringApplication.run(BlogApplication.class, args);
    }
}
