package club.banyuan.blog.controller;

import club.banyuan.blog.bean.User;
import club.banyuan.blog.service.LoginService;
import club.banyuan.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @GetMapping
    public String showLoginPage() {
        return "login";
    }

    @PostMapping
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model
                        ) throws UnsupportedEncodingException {
        User user = loginService.login(username, password);
        if (user == null) {
            return "login";
        }

        return "redirect:/admin/" + URLEncoder.encode(username, "UTF-8");
    }
}
