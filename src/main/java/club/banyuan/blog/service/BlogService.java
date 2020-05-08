package club.banyuan.blog.service;

import club.banyuan.blog.bean.Blog;
import club.banyuan.blog.bean.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogService {
    public List<Blog> findBlogs(User user) {
        List<Blog> blogs = new ArrayList<>();
        for (Integer i = 0; i < 100; i++) {
            Blog blog = new Blog();
            blogs.add(blog);
        }
        return blogs;
    }
}
