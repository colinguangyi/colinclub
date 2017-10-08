package com.colin.web.service;

import com.colin.web.dao.UserDao;
import com.colin.web.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zhaolz
 * @create 2017-09-07
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    @Transactional
    public List<User> getAll(){
        return (List<User>)userDao.findAll();
    }
}
