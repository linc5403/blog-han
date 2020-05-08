package club.banyuan.blog.controller;

import club.banyuan.blog.bean.Blog;
import club.banyuan.blog.bean.User;
import club.banyuan.blog.service.BlogService;
import club.banyuan.blog.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @GetMapping("/user/{username:[0-9a-z_]+}")
    @ResponseBody
    public User showUser(@PathVariable String username){
        UserService userService = new UserService();
        return userService.findByName(username);
    }

    @GetMapping("/user/blogs/{username:[0-9a-z_]+}")
    @ResponseBody
    public List<Blog> showBlogs(@PathVariable String username,
                                @RequestParam(defaultValue = "1",required = false) Integer page,
                                @RequestParam(defaultValue = "10",required = false) Integer size){
        BlogService blogService = new BlogService();
        UserService userService = new UserService();
        List<Blog> list = new ArrayList<>();
        if (page <= 0 || size <= 0) {
            page = 1;
            size = 10;
        }

        List<Blog> totalList = blogService.findBlogs(userService.findByName(username));
        int totalSize = totalList.size();
        int begin = (page-1) * size;
        int end = page * size -1;
        if (begin > totalSize) {
            // 开始就已经超出范围了，显示最后一页的内容
            if (totalSize % size == 0) {
                begin = totalSize - size;
            } else {
                begin = totalSize / size * size;
            }
            end = totalSize -1;
        } else if (end > totalSize) {
            // 取从begin到totalSize的内容
            end = totalSize -1;
        } else {
            // 取begin到end
        }

        return totalList.subList(begin, end+1);
    }
}

