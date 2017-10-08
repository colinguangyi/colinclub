package com.colin.web;

import com.colin.util.jdbc.JdbcTemplateHandler;
import com.colin.web.entity.User;
import com.colin.web.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author zhaolz
 * @create 2017-09-07
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.xml")
public class UserTest {
    @Autowired
    private UserService userService;
    @Autowired
    private JdbcTemplateHandler jdbcTemplateHandler;

    @Test
    public void test1(){
        List<User> users = userService.getAll();
        System.out.println(users);
    }

    @Test
    public void test2(){
        String sql = "select * from tb_user where id = ? and name = ?";
        try{
            String name = "张13";
            Page<User> users = jdbcTemplateHandler.pageHandlerBySqlStr(sql, 1, 10, 1, name);
            System.out.println(users.getContent());
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
