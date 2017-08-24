package com.lanou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.bean.Blog;
import com.lanou.mapper.BlogMapper;
import com.lanou.service.BlogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dllo on 17/8/23.
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Resource
    private BlogMapper blogMapper;

    public void addBlog(Blog blog) {
       blogMapper.addBlog(blog);
    }

    public List<Blog> findAllBlog() {
        return blogMapper.findAllBlog();
    }

    public PageInfo<Blog> queryPage(Integer pagenum, Integer pageSize) {
        pagenum = pagenum == null?1:pagenum;
        pageSize = pageSize == null?5:pagenum;

        PageHelper.startPage(pagenum,pageSize);
        List<Blog> list =  blogMapper.findAllBlog();

        PageInfo<Blog> pageInfo = new PageInfo<Blog>(list);

        return pageInfo;
    }

    public Integer deleteBlog(int bid) {
        return blogMapper.deleteBlog(bid);
    }

    public Blog findContentById(int id) {
        return blogMapper.findContentById(id);
    }


}
