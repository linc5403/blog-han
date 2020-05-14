package club.banyuan.blog.controller;

import club.banyuan.blog.bean.User;
import club.banyuan.blog.service.BlogService;
import club.banyuan.blog.service.LoginService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private BlogService blogService;

    @GetMapping
    public String showLoginPage() {
        return "login";
    }

    @PostMapping
    public String login(@RequestParam(name="username1") String username,
                        @RequestParam String password,
                        Model model
    ) {
        // 验证用户登录
        User user = loginService.login(username, password);
        if (user == null) {
            return "login";
        }
        PageInfo info = blogService.findBlogs(user, 1, 10);

        model.addAttribute("blogs", info);
        return "redirect:/admin/" + username;
    }
}
