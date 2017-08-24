package com.lanou.service.impl;

import com.lanou.bean.Blog;
import com.lanou.bean.User;
import com.lanou.mapper.UserMapper;
import com.lanou.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yg on 17/8/23.
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;


    public List<Blog> findAllMessage() {
        List<Blog> allMessage = userMapper.findAllMessage();
        return allMessage;
    }

    public boolean loginBlog(@Param("name") String name,
                             @Param("password") String password) {
        User user1 = userMapper.loginBlog(name, password);
        if (user1 == null) {
            return false;
        } else {
            return true;
        }
    }

    public void regiest(User user) {
        System.out.println(user);
        userMapper.regiest(user);
    }

    public List<Blog> senior(String title) {

        String titles = "%" + title + "%";

        List<Blog> userList = userMapper.senior(titles);
        System.out.println("输出查询结果 :  "+userList);

        return userList;
    }


}
