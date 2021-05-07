package com.zhql.test;

@FunctionalInterface
public interface MyInterface<T, R> {
    R getValue(T t1, T t2);
}
