package club.banyuan.blog.controller;

import club.banyuan.blog.bean.Blog;
import club.banyuan.blog.bean.User;
import club.banyuan.blog.service.BlogService;
import club.banyuan.blog.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.io.UnsupportedEncodingException;
import java.security.Principal;

@Controller
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    BlogService blogService;

    @GetMapping("/admin/blog")
    public String adminBlog(
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            HttpSession session,
            Model model,
            Principal principal
            ) throws UnsupportedEncodingException {
        String username = principal.getName();
        User user = userService.findByName(username);
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
    @CacheEvict(value = "index", allEntries = true)
    public String editBlog(
            HttpSession session,
            @Valid @RequestParam @Size(min=10, max=20) String title,
            @RequestParam String content,
            @PathVariable Integer id) throws UnsupportedEncodingException {
        // 保存这篇blog
        blogService.saveBlog(id, title, content);
        return "redirect:/admin/blog";
    }

    @DeleteMapping("/admin/blog/{id}")
    public String deleteBlog(@PathVariable Integer id,
                             HttpSession session) throws UnsupportedEncodingException {
        blogService.deleteBlog(id);
        return "redirect:/admin/blog";
    }

    @GetMapping("/admin")
    public String userAdmin(HttpSession session,
                            HttpServletRequest req,
                            Model model,
                            Principal principal
                            ) {
        String username = principal.getName();
        User user = userService.findByName(username);
        model.addAttribute("user", user);
        return "admin";
    }

    @GetMapping("/admin/blog/create")
    public String showBlogCreatePage() {
        return "create";
    }

    @PostMapping("/admin/blog/create")
    String createBlog(@RequestParam(value = "title") String title,
                      @RequestParam(value = "content") String content,
                      Principal principal) {
        //????blogger?????
        User user = userService.findByName(principal.getName());
        Blog blog = new Blog();
        blog.setAuthor(user);
        blog.setTitle(title);
        blog.setContent(content);
        // 将blog增加进数据库
        Integer blogId = blogService.addBlog(blog);
        // 展示所创建的blog ?? item.html
        return "redirect:/blog/" + blogId;
    }
}
