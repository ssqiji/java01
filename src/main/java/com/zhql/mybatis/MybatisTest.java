package com.zhql.mybatis;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ly-zhql
 * @version V1.0
 * @createTime 2020/11/19 22:59
 */
interface UserMapper {
    @Select("select * from user where id = #{id} and name = #{name}")
    List<User> selectUserList(Integer id, String name);
}

public class MybatisTest {
    public static void main(String[] args) {
        UserMapper userMapper = (UserMapper) Proxy
                .newProxyInstance(MybatisTest.class.getClassLoader(), new Class[]{UserMapper.class},
                        (proxy, method, arg) -> {
                            Map<String, Object> nameArgMap = buildMethodArgNameMap(method, arg);
                            System.out.println(nameArgMap);
                            Select annotation = method.getAnnotation(Select.class);
                            if(annotation != null) {
                                String sql = annotation.value()[0];
                                sql = parseSql(sql, nameArgMap);
                                System.out.println(sql);
                            }
                            return null;
                        });
        userMapper.selectUserList(1, "zhql");
    }

    private static Map<String, Object> buildMethodArgNameMap(Method method, Object[] arg) {
        Map<String, Object> nameArgMap = new HashMap<>(8);
        Parameter[] parameters = method.getParameters();
        int[] index = {0};
        Arrays.asList(parameters).stream().forEach(p -> {
            nameArgMap.put(p.getName(), arg[index[0]]);
            index[0]++;
        });
        return nameArgMap;
    }

    public static String parseSql(String sql, Map<String, Object> nameArgMap) {
        StringBuilder sb = new StringBuilder();
        int len = sql.length();
        for (int i = 0; i< len; i++) {
            char c = sql.charAt(i);
            if(c == '#') {
                int nextIndex = i + 1;
                char nextChar = sql.charAt(nextIndex);
                if(nextChar != '{') {
                    throw new RuntimeException(String.format("这里应该为#{\nsql:%s\nindex:%d", sb.toString(), nextIndex));
                }
                StringBuilder args = new StringBuilder();
                i = parseSqlArg(args ,sql, nextIndex);
                String argName = args.toString();
                Object argValue = nameArgMap.get(argName);
                if(argValue == null) {
                    throw new RuntimeException(String.format("找不到参数值%s", argName));
                }
                sb.append(argValue.toString());
                continue;
            }
            sb.append(c);
        }
        return sb.toString();
    }

    private static int parseSqlArg(StringBuilder args, String sql, int nextIndex) {
        nextIndex++;
        for (; nextIndex < sql.length(); nextIndex++) {
            char c = sql.charAt(nextIndex);
            if(c != '}') {
                args.append(c);
                continue;
            }
            if(c == '}') {
                return nextIndex;
            }
        }
        throw new RuntimeException(String.format("缺少右括号\nindex:", nextIndex));
    }
}
