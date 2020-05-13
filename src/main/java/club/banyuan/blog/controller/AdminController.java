package club.banyuan.blog.controller;

import club.banyuan.blog.service.BlogService;
import club.banyuan.blog.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private BlogService blogService;

    @RequestMapping("/admin/{username}")
    public String blogAdmin(@PathVariable String username,
                            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
                            Model model

    ){
        PageInfo info = blogService.findBlogsByUsername(username, page, size);
        System.out.println(info);
        model.addAttribute("blogs", info);
        return "admin-blogs";
    }
}
