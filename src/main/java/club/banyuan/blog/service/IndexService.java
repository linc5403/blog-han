package club.banyuan.blog.service;

import club.banyuan.blog.dao.BlogDao;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndexService {

    @Autowired
    private BlogDao blogDao;

    public PageInfo sortBlogsByDate(Integer page, Integer size) {
        PageHelper.startPage(page, size, "created_time desc");
        return new PageInfo(blogDao.selectAllBlogs());
    }
}
