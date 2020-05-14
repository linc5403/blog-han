package club.banyuan.blog.service;

import club.banyuan.blog.bean.Blog;
import club.banyuan.blog.bean.User;
import club.banyuan.blog.dao.BlogDao;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogService {

    @Autowired
    BlogDao blogDao;

    @Autowired
    UserService userService;

    public PageInfo findBlogs(User user, Integer page, Integer size) {
        //  根据user对象获取该user的所有blog
        PageHelper.startPage(page, size, "id asc");
        return new PageInfo(blogDao.findBlogsByUserId(user.getId()));
    }

    public PageInfo findBlogsByUsername(String username, Integer page, Integer size) {
        //  根据username获取该user的所有blog
        User user = userService.findByName(username);
        PageHelper.startPage(page, size, "id asc");
        return new PageInfo(blogDao.findBlogsByUserId(user.getId()));
    }

    public Blog findBlogById(Integer id) {
        Blog blog = blogDao.findBlogById(id);
        return blog;
    }
}
