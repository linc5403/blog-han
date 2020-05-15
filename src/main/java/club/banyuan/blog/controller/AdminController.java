package club.banyuan.blog.controller;

import club.banyuan.blog.bean.User;
import club.banyuan.blog.service.BlogService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
public class AdminController {

    @Autowired
    BlogService blogService;

    @GetMapping("/admin/{username}")
    public String adminBlog(
            @PathVariable String username,
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            HttpSession session,
            Model model
            ) throws UnsupportedEncodingException {
        // 判断用户是否已经登录已经是否可以访问这个页面
        User user = (User) session.getAttribute("CURRENT_USER");
        if (user == null) {
            return "redirect:/login";
        } else {
            if (!user.getName().equals(username)) {
                return "redirect:/admin/" + URLEncoder.encode(user.getName(), "UTF-8");
            }
        }
        // 返回博客管理页面
        PageInfo info = blogService.findBlogsByUsername(username, page, size);
        model.addAttribute("blogs", info);
        return "admin-blogs";
    }
}
