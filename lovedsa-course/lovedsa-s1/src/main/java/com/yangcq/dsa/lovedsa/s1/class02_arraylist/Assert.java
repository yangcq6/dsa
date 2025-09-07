package com.yangcq.dsa.lovedsa.s1.class02_arraylist;

/**
 *
 * @author yangcq6
 */
public class Assert {

    public static void test(boolean condition) {
        if (!condition) {
            try {
                throw new Exception("测试未通过");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
