package club.banyuan.blog.controller;

import club.banyuan.blog.bean.Blog;
import club.banyuan.blog.bean.User;
import club.banyuan.blog.service.BlogService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
public class AdminController {

    @Autowired
    BlogService blogService;

    @GetMapping("/admin/blog")
    public String adminBlog(
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            HttpSession session,
            Model model
            ) throws UnsupportedEncodingException {
        // 判断用户是否已经登录已经是否可以访问这个页面
        User user = (User) session.getAttribute("CURRENT_USER");
        if (user == null) {
            return "redirect:/login";
        }
        // 返回博客管理页面
        PageInfo info = blogService.findBlogsByUsername(user.getName(), page, size);
        model.addAttribute("blogs", info);
        return "admin-blogs";
    }

    @GetMapping("/admin/blog/{id}/edit")
    public String adminBlog(
            @PathVariable Integer id,
            Model model
    ) {
        //get blog
        Blog blog = blogService.findBlogById(id);
        model.addAttribute("blog", blog);
        return "edit";
    }

    @PutMapping("/admin/blog/{id}/edit")
    public String editBlog(
            HttpSession session,
            @Valid @RequestParam @Size(min=10, max=20) String title,
            @RequestParam String content,
            @PathVariable Integer id) throws UnsupportedEncodingException {
        // 保存这篇blog
        blogService.saveBlog(id, title, content);
        String username = ((User)session.getAttribute("CURRENT_USER")).getName();
        return "redirect:/admin/blog";
    }

    @DeleteMapping("/admin/blog/{id}")
    public String deleteBlog(@PathVariable Integer id,
                             HttpSession session) throws UnsupportedEncodingException {
        blogService.deleteBlog(id);
        String username = ((User)session.getAttribute("CURRENT_USER")).getName();
        return "redirect:/admin/blog";
    }

    @GetMapping("/admin")
    public String UserAdmin(HttpSession session,
                            HttpServletRequest req,
                            Model model) {
        User user = (User)session.getAttribute("CURRENT_USER");
        if (user == null) {
            session.setAttribute("next", req.getRequestURI());
            return "redirect:/login";
        } else {
            model.addAttribute("user", user);
            return "admin";
        }
    }
}
