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


    //跳转界面
    @RequestMapping("/index")
    public String jump() {
        return "index";
    }

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
        System.out.println(blogList);
        return blogList;
    }


    //登录界面
    @RequestMapping("/loginBlog")
    public String loginBlog(HttpServletRequest httpServletRequest, @RequestParam("name") String name,
                            @RequestParam("password") String password ) {
       User user = userService.loginBlog(name, password);
        if (user !=null) {
            HttpSession session = httpServletRequest.getSession();
            session.setAttribute("name", name);
            session.setAttribute("loginUser",user);
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
            return "redirect:login";
        } else {
            return "regiest";
        }
    }


//    @RequestMapping(value = "/newblog")
//    public String aaa() {
//
//        System.out.println("xadasdasdjvjksdni你倒是跳啊");
//        return "success";
//    }


    //高级查询


}
