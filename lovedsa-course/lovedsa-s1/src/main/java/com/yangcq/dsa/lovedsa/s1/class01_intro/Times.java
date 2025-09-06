package com.yangcq.dsa.lovedsa.s1.class01_intro;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author yangcq6
 */
public class Times {

    public static final SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    public interface Task {
        void execute();
    }

    public static void test(String title, Task task) {
        if (task == null) return;
        title = (title == null) ? "" : "【" + title + "】";
        System.out.println(title);

        System.out.println("开始时间：" + fmt.format(new Date()));
        long start = System.currentTimeMillis();
        task.execute();
        long end = System.currentTimeMillis();
        System.out.println("结束时间：" + fmt.format(new Date()));
        System.out.println("总耗时：" + (end - start) + " ms");
        System.out.println("----------------------------------");
    }
}
