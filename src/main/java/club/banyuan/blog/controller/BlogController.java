package club.banyuan.blog.controller;

import club.banyuan.blog.bean.Blog;
import club.banyuan.blog.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
@RequestMapping("/blog/{id}")
public class BlogController {

    @GetMapping
    public String showBlog(@PathVariable Integer id, Model model) {
        // 根据id查找对应blog的信息，得到Blog的对象，将它传递给Viewer
        Blog blog = new Blog();
        User user = new User();
        user.setName("张三");
        user.setId(1);
        blog.setAuthor(user);
        blog.setContent("张三的第一篇blog");
        blog.setTitle("fist Blog");
        blog.setCreatedTime(new Date());
        blog.setId(1);
        model.addAttribute("blog", blog);
        return "item";
    }
}
