package com.asiainfo.test;

import com.asiainfo.domain.User;
import com.asiainfo.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MybatisCRUD {

    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private UserMapper userMapper;

    /**1.执行插入*/
    @Test
    public void testFindOne(){
        //1.为User实体类赋值
        User user = new User();
        user.setAddress("武汉");
        user.setBirthday(new Date());
        user.setSex("女");
        user.setUsername("二狗");
        System.out.println("调用方法之前"+user);
        //2.调用接口方法
        userMapper.saveUser(user);
        System.out.println("调用方法之后"+user);
    }

    /**
     * 数据存入集合遍历集合一条一条插入数据库
     */
    @Test
    public void saverUserList(){
        //1.为User实体类赋值
       List<User> list = new ArrayList<User>();
        User user = new User();
        user.setSex("女");
        user.setBirthday(new Date());
        user.setUserName("刘四");
        user.setAddress("内蒙");
        list.add(user);

        System.out.println("调用方法之前"+user);

        for (User user1 : list) {
            userMapper.saveUser(user1);
        }

        System.out.println("调用方法之后"+user);
    }
    /**2.测试执行修改*/
    @Test
    public void updateUser(){
        User user = new User();
        user.setUsername("六狗");
        user.setId(44);
        int i = userMapper.updteUser(user);
        System.out.println(i);

    }
    /**3.根据id查询结果*/
    @Test
    public void findById(){
        User user = userMapper.findById(44);
        System.out.println(user);
    }
    /**4.根据id删除用户*/
    @Test
    public void deleteById(){
        int i = userMapper.deleteById(44);
        System.out.println(i);
    }
    /**5.聚合函数汇总*/
    @Test
    public void findTatol(){
        int tatol = userMapper.findTatol();
        System.out.println("用户总量:"+ tatol);
    }
    /**6.查询所有用户*/
    @Test
    public void findAll(){
        List<User> all = userMapper.findAll();
        for (User user : all) {
            System.out.println(user);
        }
    }

    /**1.在测试方法执行之前执行*/
    @Before
    public void init() throws IOException {
        //1.读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建构建者对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //3.创建 SqlSession 工厂对象
        factory = builder.build(in);
        //4.创建 SqlSession 对象
        session = factory.openSession(true);
        //5.创建 Dao 的代理对象
        userMapper = session.getMapper(UserMapper.class);
    }
    /**2.在测试方法执行之后执行*/
    @After
    public void destroy() throws IOException {
        //提交事务
        //session.commit();
        session.close();
        in.close();
    }
}
