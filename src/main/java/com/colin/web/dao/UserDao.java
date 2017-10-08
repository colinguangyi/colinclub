package com.colin.web.dao;

import com.colin.web.entity.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author zhaolz
 * @create 2017-09-07
 */
public interface UserDao extends PagingAndSortingRepository<User, Long>,JpaSpecificationExecutor<User>{
}
