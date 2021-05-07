package com.zhql.test;

import com.zhql.demo.Transaction;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class Demo {

    List<Employee> emps = Arrays.asList(
            new Employee("张三", 18, Status.FREE),
            new Employee("李四", 32, Status.BUSY),
            new Employee("王五", 24, Status.VOCATION),
            new Employee("赵六", 14, Status.BUSY),
            new Employee("田七", 31, Status.FREE),
            new Employee("三八", 27, Status.FREE)
    );


    /**
     * 1、给定一个数字列表，如何返回一个由每个数的平方组成的列表？
     * 给定【1，2，3，4，5】，返回【1，4，9，16，25】
     */

    List<Transaction> transactions;

    @Before
    public void before() {
        Trader trader1 = new Trader("张三", "武汉");
        Trader trader2 = new Trader("李四", "广州");
        Trader trader3 = new Trader("王五", "武汉");
        Trader trader4 = new Trader("赵六", "成都");

        transactions = Arrays.asList(
                new Transaction(trader1, 2011, 300),
                new Transaction(trader2, 2013, 100),
                new Transaction(trader3, 2014, 200),
                new Transaction(trader4, 2011, 700),
                new Transaction(trader1, 2013, 620),
                new Transaction(trader4, 2011, 303)
        );
    }

    @Test
    public void test1() {
        // 8、找到交易额最小的交易
        Optional<Transaction> min = transactions.stream()
                .min(Comparator.comparingInt(Transaction::getValue));
        System.out.println(min.get());

        Optional<Transaction> collect = transactions.stream()
                .collect(Collectors.minBy(Comparator.comparingInt(Transaction::getValue)));
        System.out.println(collect.get());
    }

}



