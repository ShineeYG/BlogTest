package com.lanou.mapper;

import com.lanou.bean.Blog;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dllo on 17/8/23.
 */
@Repository
public interface BlogMapper {


    void addBlog(Blog blog);

    List<Blog> findAllBlog();


    Integer deleteBlog(int bid);


    Blog findContentById(int id);
}
