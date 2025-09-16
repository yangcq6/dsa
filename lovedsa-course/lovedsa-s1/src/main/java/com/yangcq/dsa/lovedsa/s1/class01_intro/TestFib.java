package com.yangcq.dsa.lovedsa.s1.class01_intro;

/**
 * 斐波那契数列
 * 数值：0 1 1 2 3 5 8 13
 * 项数：0 1 2 3 4 5 6 7
 *
 * @author yangcq6
 */
public class TestFib {

    // 每次f1的调用都是独立的，一共执行了2^n个f1
    // O(2^n)，存在多次重复调用
    public static int f1(int n) {
        if (n <= 1) return n;
        return f1(n - 1) + f1(n - 2);
    }

    // O(n)
    public static int f2(int n) {
        if (n <= 1) return n;
        int a = 0, b = 1;
        // n-1次加法
        for (int i = 0; i < n - 1; i++) {
            int res = a + b;
            a = b;
            b = res;
        }
        return b;
    }

    public static int f3(int n) {
        if (n <= 1) return n;
        int a = 0, b = 1;
        for (int i = 0; i < n - 1; i++) {
            b = a + b;
            a = b - a;
        }
        return b;
    }

    public static int f4(int n) {
        if (n <= 1) return n;
        int a = 0, b = 1;
        while (n-- > 1) {
            b = a + b;
            a = b - a;
        }
        return b;
    }

    // 特征方程
    public static int f5(int n) {
        double c = Math.sqrt(5);
        return (int) (((Math.pow((1 + c) / 2, n)) - (Math.pow((1 - c) / 2, n))) / c);
    }

    public static void main(String[] args) {
        // n=47超出int范围
        int n = 46;
        // System.out.println(f2(n));
        // System.out.println(f1(n));

        System.out.println(f5(n));

        Times.test("f4", new Times.Task() {
            @Override
            public void execute() {
                System.out.printf("f4(%d) = %d\n", n, f4(n));
            }
        });

        Times.test("f3", new Times.Task() {
            @Override
            public void execute() {
                System.out.printf("f3(%d) = %d\n", n, f3(n));
            }
        });

        Times.test("f2", new Times.Task() {
            @Override
            public void execute() {
                System.out.printf("f2(%d) = %d\n", n, f2(n));
            }
        });

        Times.test("f1", new Times.Task() {
            @Override
            public void execute() {
                System.out.printf("f1(%d) = %d\n", n, f1(n));
            }
        });
    }


    // 评估算法：1.时间复杂度。2.空间复杂度
    // 将每条指令执行时间记作单位时间，看每个算法需要多少的执行指令，粗略的计算算法的执行时间
    // 将计算的程序指令执行次数，化作函数表示，记作O(n)，忽略其中的低阶项、常数项、系数
    // O是粗略的估算模型
    public static void foo1(int n) {
        // 1
        if (n > 10) System.out.println("n > 10");
        else if (n > 5) System.out.println("n > 5");
        else System.out.println("n <= 5");

        // 1 + 5 + 4 + 4 -> 14
        // 15
        // O(1)
        for (int i = 0; i < 4; i++) {
            System.out.println("foo1");
        }
    }

    public static void foo2(int n) {
        // 1 + (n+1) + n + n
        // 3n + 2
        // O(n)
        for (int i = 0; i < n; i++) {
            System.out.println("foo2");
        }
    }

    public static void foo3(int n) {
        // 1 + (n+1) + n(3n+2) + n
        // 3n^2 + 4n + 2
        // O(n^2)
        for (int i = 0; i < n; i++) {
            // 3n+2
            for (int j = 0; j < n; j++) {
                System.out.println("foo3");
            }
        }
    }

    public static void foo4(int n) {
        // 1 + (n+1) + n(47) + n
        // 49n + 2
        // O(n)
        for (int i = 0; i < n; i++) {
            // 1 + 16 + 15 + 15
            // 47
            for (int j = 0; j < 15; j++) {
                System.out.println("foo4");
            }
        }
    }

    public static void foo5(int n) {
        // 判断n能被2整除多少次
        // n = 32, 16,8,4,2,1 一共5次
        // n = 16, 8,4,2,1    一共4次
        // n = 20, 10,5,2,1   一共4次
        // 对数换底公式，log2(n) = log2(9) * log9(n) ---> O(log2(n)) = O(log9(n))
        // 统称为logn
        // log2(n)
        // O(logn)
        while ((n = n / 2) > 0) {
            System.out.println("foo5");
        }
    }

    public static void foo6(int n) {
        // log5(n)
        // O(logn)
        while ((n = n / 5) > 0) {
            System.out.println("foo6");
        }
    }

    public static void foo7(int n) {
        // 1 + log2(n) + log2(n) * (3n+2) + log2(n)
        // 3nlog2(n) + 4log2(n) + 1
        // O(nlogn)
        for (int i = 1; i < n; i += i) {
            // 3n+2
            for (int j = 0; j < n; j++) {
                System.out.println("foo7");
            }
        }
    }

    public static void foo8(int n, int m) {
        // O(n + m)
        for (int i = 0; i < n; i++) {
            System.out.println("foo8");
        }
        for (int i = 0; i < m; i++) {
            System.out.println("foo8");
        }
    }
}
