package club.banyuan.blog;

import club.banyuan.blog.bean.Blog;
import club.banyuan.blog.dao.BlogDao;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("club.banyuan")
@MapperScan("club.banyuan.blog.dao")
public class BlogApplication {

	public static void main(String[] args) {
		ApplicationContext context =  SpringApplication.run(BlogApplication.class, args);
		BlogDao blogDao = context.getBean(club.banyuan.blog.dao.BlogDao.class);
		Blog blog = blogDao.findBlogById(11);
		System.out.println(blog);
    }
}
