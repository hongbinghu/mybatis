package com.asiainfo.test;

import com.asiainfo.mapper.UserMapper;
import com.asiainfo.domain.User;
import com.asiainfo.util.SqlSessionFactoryUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**测试类*/
public class UserTest {

    public static void main(String[] args) throws IOException {
        /**1.不使用工具类*/
        System.out.println("------------------------不使用工具类------------------------");
        Test();
        System.out.println("------------------------使用工具类------------------------");
        /**2.使用工具类*/
        Util();
    }
    private static void Util() throws IOException {
        /**1.使用工厂构建对象，创建mapper接口代理对象*/
        UserMapper mapper = SqlSessionFactoryUtil.getSession().getMapper(UserMapper.class);
        /**2.使用代理对象执行dao接口中的方法*/
        List<User> userList = mapper.findAll();
        /**3。遍历结果集*/
        for (User user:userList) {
            System.out.println(user);
        }
        /**4.关流*/
        SqlSessionFactoryUtil.liu();
    }
    private static void Test() throws IOException {
        //1.读取配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建 SqlSessionFactory 的构建者对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //3.使用构建者创建工厂对象 SqlSessionFactory
        SqlSessionFactory factory = builder.build(in);
        //4.使用 SqlSessionFactory 生产 SqlSession 对象
        SqlSession session = factory.openSession();
        //5.使用 SqlSession 创建 mapper 接口的代理对象
        UserMapper userDao = session.getMapper(UserMapper.class);
        //6.使用代理对象执行查询所有方法
        List<User> users = userDao.findAll();
        for(User user : users) {
            System.out.println(user);
        }
        //7.释放资源
        session.close();
        in.close();
    }

}
