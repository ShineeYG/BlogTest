package com.lanou.mapper;

import com.lanou.bean.Blog;
import com.lanou.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by yg on 17/8/23.
 */
@Repository
public interface UserMapper {


    List<Blog> findAllMessage();

    User loginBlog(@Param("name") String name,
                   @Param("password") String password);

   void regiest(User user);

    List<Blog> senior(String title);
}
