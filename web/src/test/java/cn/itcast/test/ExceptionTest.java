package cn.itcast.test;

import org.junit.Test;

public class ExceptionTest {
    @Test
    public void test01() {
        System.out.println("start");
        char[] chars = new char[]{'a'};
        String s = chars.toString();
        System.out.println(s);
    }
}
