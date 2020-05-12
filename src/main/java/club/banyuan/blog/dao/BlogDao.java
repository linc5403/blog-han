package club.banyuan.blog.dao;

import club.banyuan.blog.bean.Blog;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogDao {
    // @Select("select * from blog where id = #{id}")
    public Blog findBlogById(Integer id);

}
