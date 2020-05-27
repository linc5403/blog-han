package club.banyuan.blog.controller;

import club.banyuan.blog.service.IndexService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexController {
    @Autowired
    private IndexService indexService;

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @GetMapping({"/", "/index"})
    public String showHomepage(
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            Model model
    ) {
        // 获取blogs，按照时间倒排
        PageInfo info = indexService.sortBlogsByDate(page, size);
        model.addAttribute("blogs", info);
        logger.info("in IndexController!!!!!!!");
        return "index";
    }
}
