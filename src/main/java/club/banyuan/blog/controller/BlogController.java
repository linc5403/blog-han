package club.banyuan.blog.controller;

import club.banyuan.blog.bean.Blog;
import club.banyuan.blog.bean.User;
import club.banyuan.blog.exception.NotFoundException;
import club.banyuan.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private BlogService blogService;

    @GetMapping
    public String showBlog(@PathVariable Integer id, Model model) {
        // 根据id查找对应blog的信息，得到Blog的对象，将它传递给Viewer
        Blog blog = blogService.findBlogById(id);
        if (blog == null) {
            // 返回404
            throw new NotFoundException("没有这个博客");
        }
        model.addAttribute("blog", blog);
        return "item";
    }
}
