package com.asiainfo.test;

import com.asiainfo.domain.User;
import com.asiainfo.mapper.UserMapper;
import com.asiainfo.util.SqlSessionFactoryUtil;

import java.io.IOException;
import java.util.List;

public class UserLike {

    public static void main(String[] args) throws IOException {

        List<User> userList = SqlSessionFactoryUtil.getSession().getMapper(UserMapper.class).findLike("%王%");
        for (User u:userList) {
            System.out.println(u);
        }
        SqlSessionFactoryUtil.liu();
    }

}
