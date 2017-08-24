package com.lanou.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Blog;
import com.lanou.bean.User;
import com.lanou.service.BlogService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by dllo on 17/8/23.
 */
@Controller
public class BlogController {

    @Resource
    private BlogService blogService;

    @RequestMapping(value = "/")
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/newblog")
    public String newBlog(){
        return "newblog";
    }

    @RequestMapping(value = "/return")
    public String return1(){
        return "index";
    }

    @RequestMapping(value = "/cc")
    public String content(){
        return "viewblog";
    }


    @RequestMapping(value = "/add")
    public String addBlog(HttpSession session, Blog blog){
        User user = (User) session.getAttribute("loginUser");
        blog.setUser(user);
        blogService.addBlog(blog);
      return "index";
    }

    @RequestMapping(value = "/findAll")
    @ResponseBody
    public List<Blog> findAllBlog(HttpSession session, Blog blog){
        User user = (User) session.getAttribute("loginUser");
        blog.setUser(user);
       List<Blog> blog2 =  blogService.findAllBlog(blog);
       return  blog2;
    }

    @RequestMapping(value = "/pagetest")
    @ResponseBody
    public PageInfo<Blog> pagetest(){
        return blogService.queryPage(1,2);
    }


    @RequestMapping(value = "/delete")
    @ResponseBody
    public Integer deleteBlog(@RequestParam("bid") int bid){
      Integer blogid = blogService.deleteBlog(bid);
       return blogid;
    }

    @RequestMapping(value = "/content")
    @ResponseBody
    public Blog findContent(@Param("id") int id){
      Blog blog =  blogService.findContentById(id);
        System.out.println(blog);
       return blog;
    }





}
