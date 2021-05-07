package com.zhql.test;

import java.util.HashMap;
import java.util.Map;

public class MyKey {

    private Integer keyId;

    public MyKey(Integer keyId) {
        this.keyId = keyId;
    }

    @Override
    public int hashCode() {
        return keyId.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyKey key = (MyKey) o;
        return keyId.equals(key.keyId);
    }

    public static void main(String[] args) {
        MyKey key1 = new MyKey(1);
        MyKey key2 = new MyKey(1);
        Map<MyKey, String> map = new HashMap<>(4);
        map.put(key1, "keyId为1");
        System.out.println(map.get(key2));
    }
}
