package com.asiainfo.mapper;

import com.asiainfo.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Dao接口
 */
public interface UserMapper {

    /**1.查询所有用户*/
    List<User> findAll();

    /**2.模糊查询用户*/
    List<User> findLike(String userName);

    /**3.插入一条数据*/
    int saveUser(User user);

    /**4.更新一条数据*/
    int updteUser(User user);

    /**5.根据id查询结果集*/
    User findById(Integer id);

    /**6.根据id删除用户*/
    int deleteById(Integer id);

    /**7.查询总数*/
    int findTatol();
}
