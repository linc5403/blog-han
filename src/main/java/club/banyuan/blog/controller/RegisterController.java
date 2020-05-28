package club.banyuan.blog.controller;

import club.banyuan.blog.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.NoSuchAlgorithmException;

@Controller
public class RegisterController {
    @Autowired
    RegisterService registerService;

    @GetMapping("/register")
    String showRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    String register(@RequestParam String email,
                    @RequestParam String name,
                    @RequestParam String password) throws NoSuchAlgorithmException {
        // 调用service层处理
        registerService.register(name, password, email);

        // 返回首页
        return "redirect:/";
    }

    @GetMapping("/active")
    String active(@RequestParam String token) {
        registerService.active(token);
        return "redirect:/login";
    }
}
