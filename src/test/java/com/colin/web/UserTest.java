package com.colin.web;

import com.colin.util.jdbc.JdbcTemplateHandler;
import com.colin.web.dao.TestDao;
import com.colin.web.dao.UserDao;
import com.colin.web.entity.Tbtest;
import com.colin.web.entity.User;
import com.colin.web.service.UserService;
import org.apache.commons.net.ntp.TimeStamp;
import org.hibernate.hql.spi.id.TableBasedDeleteHandlerImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.*;

/**
 * @author zhaolz
 * @create 2017-09-07
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.xml")
public class UserTest {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserService userService;
    @Autowired
    private JdbcTemplateHandler jdbcTemplateHandler;
    @Resource
    private TestDao testDao;

    @Test
    public void test1(){
        List<User> users = userService.getAll();
        System.out.println(users);
    }

    @Test
    public void test2(){
        String sql = "select * from tb_user where name = ?";
        try{
            String name = "1 or 1=1";
            Page<User> users = jdbcTemplateHandler.pageHandlerBySqlStr(sql, 1, 10, name);
            System.out.println(users.getContent());
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @Test
    public void test3(){
        User user = new User();
        user.setAge(20);
        user.setName("testTime");

        //开始时间
        Date nowDate = new Date();
        //一年后时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(nowDate);
        calendar.add(Calendar.YEAR, 1);

        Timestamp now = new Timestamp(nowDate.getTime());
        Timestamp oneYearLater = new Timestamp(calendar.getTime().getTime());
        user.setBeginTime(now);
        user.setEndTime(oneYearLater);

        user.setBeginTime(now);
        user.setEndTime(oneYearLater);
        System.out.println(userDao.save(user));
    }

    @Test
    public void test4(){
        String path = "C:\\Users\\zlz\\Desktop\\11.xlsx";
        try{
            userService.import2DB(path);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test5(){
        Tbtest test  = new Tbtest();
        test.setName("test");
        System.out.println(testDao.save(test));
    }

}
