package com.asiainfo.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**创建工厂对象工具类*/
public class SqlSessionFactoryUtil {

    private static SqlSession session;

    private static InputStream in;

    /**静态代码块，在方法调用之前调用而且只调用一次，随着类的加载而加载*/
    static {

        try {
            //1.通过流读取配置文件
            in = Resources.getResourceAsStream("SqlMapConfig.xml");
            //2.创建一个SqlSessionFactory工厂对象
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
            //3.通过工厂来生产SqlSession对象
            session = factory.openSession();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**1.调用方法，返回SqlSession对象*/
    public static SqlSession getSession(){
        return session;
    }

    /**2.调用方法，关流*/
    public static void liu() throws IOException {
        session.close();
        in.close();
    }

}
