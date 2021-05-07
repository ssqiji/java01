package com.zhql.mybatis;

/**
 * @author ly-zhql
 * @version V1.0
 * @createTime 2020/11/24 11:45
 */
public class Blog {
    private Integer id;
    private String name;

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
