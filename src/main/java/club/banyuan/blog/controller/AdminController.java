package club.banyuan.blog.controller;

import club.banyuan.blog.service.BlogService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {

    @Autowired
    BlogService blogService;

    @GetMapping("/admin/{username}")
    public String adminBlog(
            @PathVariable String username,
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            Model model
            ) {
        // 返回博客管理页面
        PageInfo info = blogService.findBlogsByUsername(username, page, size);
        model.addAttribute("blogs", info);
        return "admin-blogs";
    }
}
