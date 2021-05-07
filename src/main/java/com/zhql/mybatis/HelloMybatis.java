package com.zhql.mybatis;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author ly-zhql
 * @version V1.0
 * @createTime 2020/11/24 11:33
 */
public class HelloMybatis {
    public static void main(String[] args) throws IOException {
//        DataSource dataSource = getDataSource();
//        TransactionFactory transactionFactory = new JdbcTransactionFactory();
//        Environment environment = new Environment("development", transactionFactory, dataSource);
//        Configuration configuration = new Configuration(environment);
//        configuration.addMapper(BlogMapper.class);
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
//        try (SqlSession session = sqlSessionFactory.openSession()) {
//            BlogMapper mapper = session.getMapper(BlogMapper.class);
//            Blog blog = mapper.selectBlog(1);
//            System.out.println(blog);
//        }
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        try (SqlSession session = sqlSessionFactory.openSession()) {
            Blog blog = session.selectOne("com.zhql.mybatis.BlogMapper.selectBlog", 1);
            System.out.println(blog);
//            BlogMapper mapper = session.getMapper(BlogMapper.class);
//            Blog blog = mapper.selectBlog(1);
//            System.out.println(blog);
        }
    }

    public static DataSource getDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=gbk&zeroDateTimeBehavior=convertToNull&serverTimezone=Asia/Shanghai&useSSL=false&jdbcCompliantTruncation=false");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }
}

interface BlogMapper {
    //@Select("select name from blog where id = #{id}")
    Blog selectBlog(Integer id);
}

