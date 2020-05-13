package club.banyuan.blog.service;

import club.banyuan.blog.bean.Blog;
import club.banyuan.blog.bean.User;
import club.banyuan.blog.dao.BlogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogService {

    @Autowired
    BlogDao blogDao;

    public List<Blog> findBlogs(User user) {
        List<Blog> blogs = new ArrayList<>();
        for (Integer i = 0; i < 100; i++) {
            Blog blog = new Blog();
            blogs.add(blog);
        }
        return blogs;
    }

    public Blog findBlogById(Integer id) {
        blogDao.findBlogById(id);
        return null;
    }
}
