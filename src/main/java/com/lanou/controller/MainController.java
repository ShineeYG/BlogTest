package com.lanou.controller;

import com.lanou.bean.Blog;
import com.lanou.bean.User;
import com.lanou.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by yg on 17/8/23.
 */
@Controller
public class MainController {

    @Resource
    private UserService userService;


//    //跳转界面
//    @RequestMapping("/")
//    public String jump() {
//        return "index";
//    }

    //跳转到登录界面
    @RequestMapping("login")
    public String login() {
        return "login";
    }

    //跳转到注册界面
    @RequestMapping("regiestP")
    public String registe() {
        return "regiest";
    }


    //查找出所有的评论
    @RequestMapping("/findAllMessage")
    @ResponseBody
    public List<Blog> findAllMessage() {
        List<Blog> blogList = userService.findAllMessage();
//        System.out.println(blogList);
        return blogList;
    }


    //登录界面
    @RequestMapping("/loginBlog")
    public String loginBlog(HttpServletRequest httpServletRequest, @RequestParam("name") String name,
                            @RequestParam("password") String password) {
        System.out.println(name + "      +     " + password);
        boolean b = userService.loginBlog(name, password);
        if (b) {
            HttpSession session = httpServletRequest.getSession();
            session.setAttribute("name", name);
            return "index";
        } else {
            return "regiest";
        }
    }


    //注册
    @RequestMapping("/regiest")
    public String regiest(HttpServletRequest httpServletRequest, @RequestParam("name") String name, @RequestParam("password") String password,
                          @RequestParam("secPassword") String secPassword, @RequestParam("tel") String tel,
                          @RequestParam("address") String address) {
        System.out.println("name: " + name + "password :" + password + "secPassword :" + secPassword + " tel :" + tel + " address :" + address);
        if (password.equals(secPassword)) {
            User user1 = new User();
            user1.setName(name);
            user1.setPassword(password);
            user1.setTel(tel);
            user1.setAddress(address);
            userService.regiest(user1);
            httpServletRequest.getSession().setAttribute("name", name);
            return "login";
        } else {
            return "regiest";
        }
    }


    //高级查询
    @RequestMapping(value = "/senior")
    @ResponseBody
    public List<Blog> senior(@RequestParam("title") String title) {
        System.out.println("输出info    " + title);
        List<Blog> userList = userService.senior(title);
        return userList;
    }

}
